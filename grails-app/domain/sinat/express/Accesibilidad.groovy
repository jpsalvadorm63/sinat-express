package sinat.express

class Accesibilidad {

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
    table "accesibilidad"
    version true
    cache true
    sort nombre:"asc"

    id                 column: "id"
    codigo 				     column:"codigo"
    nombre 			       column:"nombre"

    fechaCreacion      column: "creacion"
    fechaActualizacion column: "actualizacion"
  }

  static void fillData() {
    Map dpaAccesibilidad = ['asf':'Asfalto','las':'Lastrado',
        'ver':'De verano','emp':'Empedrada','trr':'Tierra','hrr':'Herradura',
        'ptnl':'Peatonal','acu':'Acuática','aer':'Aérea','fer':'Ferrea','nin':'Ninguna']
    dpaAccesibilidad.each { dpaa ->
      String codigo = dpaa.key
      String nombre = dpaa.value
      if(!Accesibilidad.findByCodigo(codigo)) {
        Accesibilidad accesibilidad = new Accesibilidad()
        accesibilidad.codigo = codigo
        accesibilidad.nombre = nombre
        accesibilidad.fechaCreacion = new Date()
        accesibilidad.fechaActualizacion = new Date()
        accesibilidad.version = 1
        accesibilidad.save(flush:true)
      }
    }
  }

  String toString() { nombre }

  String toString2() { nombre + ' (' + codigo + ')' }

  static entityName = "Accesibilidad"

  static entityNamePlural = "Accesibilidades"

}
