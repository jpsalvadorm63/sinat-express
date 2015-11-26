package sinat.express

import groovy.sql.Sql
import externos.DPALP
import util.AppSession

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FichaCampoController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
  def dataSource
  Sql sql = null
  //static wms = 'http://172.21.1.25:9090/geoserver/wms'
  static wms = 'http://45.55.142.238:9090/geoserver/wms'
  //static wms = 'http://localhost:9090/geoserver/wms'
  static photoDirBase = '/var/fic'
  static allowedExtensions = ["gif","png","jpg","jpeg","PNG","JPG","JPEG"]

  def GeomsService

  def ping() { render "<span>pung !</span>" }

  def index(Integer max) {
    params.max = Math.min(max ?: 16, 100)
    def n
    def lista
    if (AppSession.getSessionVar(session.id, 'canton') != null) {
      lista = FichaCampo.findAllByCanton(DPALP.findByCodigo(AppSession.getSessionVar(session.id, 'canton')), params)
      n = FichaCampo.countByCanton(DPALP.findByCodigo(AppSession.getSessionVar(session.id, 'canton')))
    } else {
      n = FichaCampo.count
      lista = FichaCampo.list(params)
    }
    respond lista, model: [fichaCampoInstanceCount: n]
  }

  def show(FichaCampo fichaCampoInstance) {
    def photoFilesList = []
    if(fichaCampoInstance && fichaCampoInstance.codigoCatastral && fichaCampoInstance.codigoCatastral != '') {
      photoFilesList = allPhotos(photoDirBase,fichaCampoInstance.codigoCatastral)
    }
    def coberturaInstanceList = Cobertura.findAllByFichaCampo(fichaCampoInstance)
    def habitacionalInstanceList = Habitacional.findAllByFichaCampo(fichaCampoInstance)
    respond fichaCampoInstance,
        model:[ showing:'true',
                opname:'PRESENTACIÓN',
                actionName:'SHOWING',
                coberturaInstanceList:coberturaInstanceList,
                habitacionalInstanceList:habitacionalInstanceList,
                claveCatastral: fichaCampoInstance.codigoCatastral,
                photos:photoFilesList ]
  }

  def allPhotos(photoDirBase,cc) {
    def photoFilesList = []
    def photoDir = new File(photoDirBase)
    if(photoDir.exists() && photoDir.isDirectory()) {
      photoDir = new File(photoDirBase + "/" + cc)
      if(!photoDir.exists())
        photoDir.mkdir();
      // buuild list of graphic files
      photoDir.listFiles().each() {
        if(!it.isDirectory())
          photoFilesList << it.getName()
      }
    }
    return photoFilesList
  }

  @Transactional
  def create() {
    def fichaNueva = anyFichacampo()
    if( params.id != null ) {
      sql = new Sql(dataSource)
      def strqry = "select st_area(geom) area, st_x(st_centroid(geom)) x, st_y(st_centroid(geom)) y from chunchiforweb where codigocata = '" + params.id +"'"
      def data = sql.firstRow(strqry)
      fichaNueva.codigoCatastral = params.id
      Double suptotal = data[0]/10000; suptotal = suptotal * 100; suptotal = suptotal.trunc(); suptotal = suptotal / 100
      fichaNueva.superficieTotal = suptotal
      Double x = data[1] * 10.0; x = x.trunc()/10.0
      fichaNueva.coordenadaX = "${x.toString()}"
      Double y = data[2] * 10.0; y = y.trunc()/10.0
      fichaNueva.coordenadaY = "${y.toString()}"
      fichaNueva.provincia = DPALP.findByCodigo(params.id[0..1])
      fichaNueva.canton = DPALP.findByCodigo(params.id[0..3])
      fichaNueva.parroquia = DPALP.findByCodigo(params.id[0..5])
      fichaNueva.zonaHomogenea = '?'
      fichaNueva.construccion = ''
      fichaNueva.legalizacion = ''
    } else
    if(AppSession.getSessionVar(session.id,'provincia') != null && AppSession.getSessionVar(session.id,'canton') != null) {
      fichaNueva.provincia = DPALP.findByCodigo(AppSession.getSessionVar(session.id,'provincia'))
      fichaNueva.canton = DPALP.findByCodigo(AppSession.getSessionVar(session.id,'canton'))
    }
    respond fichaNueva, model:[showing:'false',opname:'CREACIÓN']
  }

  def anyFichacampo() {
    def fichaNueva = new FichaCampo()
    fichaNueva.fecha = new Date()
    fichaNueva.numeroFicha = "(GENERAR)"
    fichaNueva.provincia = DPALP.findByCodigo('11')
    fichaNueva.canton = DPALP.findByCodigo('1104')
    fichaNueva.parroquia = DPALP.findByCodigo('110455')
    fichaNueva.construccion = ''
    fichaNueva.legalizacion = ''
    fichaNueva.confiabilidad = ''

    return fichaNueva
  }

  String randomNumericCode(int times) {
    def codigo = ""
    (1..14).each {
      codigo += "${Math.round(Math.random()*10000)}"
    }
    return codigo
  }

  @Transactional
  def save(FichaCampo fichaCampoInstance) {
    if (fichaCampoInstance == null) {
      notFound()
      return
    }
    fichaCampoInstance.clearErrors()
    def canton = fichaCampoInstance.canton
    fichaCampoInstance.fechaActualizacion = new Date()
    fichaCampoInstance.numeroFicha = canton.indice()
    fichaCampoInstance.validate()
    if (fichaCampoInstance.hasErrors()) {
      respond fichaCampoInstance.errors, view: 'create'
      return
    }
    fichaCampoInstance.save flush: true
    canton.updateIndice()
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.created.message', args: [message(code: 'fichaCampoInstance.label', default: 'FichaCampo'), fichaCampoInstance.id])
        redirect fichaCampoInstance
      }
      '*' { respond fichaCampoInstance, [status: CREATED] }
    }
  }

  def edit(FichaCampo fichaCampoInstance) {
    respond fichaCampoInstance
  }

  @Transactional
  def update(FichaCampo fichaCampoInstance) {
    if (fichaCampoInstance == null) {
      notFound()
      return
    }

    if (fichaCampoInstance.hasErrors()) {
      respond fichaCampoInstance.errors, view: 'edit'
      return
    }

    fichaCampoInstance.save flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'FichaCampo.label', default: 'FichaCampo'), fichaCampoInstance.id])
        redirect fichaCampoInstance
      }
      '*' { respond fichaCampoInstance, [status: OK] }
    }
  }

  @Transactional
  def delete(FichaCampo fichaCampoInstance) {

    if (fichaCampoInstance == null) {
      notFound()
      return
    }

    fichaCampoInstance.delete flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'FichaCampo.label', default: 'FichaCampo'), fichaCampoInstance.id])
        redirect action: "index", method: "GET"
      }
      '*' { render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'fichaCampoInstance.label', default: 'FichaCampo'), params.id])
        redirect action: "index", method: "GET"
      }
      '*' { render status: NOT_FOUND }
    }
  }

  def cantones() {
    def provincia = DPALP.get(params.id)

    render g.select(style:'width:160px;',
                    id:'canton',
                    name:'canton.id',
                    from:DPALP.cantones(provincia),
                    optionKey:'id',
                    required:'',
                    value:'',
                    class:'many-to-one',
                    onchange: remoteFunction(controller:'fichaCampo', action:'parroquias',params:'\'id=\'+escape(this.value)',onSuccess:'ajaxParroquias(data);') )
  }

  def parroquias() {
    def canton = DPALP.get(params.id)

    render g.select(style:'width:160px;',
        id:'parroquia',
        name:'parroquia.id',
        from:DPALP.parroquias(canton),
        optionKey:'id',
        required:'', value:'',class:'many-to-one')
  }

  def editInformacionGeneral() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    render template: "editInformacionGeneral", model:[fichaCampoInstance:fichaCampoInstance, actionName:'EDITING', showing:'false']
  }

  def cancelInformacionGeneral() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    render template: "form", model:[fichaCampoInstance:fichaCampoInstance, actionName:'SHOWING', showing:'true']
  }

  @Transactional
  def updateInformacionGeneral() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    fichaCampoInstance.properties = params
    fichaCampoInstance.fechaActualizacion = new Date()
    fichaCampoInstance.save(flush:true)
    fichaCampoInstance.refresh()
    render template: "form", model:[fichaCampoInstance:fichaCampoInstance, actionName:'SHOWING', showing:'true']
  }

  def editCaractreriasticasPredio() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    render template: "editCaracteristicasPredio", model:[fichaCampoInstance:fichaCampoInstance, actionName:'EDITING', showing:'false']
  }

  def cancelCaractreriasticasPredio() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    render template: "formCaracteristicasPredio", model:[fichaCampoInstance:fichaCampoInstance, actionName:'SHOWING', showing:'true']
  }

  @Transactional
  def updateCaractreriasticasPredio() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    fichaCampoInstance.properties = params
    fichaCampoInstance.save(flush:true)
    render template: "formCaracteristicasPredio", model:[fichaCampoInstance:fichaCampoInstance, actionName:'SHOWING', showing:'true']
  }

  def editRegistroFotografico() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    def photoFilesList = []
    if(fichaCampoInstance && fichaCampoInstance.codigoCatastral && fichaCampoInstance.codigoCatastral != '') {
      photoFilesList = allPhotos(photoDirBase,fichaCampoInstance.codigoCatastral)
    }
    render template: "editRegistroFotografico", model:[fichaCampoInstance:fichaCampoInstance,
                                                       actionName:'EDITING',
                                                       showing:'false',
                                                       claveCatastral: fichaCampoInstance.codigoCatastral,
                                                       photos:photoFilesList]
  }

  def cancelRegistroFotografico() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    def photoFilesList = []
    if(fichaCampoInstance && fichaCampoInstance.codigoCatastral && fichaCampoInstance.codigoCatastral != '') {
      photoFilesList = allPhotos(photoDirBase,fichaCampoInstance.codigoCatastral)
    }
    render template: "formRegistroFotografico", model:[fichaCampoInstance:fichaCampoInstance,
                                                       actionName:'SHOWING',
                                                       showing:'true',
                                                       claveCatastral: fichaCampoInstance.codigoCatastral,
                                                       photos:photoFilesList]
  }

  @Transactional
  def updateRegistroFotografico() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    def photoFilesList = []
    if(fichaCampoInstance && fichaCampoInstance.codigoCatastral && fichaCampoInstance.codigoCatastral != '') {
      photoFilesList = allPhotos(photoDirBase,fichaCampoInstance.codigoCatastral)
    }
    fichaCampoInstance.properties = params
    fichaCampoInstance.save(flush:true)
    render template: "formRegistroFotografico", model:[fichaCampoInstance:fichaCampoInstance,
                                                       actionName:'SHOWING',
                                                       showing:'true',
                                                       claveCatastral: fichaCampoInstance.codigoCatastral,
                                                       photos:photoFilesList]
  }

  def editObservaciones() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    render template: "editObservaciones", model:[fichaCampoInstance:fichaCampoInstance, actionName:'EDITING', showing:'false']
  }

  def cancelObservaciones() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    render template: "formObservaciones", model:[fichaCampoInstance:fichaCampoInstance, actionName:'SHOWING', showing:'true']
  }

  @Transactional
  def updateObservaciones() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    fichaCampoInstance.properties = params
    fichaCampoInstance.save(flush:true)
    render template: "formObservaciones", model:[fichaCampoInstance:fichaCampoInstance, actionName:'SHOWING', showing:'true']
  }

  def createCobertura() {
    def coberturaInstance = new Cobertura()
    def fichaCampoInstance = FichaCampo.get(params.id)
    coberturaInstance.fichaCampo = fichaCampoInstance
    coberturaInstance.tipoUso = null //TipoUso.findByCodigo('agr')
    coberturaInstance.tipoCobertura = null // TipoCobertura.findByTipoUso(coberturaInstance.tipoUso)
    def coberturas0 = TipoCobertura.findAllByTipoUso(coberturaInstance.tipoUso)
    def deshabilitarOtros = true
    coberturaInstance.save(flush: true)
    render template:"createCobertura", model:[fichaCampoInstance:fichaCampoInstance,
                                              coberturaInstance:coberturaInstance,
                                              coberturas0:coberturas0,
                                              deshabilitarOtros:deshabilitarOtros,
                                              showing:'false',actionName:'CREATING']
  }

  @Transactional
  def saveCobertura() {
    Cobertura coberturaInstance = new Cobertura()
    coberturaInstance.properties = params
    if(coberturaInstance.tipoUso == null) {
      render "error0"
    } else
    if(coberturaInstance.venta == null && coberturaInstance.oferta == null && coberturaInstance.arriendo == null) {
      render "error1"
    } else
    if(coberturaInstance.tipoUso.hasCoberturas && coberturaInstance.tipoCobertura == null) {
      render "error2"
    } else
    if( TipoTecnologiaPredominante.findAllByTipoUso(coberturaInstance.tipoUso).size() > 0 && coberturaInstance.tecnologiaPredominante == null ) {
      render "error3"
    } else {
      coberturaInstance.save(flush:true)
      def coberturaInstanceList = Cobertura.findAllByFichaCampo(coberturaInstance.fichaCampo)
      render template:'indexCoberturas',
          model:[ fichaCampoInstance:coberturaInstance.fichaCampo,
                  coberturaInstanceList:coberturaInstanceList,
                  actionName:'SHOWING',
                  showing:'true' ]
    }
  }

  def editCobertura() {
    def coberturaInstance = Cobertura.get(params.id)
    def fichaCampoInstance = coberturaInstance.fichaCampo
    def coberturas0 = TipoCobertura.findAllByTipoUso(coberturaInstance.tipoUso)
    def nmbr = (coberturaInstance.tipoCobertura != null)?coberturaInstance.tipoCobertura.nombre[0..2]:'XXX'
    def deshabilitarOtros = (nmbr != 'OTR')
    render template:"editCobertura",
           model:[fichaCampoInstance:fichaCampoInstance,
                  coberturaInstance:coberturaInstance,
                  coberturas0:coberturas0,
                  showing:'false',
                  deshabilitarOtros:deshabilitarOtros,
                  actionName:'EDITING']
  }

  @Transactional
  def updateCobertura() {
    Cobertura coberturaInstance = Cobertura.get(params.id)
    coberturaInstance.properties = params
    if(coberturaInstance.tipoUso == null) {
      render "error0"
    } else
    if(coberturaInstance.venta == null && coberturaInstance.oferta == null && coberturaInstance.arriendo == null) {
      render "error1"
    } else
    if(coberturaInstance.tipoUso.hasCoberturas && coberturaInstance.tipoCobertura == null) {
      render "error2"
    } else
    if( TipoTecnologiaPredominante.findAllByTipoUso(coberturaInstance.tipoUso).size() > 0 && coberturaInstance.tecnologiaPredominante == null ) {
      render "error3"
    } else  {
      coberturaInstance.save(flush:true)
      def fichaCampoInstance = coberturaInstance.fichaCampo
      def coberturas0 = TipoCobertura.findAllByTipoUso(coberturaInstance.tipoUso)
      def coberturaInstanceList = Cobertura.findAllByFichaCampo(fichaCampoInstance)
      render template:'indexCoberturas',
          model:[fichaCampoInstance:fichaCampoInstance,
                 coberturaInstanceList:coberturaInstanceList,
                 actionName:'SHOWING',
                 showing:'true',
                 coberturas0:coberturas0]
    }

  }

  def cancelCobertura() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    def coberturaInstanceList = Cobertura.findAllByFichaCampo(fichaCampoInstance)
    def coberturas0 = TipoCobertura.findAllByTipoUso(coberturaInstance.tipoUso)
    render template:'indexCoberturas',
        model:[fichaCampoInstance:fichaCampoInstance,
               coberturaInstanceList:coberturaInstanceList,
               actionName:'SHOWING',
               showing:'true',
               coberturas0:coberturas0]
  }

  def cancelEditCobertura() {
    def coberturaInstance = Cobertura.get(params.id)
    def fichaCampoInstance = coberturaInstance.fichaCampo
    def coberturaInstanceList = Cobertura.findAllByFichaCampo(fichaCampoInstance)
    def coberturas0 = TipoCobertura.findAllByTipoUso(coberturaInstance.tipoUso)
    render template:'indexCoberturas',
        model:[fichaCampoInstance:coberturaInstance.fichaCampo,
               coberturaInstanceList:coberturaInstanceList,
               actionName:'SHOWING',
               showing:'true',
               coberturas0:coberturas0]
  }

  @Transactional
  def deleteCobertura() {
    def coberturaInstance = Cobertura.get(params.id)
    def fichaCampoInstance = coberturaInstance.fichaCampo
    coberturaInstance.delete(flush:true)
    def coberturaInstanceList = Cobertura.findAllByFichaCampo(fichaCampoInstance)
    def coberturas0 = TipoCobertura.findAllByTipoUso(coberturaInstance.tipoUso)
    render template:'indexCoberturas',
        model:[fichaCampoInstance:coberturaInstance.fichaCampo,
               coberturaInstanceList:coberturaInstanceList,
               actionName:'SHOWING',
               showing:'true',
               coberturas0:coberturas0]
  }

  def tiposDeCoberturas() {
    TipoUso tipoUso = TipoUso.get(params.id)
    def coberturas0 = TipoCobertura.findAllByTipoUso(tipoUso)
    def coberturaInstance = Cobertura.get(params.coberturaInstanceId)
    render template:"editTiposCobertura", model:[coberturas0:coberturas0,coberturaInstance:coberturaInstance, showing:'false']
  }

  def tiposTecnologiaPredominante() {
    TipoUso tipoUso = TipoUso.get(params.id)
    def lista = TipoTecnologiaPredominante.forTipoUso(tipoUso)
    render template:'selectTecnologiaPredominante', model:[showing:'false', lista:lista]
  }

  def test() {

  }

  def habilitacion =
  [ 'AGRÍCOLA':['superficie':'*','rendimiento':'*','rotacion':'*','cosechasPorAnio':'*','cargaAnimal':'disabled','tecnologiaPredominante':'*','sistemaDeRiego':'*','mecanizacion':'*','venta':'*','oferta':'*','arriendo':'*','precioProducto':'*'],
    'PECUARIO':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'*','tecnologiaPredominante':'*','sistemaDeRiego':'*','mecanizacion':'*','venta':'*','oferta':'*','arriendo':'*','precioProducto':'*'],
    'FORESTAL':['superficie':'*','rendimiento':'*','rotacion':'*','cosechasPorAnio':'*','cargaAnimal':'disabled','tecnologiaPredominante':'*','sistemaDeRiego':'*','mecanizacion':'*','venta':'*','oferta':'*','arriendo':'*','precioProducto':'*'],
    'CONSERVACIÓN':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'ACUACULTURA':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'*','tecnologiaPredominante':'*','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'*'],
    'SIN USO':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    /*'HABITACIONAL':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],*/
    'COMERCIAL':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'TURISMO':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'INDUSTRIA':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'MINERÍA':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'HIDROCARBUROS':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'CEMENTERIO':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'EDUCACIONAL':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'SALUD':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'CULTO':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'RECREACIÓN':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'ESPACIO PÚBLICO':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'CASA COMUNAL':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'INFRAESTRUCTURA ESPECIAL':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'],
    'OTROS':['superficie':'*','rendimiento':'disabled','rotacion':'disabled','cosechasPorAnio':'disabled','cargaAnimal':'disabled','tecnologiaPredominante':'disabled','sistemaDeRiego':'disabled','mecanizacion':'disabled','venta':'*','oferta':'*','arriendo':'*','precioProducto':'disabled'] ]

  def selects = ['tecnologiaPredominante','sistemaDeRiego','mecanizacion']

  def onChangeTipoUso() {
    String element
    if( (params.coberturaInstanceId == '') || (params.coberturaInstanceId == null) ) {
      element = "#intCobertura"
    } else {
      element = "#cob${params.coberturaInstanceId}"
    }
    def generated = ''
    habilitacion["${TipoUso.get(params.tipoUso)}"].each { campo, hab ->
      def tipo = (selects.indexOf(campo) == -1)?'input':'select'
      def jsline = "('${element} $tipo#$campo')${(hab=='disabled')?'.attr(\'disabled\',\'disabled\').addClass(\'desabilitado\')':'.removeAttr(\'disabled\').removeClass(\'desabilitado\')'};\n"
      generated += '$' + jsline
    }
    def jsgenerated = "<script type=\"text/javascript\">\n" +
        "  function onTipoUsoChange() {\n" +
        "    ${generated}\n" +
        "  }\n" +
        "  </script>"
    render jsgenerated
  }

  def onEditCobertura() {
    def coberturaInstance = Cobertura.get(params.coberturaId)
    redirect(action: "onChangeTipoUso", params: [tipoUso: coberturaInstance.tipoUso.id])
  }

  // - - - - - - - - - - - - - - HABITACIONALES - - - - - - - - - - - - - - - //

  def indexHabitacional() {
    def habitacionalInstanceList = Habitacional.findAllByFichaCampo(FichaCampo.get(params.id))
    render template:"indexHabitacionales", model:[habitacionalInstanceList:habitacionalInstanceList,showing:'true']
  }

  def createHabitacional() {
    def fichaCampoInstance = FichaCampo.get(params.id)
    def habitacionalInstance = new Habitacional()
    habitacionalInstance.fichaCampo = fichaCampoInstance

    render template : 'createHabitacional',
           model : [ fichaCampoInstance:fichaCampoInstance,
                     habitacionalInstance:habitacionalInstance,
                     showing:'false']
  }

  @Transactional
  def saveHabitacional() {
    def habitacionalInstance = new Habitacional()
    habitacionalInstance.properties = params
    if(habitacionalInstance.venta == null && habitacionalInstance.oferta == null && habitacionalInstance.arriendo == null) {
      render "error1"
    } else {
      habitacionalInstance.save(flush:true)
      redirect(action: "indexHabitacional", params:[id:params.fichaCampo.id])
    }
  }

  def cancelUpdateHabitacional() {
    def habitacionalInstance = Habitacional.get(params.habitacionalInstanceId)
    redirect(action: "indexHabitacional", params:[id:habitacionalInstance.fichaCampo.id])
  }

  def editHabitacional() {
    def habitacionalInstance = Habitacional.get(params.id)
    render template:"editHabitacional", model:[habitacionalInstance:habitacionalInstance,showing:'false']
  }

  @Transactional
  def updateHabitacional() {
    def habitacionalInstance = Habitacional.get(params.id)
    habitacionalInstance.properties = params
    if(habitacionalInstance.venta == null && habitacionalInstance.oferta == null && habitacionalInstance.arriendo == null) {
      render "error1"
    } else {
      habitacionalInstance.save(flush:true)
      render template:"oneHabitacional", model:[habitacionalInstance:habitacionalInstance,showing:'true']
    }
  }

  @Transactional
  def deleteHabitacional() {
    def habitacionalInstance = Habitacional.get(params.id)
    def fichaCampoId = habitacionalInstance.fichaCampo.id
    habitacionalInstance.delete()
    redirect(action: "indexHabitacional", params:[id:fichaCampoId])
  }

  def ahora() {
    render new Date().format("ddMMMyyyy hh:mm a").toUpperCase()
  }

  // - - - - Geoserver  - - - - -

  def geoserver() {
    def fichaCampoInstance = (params?.id != null)?FichaCampo.get(params.id):null
    def minx = AppSession.getSessionVar(session.id,'cantonminx')
    def miny = AppSession.getSessionVar(session.id,'cantonminy')
    def maxx = AppSession.getSessionVar(session.id,'cantonmaxx')
    def maxy = AppSession.getSessionVar(session.id,'cantonmaxy')
    def gad = AppSession.getSessionVar(session.id,'canton')
    render view:"geoserver", model:[wms:wms,fichaCampoInstance:fichaCampoInstance,minx:minx,miny:miny,maxx:maxx,maxy:maxy,gad:gad]
  }

  def selectPredio() {
    def longitud = params.longitud
    def latitud = params.latitud
    def strqry
    def data
    sql = new Sql(dataSource)
    def myTable = ""

    strqry = "select cc, st_x(st_centroid(geom)), st_y(st_centroid(geom)) from lp.prediogr where ST_Contains(geom,ST_GeomFromText('POINT(' || ${longitud} || ' ' || ${latitud} || ' )',32717))"
    data = sql.firstRow(strqry)
    if(data != null) {
      def cod = data[0]
      def fichaCampoInstance = FichaCampo.findByCodigoCatastral(cod)
      def id = fichaCampoInstance?"${fichaCampoInstance.id}":''
      render(contentType: 'text/json') {['codigoCatastral':data[0],'id':id, info:myTable]}
    } else {
      render(contentType: 'text/json') {['codigoCatastral':'','id':'', info:myTable]}
    }
  }

  def searchPredio() {
    sql = new Sql(dataSource)
    def myTable = ""
    def strqry = "select cc, st_x(st_centroid(geom)), st_y(st_centroid(geom)) from lp.prediogr where cc = '" + params.cc + "'"
    def data = sql.firstRow(strqry)
    if(data != null) {
      def cod = data[0]
      def x = data[1]
      def y = data[2]
      def fichaCampoInstance = FichaCampo.findByCodigoCatastral(cod)
      def id = fichaCampoInstance?"${fichaCampoInstance.id}":''
      render(contentType: 'text/json') {['codigoCatastral':data[0],'id':id, x:x, y:y, info:myTable]}
    } else {
      render(contentType: 'text/json') {['codigoCatastral':'','id':'', x:x, y:y, info:myTable]}
    }
  }

  @Transactional
  def testService() {
    render GeomsService.loadDataToLp('chunchiforweb', 'geom','codigocata')
  }

  def uploadPhoto() {
    println params
    render ""
  }

  def displayPhoto() {
    def path = photoDirBase + "/${params['cc']}/${params['photo']}"
    java.awt.image.BufferedImage originalImage = javax.imageio.ImageIO.read(new File(path));
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    def fileext = path.substring(path.indexOf(".")+1, path.length())
    javax.imageio.ImageIO.write( originalImage, fileext, baos );
    baos.flush();
    byte[] img = baos.toByteArray();
    baos.close();
    response.setHeader('Content-length', img.length.toString())
    response.contentType = "image/"+fileext // or the appropriate image content type
    response.outputStream << img
    response.outputStream.flush()
  }

  def deletePhoto() {
    def path = photoDirBase + "/${params['cc']}/${params['photo']}"
    def deletedPath = photoDirBase + "/${params['cc']}/deleted"
    def deletedDir = new File(deletedPath)
    if(!deletedDir.exists() || !deletedDir.isDirectory())
      deletedDir.mkdir()
    def file2move = new File(path)
    if(file2move.exists() && deletedDir.exists() && deletedDir.isDirectory()) {
      file2move.renameTo(deletedPath + '/' + params['photo'])
      render(contentType: 'text/json') {['msg':"Archivo ${params['photo']} ha sido borrado . . ."]}
    } else {
      render(contentType: 'text/json') {['msg':"Error: Archivo ${params['photo']} NO PUDO se borrado . . ."]}
    }
  }

}
