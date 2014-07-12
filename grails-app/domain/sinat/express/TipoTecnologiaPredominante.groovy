package sinat.express

class TipoTecnologiaPredominante {

  String codigo
  String nombre
  TipoUso tipoUso

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {

    codigo(size:1..8,nullable:false,unique:true)
    nombre(size:1..64,nullable:false,unique:false)
    tipoUso(nullable:true)

    fechaCreacion(default:new Date())
    fechaActualizacion(default:new Date())
  }

  static mapping = {
    table   "tipotecnologiapredominante"
    version true
    cache   true
    sort    nombre:"asc"

    id                  column:"id"
    codigo 				      column:"codigo"
    nombre 				      column:"nombre"
    tipoUso             column:"tipouso"

    fechaCreacion 		  column:"creacion"
    fechaActualizacion  column:"actualizacion"
  }

  static void fillData() {
    fillDataWidth('agr',['tecn':'Tecnificado','trad':'Tradicional','subs':'Subsistencia','huer':'Huerto Familiar'])
    fillDataWidth('pec',['int':'Intensivo',	'ext':'Extensivo',	'subsi':'Subsistencia'])
    fillDataWidth('for',['ind':'Industrial', 'art':'Artesanal'])
    fillDataWidth('acua',['aint':'Intensivo',	'aext':'Extensivo'])
  }

  static void fillDataWidth(tipoUsoStr,arr)
  {
    def tipoUso = TipoUso.findByCodigo(tipoUsoStr)
    Map dpaTecnologia = arr
    dpaTecnologia.each {dpat ->
      String codigo = dpat.key
      String nombre = dpat.value
      if(!TipoTecnologiaPredominante.findByCodigo(codigo)) {
        TipoTecnologiaPredominante tipoTecnologiaPredominante = new TipoTecnologiaPredominante()
        tipoTecnologiaPredominante.codigo = codigo
        tipoTecnologiaPredominante.nombre = nombre.toUpperCase()
        tipoTecnologiaPredominante.tipoUso = tipoUso
        tipoTecnologiaPredominante.fechaCreacion = new Date()
        tipoTecnologiaPredominante.fechaActualizacion = new Date()
        tipoTecnologiaPredominante.version = 1
        tipoTecnologiaPredominante.save(flush:true)
      }
    }
  }

  static def forTipoUso(TipoUso tipoUso) {
    TipoTecnologiaPredominante.findAllByTipoUso(tipoUso)
  }

  String toString() { nombre }
  String toString2() { codigo + ' ' + nombre }

  static entityName = "Tipo Tecnologia Predominante"

  static entityNamePlural = "Tipo Tecnologia Predominante"

}
