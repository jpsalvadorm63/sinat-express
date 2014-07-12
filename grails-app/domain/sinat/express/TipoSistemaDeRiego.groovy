package sinat.express

class TipoSistemaDeRiego {

  String codigo
  String nombre

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {

    codigo(size:1..8,nullable:false,unique:true)
    nombre(size:1..64,nullable:false,unique:true)

    fechaCreacion(default: new Date())
    fechaActualizacion(default: new Date())
  }

  static mapping = {
    table "tiposistemaderiego"
    version true
    cache true

    id                 column: "id"
    codigo				     column:"codigo"
    nombre 				     column:"nombre"

    fechaCreacion      column: "creacion"
    fechaActualizacion column: "actualizacion"
  }

  static void fillData() {
    Map dpaSistemaRiego = ['got':'Goteo','asp':'Aspersión','gra':'Gravedad','bom':'Bomba','otr':'Otros','no':'No tiene']
    dpaSistemaRiego.each {dpasr ->
      String codigo = dpasr.key
      String nombre = dpasr.value
      if(!TipoSistemaDeRiego.findByCodigo(codigo)) {
        TipoSistemaDeRiego tipoSistemaDeRiego = new TipoSistemaDeRiego()
        tipoSistemaDeRiego.codigo = codigo
        tipoSistemaDeRiego.nombre = nombre
        tipoSistemaDeRiego.fechaCreacion = new Date()
        tipoSistemaDeRiego.fechaActualizacion = new Date()
        tipoSistemaDeRiego.version = 1
        tipoSistemaDeRiego.save(flush:true)
      }
    }
  }

  String toString() { nombre }
  String toString2() { codigo + ' ' + nombre }

  static entityName = "Tipo Sistema De Riego"

  static entityNamePlural = "Tipo Sistema De Riego"

}
