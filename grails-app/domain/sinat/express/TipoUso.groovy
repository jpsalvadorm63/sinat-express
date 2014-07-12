package sinat.express

class TipoUso {

  String codigo
  String nombre
  Boolean hasCoberturas

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {
    codigo(size:1..8,nullable:false,unique:true)
    nombre(size:1..64,nullable:false,unique:true)
    hasCoberturas(nullable: true)

    fechaCreacion(default:new Date())
    fechaActualizacion(default:new Date())
  }

  static mapping = {
    table   "tipouso"
    version true
    cache   true
    sort    nombre:"asc"

    id                  column:"id"
    codigo 				      column:"codigo"
    nombre 				      column:"nombre"
    hasCoberturas       column:"hascoberturas"

    fechaCreacion 		  column:"creacion"
    fechaActualizacion  column:"actualizacion"
  }

  static void fillData() {
    Map dpaTipoUsos = ['hab':'Habitacional',
                       'agr':'Agrícola',
                       'pec':'Pecuario',
                       'for':'Forestal',
                       'acua':'Acuacultura',
                       'cons':'Conservacion',
                       'com':'Comercial',
                       'tur':'Turismo',
                       'ind':'Industria',
                       'min':'Minería',
                       'hid':'Hidrocarburos',
                       'cem':'Cementerio',
                       'edu':'Educacional',
                       'sal':'Salud',
                       'cul':'Culto',
                       'rec':'Recreación',
                       'esp':'Espacio público',
                       'cas':'Casa comunal',
                       'inf':'Infraestructura especial',
                       'otr':'Otros',
                       'sin':'Sin uso']
    dpaTipoUsos.each { dpatu ->
      String codigo = dpatu.key
      String nombre = dpatu.value

      if(!TipoUso.findByCodigo(codigo)) {
        TipoUso tipouso = new TipoUso()
        tipouso.codigo = codigo
        tipouso.nombre = nombre
        tipouso.hasCoberturas = false

        tipouso.version = 1
        tipouso.fechaCreacion = new Date()
        tipouso.fechaActualizacion = new Date()

        tipouso.save(flush:true)
      }
    }
  }

  String toString() { nombre }
  String toString2() { codigo + ' ' + nombre }

  static entityName = "Tipo de Uso"

  static entityNamePlural = "Tipos de Uso"

}
