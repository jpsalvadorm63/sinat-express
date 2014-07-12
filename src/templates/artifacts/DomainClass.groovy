@artifact.package@class @artifact.name@ {

  // campos de dominio

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {


    fechaCreacion(default:new Date())
    fechaActualizacion(default:new Date())
  }

  static mapping = {
    table   "????"
    version true
    cache   false

    id                  column:"id"
    // Mapping de ampos de dominio

    fechaCreacion 		  column:"creacion"
    fechaActualizacion  column:"actualizacion"
  }

  static void fillData() {

  }

  String toString() { 'que es esto ?' }
  String toString2() { 'que va aquí ?' }

  static entityName = "@artifact.name@"

  static entityNamePlural = "@artifact.name@"

}
