package util

class AppSession {

  String sessionId
  String nombre
  String valor
  Date   fechaCreacion
  Date   fechaActualizacion

  static constraints = {
    sessionId          nullable:false, minSize: 1, maxSize: 254
    nombre             nullable:true, maxSize: 254
    valor              nullable:true, maxSize: 254
    fechaCreacion      nullable:true, default: new Date()
    fechaActualizacion nullable:true, default: new Date()
  }

  static mapping = {
    table name:'appsession'
    version true
    cache true

    id                 column: "id"
    nombre             column: "nombre"
    valor              column: "valor"
    fechaCreacion      column: "creacion"
    fechaActualizacion column: "actualizacion"
  }

  static void setSessionVar(sessionId,nombre,valor) {
    def myVar = AppSession.findBySessionIdAndNombre(sessionId,nombre)
    if(myVar == null) {
      myVar = new AppSession()
      myVar.fechaCreacion = new Date()
      myVar.sessionId = sessionId
      myVar.nombre = nombre
      myVar.valor = valor
      myVar.version = 1
      myVar.fechaActualizacion = new Date()
      myVar.save(flush: true)
    } else {
      myVar.valor = valor
      myVar.fechaActualizacion = new Date()
      myVar.save(flush: true)
    }
  }

  static String getSessionVar(sessionId,nombre) {
    def myVar = AppSession.findBySessionIdAndNombre(sessionId,nombre)
    if(myVar != null)
      return myVar.valor
    else
      return null
  }

  static void deleteSessionVar(sessionId,nombre) {
    AppSession.findAllBySessionIdAndNombre(sessionId,nombre).each {
      it.delete(flush: true)
    }
  }

  static void deleteAllSessionVar(sessionId) {
    AppSession.findAllBySessionId(sessionId).each {
      it.delete(flush: true)
    }
  }

}
