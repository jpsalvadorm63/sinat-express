package sinat.express

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import util.AppSession
import externos.DPALP

@Transactional(readOnly = true)
class DPAController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 24, 100)
        params.sort = (params.sort)?:'nombre'
        params.order = (params.order)?:'ASC'
        render view:'index',
               model:[ DPAInstanceList: DPALP.findAllByNivel('PROVINCIA',params),
                       DPAInstanceCount: DPALP.findAllByNivel('PROVINCIA').size() ]
    }

    @Transactional
    def updateProvincia() {
        def provincia = DPALP.get(params.provinciaId)
        def cantones = DPALP.findAllBySuperior(provincia)
        def canton = cantones?cantones[0]:null
        // println "\n$params\n$provincia\n$canton\n${getSession().id}"
        AppSession.setSessionVar(session.id,'provincia',provincia?.codigo)
        AppSession.setSessionVar(session.id,'canton',canton?.codigo)
        setEnvelope(canton)
        render g.select(
              style:'width:260px;',
              id:'canton',
              name:'canton.id',
              from:DPALP.cantones(provincia),
              optionKey:'id',
              value:canton?.id,
              class:'many-to-one',
              onchange: 'cambiaCanton(this.value)' )
    }

    @Transactional
    def updateCanton() {
        def canton = DPALP.get(params.cantonId)
        AppSession.setSessionVar(session.id,'canton',canton?.codigo)
        setEnvelope(canton)
        render ""
    }

    def setEnvelope(canton) {
        AppSession.setSessionVar(session.id,'cantonminx',0)
        AppSession.setSessionVar(session.id,'cantonminy',0)
        AppSession.setSessionVar(session.id,'cantonmaxx',0)
        AppSession.setSessionVar(session.id,'cantonmaxy',0)
        /*
        def sqlstr = "select min(minx), min(miny), max(maxx), max(maxy) from lp.Prediogr where gad = '" + canton.codigo + "'"
        def data = ????.Prediogr.executeQuery(sqlstr)
        data.each {
            AppSession.setSessionVar(session.id,'cantonminx',it[0])
            AppSession.setSessionVar(session.id,'cantonminy',it[1])
            AppSession.setSessionVar(session.id,'cantonmaxx',it[2])
            AppSession.setSessionVar(session.id,'cantonmaxy',it[3])
        }
        */
    }

  @Transactional
  def cambiarCanton() {
    if(AppSession.getSessionVar(session.id,'provincia') == null && AppSession.getSessionVar(session.id,'canton') == null ) {
      AppSession.setSessionVar(session.id,'provincia','01')
      AppSession.setSessionVar(session.id,'canton','0101')
    }
    render( view:"cambiarCanton",
            model:[ provincia:DPALP.findByCodigo(AppSession.getSessionVar(session.id,'provincia')),
                    canton:DPALP.findByCodigo(AppSession.getSessionVar(session.id,'canton')) ])
  }

  def cantones() {
    params.sort = (params.sort)?:'nombre'
    params.order = (params.order)?:'ASC'
    def provincia = DPALP.findByCodigo(params.id)
    def cantones = DPALP.findAllByNivelAndSuperior('CANTON',provincia,params)
    render template:"cantones", model:[DPAInstanceList:cantones]
  }

  def parroquias() {
    params.sort = (params.sort)?:'nombre'
    params.order = (params.order)?:'ASC'
    def canton = DPALP.findByCodigo(params.id)
    def parroquias = DPALP.findAllByNivelAndSuperior('PARROQUIA',canton,params)
    render template:"parroquias", model:[DPAInstanceList:parroquias]
  }

  def reportes(Integer max) {
    params.max = Math.min(max ?: 16, 100)
    params.sort = (params.sort)?:'nombre'
    params.order = (params.order)?:'ASC'
    respond DPALP.findAllByNivel('PROVINCIA',params), model:[DPAInstanceCount: DPALP.findAllByNivel('PROVINCIA').size()]
  }

  def cantonesRep() {
    params.sort = (params.sort)?:'nombre'
    params.order = (params.order)?:'ASC'
    def provincia = DPALP.findByCodigo(params.id)
    def cantones = DPALP.findAllByNivelAndSuperior('CANTON',provincia,params)
    render template:"cantonesRep", model:[DPAInstanceList:cantones]
  }

  def informacionGeneralACsv() {
    response.setHeader "Content-disposition", "attachment; filename=InformacionGeneral.csv"
    response.contentType = 'text/csv'
    def textcsv = ''
    def textcabecera='CODIGO_FICHA,FECHA,UTMX,UTMY,ALTITUD,ZONA_HOMOGENEA,SECTOR,PARROQUIA,CANTON,PROVINCIA,PROPIETARIO,ARRENDATARIO,ADMINISTRADOR,ENCUESTADO,SUPERFICIE(ha),CODIGO_CATASTRAL,CONSTRUCCION,LEGALIZACION\n'
    def cantonInstance=DPALP.findByCodigo(params.id)
    def fichaCampoInstanceList=FichaCampo.findAllByCanton(cantonInstance)
    fichaCampoInstanceList.each { ficha ->
      textcsv += "${ficha.numeroFicha},${ficha.fecha},${ficha.coordenadaX?:''},${ficha.coordenadaY?:''},${ficha.altitud?:''},${ficha.zonaHomogenea?:''},${ficha.sector?:''},${ficha.parroquia?:''},${ficha.canton},${ficha.provincia},${ficha.nombrePropietario?:''},${ficha.nombreArrendatario?:''},${ficha.nombreAdministrador?:''},${ficha.encuestado?:''},${ficha.superficieTotal?:''},=\"${ficha.codigoCatastral?:''}\",${ficha.construccion?:''},${ficha.legalizacion?:''}\n"
    }
    response.outputStream << textcabecera
    response.outputStream << textcsv
    response.outputStream.flush()
  }

  def serviciosBasicosACsv() {
    response.setContentType("application/vnd.ms-excel:UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=ServiciosBasicos.csv");
    //response.setHeader "Content-disposition", "attachment; filename=ServiciosBasicos.csv"
    //response.setContentType("application/vnd.ms-excel:UTF-8");
    OutputStream outputStream = response.getOutputStream();
    def textcsb = ''
    def textcabecerasb='CODIGO_FICHA,ENERGIA,AGUA,ALCANTARILLADO,COMUNICACIONES,ACCESIBILIDAD\n'
    def cantonInstance=DPALP.findByCodigo(params.id)
    def fichaCampoInstanceList=FichaCampo.findAllByCanton(cantonInstance)
    fichaCampoInstanceList.each { fichasb ->
      // textcsb += "${fichasb.numeroFicha},${fichasb.sbEnergiaElectrica},${fichasb.sbAguaPotable},${fichasb.sbAlcantarillado},${fichasb.sbComunicaciones},${fichasb.accesibilidad}\n"
      textcsb += "${fichasb.numeroFicha},${fichasb.sbEnergiaElectrica?'Si':'No'},${fichasb.sbAguaPotable?'Si':'No'},${fichasb.sbAlcantarillado?'Si':'No'},${fichasb.sbComunicaciones?'Si':'No'},${fichasb.accesibilidad?:""}\n"
    }
    outputStream << textcabecerasb
    outputStream << textcsb
    outputStream.flush()
  }

  def coberturaACsv() {
    response.setHeader "Content-disposition", "attachment; filename=Cobertura.csv"
    response.contentType = 'text/csv'
    def textccb = ''
    def textcabeceracb='CODIGO_FICHA,USO,COBERTURA,ESPECIFICAR,SUPERFICIE,ROTACION,COSECHA,CARGA_ANIMAL,RENDIMIENTO,PRECIO PRODUCTO,TECNOLOGIA,RIEGO,MECANIZACION,VALOR_OFERTA,VALOR_VENTA,VALOR_ARRIENDO\n'
    def cantonInstance = DPALP.findByCodigo(params.id)
    def fichaCampoInstanceList=FichaCampo.findAllByCanton(cantonInstance)
    fichaCampoInstanceList.each {fc ->
      def numficha=fc.numeroFicha
      def coberturaInstanceList = Cobertura.findAllByFichaCampo(fc)
      coberturaInstanceList.each {c ->
        //textccb += "$numficha,${c.tipoUso?.nombre},${c.tipoCobertura?.nombre},${c.superficie},${c.rotacion},${c.cosechasPorAnio},${c.cargaAnimal},${c.rendimiento},${c.tecnologiaPredominante?.nombre},${c.sistemaDeRiego},${c.mecanizacion},${c.oferta},${c.venta},${c.arriendo}\n"
        textccb += "$numficha,${c.tipoUso?:''},${c.tipoCobertura?:''},${c.otrosCultivo?:''},${c.superficie?:''},${c.rotacion?:''},${c.cosechasPorAnio?:''},${c.cargaAnimal?:''},${c.rendimiento?:''},${c.precioProducto?:''},${c.tecnologiaPredominante?:''},${c.sistemaDeRiego?:''},${c.mecanizacion?:''},${c.oferta?:''},${c.venta?:''},${c.arriendo?:''}\n"
      }
    }
    response.outputStream << textcabeceracb
    response.outputStream << textccb
    response.outputStream.flush()
  }

  def habitacionalACsv() {
    response.setHeader "Content-disposition", "attachment; filename=Habitacional.csv"
    response.contentType = 'text/csv'
    def textchb = ''
    def textcabecerahb='CODIGO_FICHA,SUPERFICIE,LEGALIZACION,AGUA,ENERGIA,ALCANTARILLADO,COMUNICACIONES,VALOR_OFERTA,VALOR_VENTA,VALOR_ARRIENDO\n'
    def cantonInstance = DPALP.findByCodigo(params.id)
    def fichaCampoInstanceList=FichaCampo.findAllByCanton(cantonInstance)
    fichaCampoInstanceList.each { fh ->
      def numficha=fh.numeroFicha
      def habitacionalInstanceList=Habitacional.findAllByFichaCampo(fh)
      habitacionalInstanceList.each {habit ->
        // textchb += "$numficha,${habit.superficie},${habit.legalizacion},${habit.sbAguaPotable},${habit.sbEnergiaElectrica},${habit.sbAlcantarillado},${habit.sbComunicaciones},${habit.oferta},${habit.venta},${habit.arriendo}\n"
        textchb += "$numficha,${habit.superficie?:''},${habit.legalizacion?:''},${habit.sbAguaPotable?'Si':'No'},${habit.sbEnergiaElectrica?'Si':'No'},${habit.sbAlcantarillado?'Si':'No'},${habit.sbComunicaciones?'Si':'No'},${habit.oferta?:''},${habit.venta?:''},${habit.arriendo?:''}\n"
      }
    }
    response.outputStream << textcabecerahb
    response.outputStream << textchb
    response.outputStream.flush()
  }

}
