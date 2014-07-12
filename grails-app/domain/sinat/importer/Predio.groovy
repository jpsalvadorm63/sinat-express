package sinat.importer

class Predio {

  String origen
  String codCatastral
  String codAnterior
  String sector
  String nombrePredio
  String tieneNombre
  String telefono
  String tipoTelefono
  String recibeNotificaciones
  String calleNotificaciones
  String numeroNotificacion
  String pisoNotificacion
  String ciudadNotificacion
  String cantonNotificaciones
  String parroquiaNotificacion
  String titulo
  String observaciones

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {
    origen(nullable:false, size:0..16)
    codCatastral(nullable:false, size:0..16)
    codAnterior(nullable:true, size:0..24)
    sector(nullable:true, size:0..64)
    nombrePredio(nullable:true, size:0..64)
    tieneNombre(nullable:true, size:0..16)
    telefono(nullable:true, size:0..16)
    tipoTelefono(nullable:true, size:0..16)
    recibeNotificaciones(nullable:true, size:0..16)
    calleNotificaciones(nullable:true, size:0..64)
    numeroNotificacion(nullable:true, size:0..16)
    pisoNotificacion(nullable:true, size:0..16)
    ciudadNotificacion(nullable:true, size:0..64)
    cantonNotificaciones(nullable:true, size:0..64)
    parroquiaNotificacion(nullable:true, size:0..64)
    titulo(nullable:true, size:0..16)
    observaciones(nullable:true, size:0..2048)

    fechaCreacion(default: new Date())
    fechaActualizacion(default: new Date())
  }

  static mapping = {
    table "predio"
    version true
    cache false

    id column: "id"
    // Mapping de ampos de dominio
    observaciones type:'text'
    fechaCreacion column: "creacion"
    fechaActualizacion column: "actualizacion"
  }

  static void fillData() {

  }

  String toString() { 'que es esto ?' }

  String toString2() { 'que va aquí ?' }

  static entityName = "Predio"

  static entityNamePlural = "Predio"

}
