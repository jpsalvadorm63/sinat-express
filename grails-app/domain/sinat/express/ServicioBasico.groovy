package sinat.express

class ServicioBasico {
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
    table "serviciobasico"
    version true
    cache true

    id                 column: "id"
    codigo 					   column:"codigo"
    nombre 					   column:"nombre"

    fechaCreacion      column: "creacion"
    fechaActualizacion column: "actualizacion"
  }

  static void fillData() {
    Map dpaServicoBasico = ['ele':'Energía Electrica','apo':'Agua Potable','alc':'Alcantarillado','tel':'Teléfono']
    dpaServicoBasico.each {dpas->
      String codigo = dpas.key
      String nombre = dpas.value
      if(!ServicioBasico.findByCodigo(codigo)) {
        ServicioBasico servicio = new ServicioBasico()
        servicio.codigo = codigo
        servicio.nombre = nombre
        servicio.fechaCreacion = new Date()
        servicio.fechaActualizacion = new Date()
        servicio.version = 1
        servicio.save(flush:true)
      }
    }
  }

  String toString() { 'que es esto ?' }

  String toString2() { 'que va aquí ?' }

  static entityName = "ServiciosBasicos"

  static entityNamePlural = "ServiciosBasicos"

}
