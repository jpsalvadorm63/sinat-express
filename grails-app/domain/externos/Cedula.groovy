package externos

class Cedula {

  String ciruc
  String codigoError
  String tipo
  String nombre
  String condicionCedulado
  Date   fechaNacimiento
  String lugarNacimiento
  String estadoCivil
  String instruccion
  String genero
  String conyuge

  static constraints = {
    ciruc              maxSize: 16, nullable: false
    codigoError        maxSize: 16, nullable: true
    tipo               maxSize: 8, inList: ['CI','RUC','OTRO','?'], nullable: false
    nombre             maxSize: 128, nullable: true
    condicionCedulado  maxSize: 32, nullable: true
    fechaNacimiento    nullable: true
    lugarNacimiento    maxSize: 128, nullable: true
    estadoCivil        maxSize: 32, nullable: true
    instruccion        maxSize: 32, nullable: true
    genero             maxSize: 32, nullable: true
    conyuge            maxSize: 128, nullable: true
  }

  static mapping = {
    table              name:'cedula', schema: 'entidades_externas'
    version            false
    cache              false

    id                 column:'id'
    ciruc              column:'ciruc', index:'cirucidx'
    codigoError        column:'codigoerror'
    tipo               column:'tipo'
    nombre             column:'nombre'
    condicionCedulado  column:'condicioncedulado'
    fechaNacimiento    column:'fechanacimiento'
    lugarNacimiento    column:'lugarnacimiento'
    estadoCivil        column:'estadocivil'
    instruccion        column:'instruccion'
    genero             column:'genero'
    conyuge            column:'comyuge'
  }

}