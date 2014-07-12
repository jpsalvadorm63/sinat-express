package sinat.express

class TipoMecanizacion {

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
    table "tipomecanizacion"
    version true
    cache true

    id                  column: "id"
    codigo 				      column:"codigo"
    nombre 				      column:"nombre"

    fechaCreacion       column: "creacion"
    fechaActualizacion  column: "actualizacion"
  }

  static void fillData() {
    Map dpaMecanizacion = ['fac':'Fácil','mod':'Moderada','nom':'No mecanizable']
    dpaMecanizacion.each {dpam ->
      String codigo = dpam.key
      String nombre = dpam.value
      if(!TipoMecanizacion.findByCodigo(codigo)) {
        TipoMecanizacion tipoMecanizacion = new TipoMecanizacion()
        tipoMecanizacion.codigo = codigo
        tipoMecanizacion.nombre = nombre
        tipoMecanizacion.fechaCreacion = new Date()
        tipoMecanizacion.fechaActualizacion = new Date()
        tipoMecanizacion.version = 1
        tipoMecanizacion.save(flush:true)
      }
    }
  }

  String toString() { nombre }
  String toString2() { codigo + ' ' + nombre }

  static entityName = "Tipo Mecanizacion"

  static entityNamePlural = "Tipo Mecanizacion"

}
