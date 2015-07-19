package lp

class Propietario {

  String codigoCatastral
  String propietario
  String ciruc
  String tipoCiruc
  String contacto
  String estadoCivil
  Integer anioNacimiento
  Integer aniosPosesion
  String relacionadoCon
  String tipoRelacion
  Boolean conviveConConyuge
  Boolean documentoVerificado
  Integer orden
  String empresa

  static constraints = {
    codigoCatastral      nullable: false, maxSize: 24
    propietario          nullable: true, maxSize: 96
    ciruc                nullable: true, maxSize: 24
    tipoCiruc            nullable: true, maxSize: 24, inList:['CI','RUC','PASS','OTRO']
    contacto             nullable: true, maxSize: 24
    estadoCivil          nullable: true, inList:['EN UNION DE HECHO', 'CASADO','SOLTERO', 'VIUDO', 'DIVORCIADO'], maxSize: 24
    anioNacimiento       nullable: true
    aniosPosesion        nullable: true
    relacionadoCon       nullable: true, maxSize: 24
    tipoRelacion         nullable: true, maxSize: 24, inList:['cónyuge','representante legal']
    conviveConConyuge    nullable: true
    documentoVerificado  nullable: true
    orden                nullable: true
    empresa              nullable: true, inList:['inypsa','nip','seresco','?'], maxSize: 24
  }



  static mapping = {
    table              name:'propietario', schema: 'lp'
    version            false
    cache              false

    id                       column:'id'
    codigoCatastral          column:'cc'
    propietario              column:'propietario'
    ciruc                    column:'ciruc'
    tipoCiruc                column:'tipociruc'
    contacto                 column:'contacto'
    estadoCivil              column:'estadocivil'
    anioNacimiento           column:'anionacimiento'
    aniosPosesion            column:'aniosposesion'
    relacionadoCon           column:'relacionadocon'
    tipoRelacion             column:'tiporelacion'
    conviveConConyuge        column:'conviveconconyuge'
    documentoVerificado      column:'documentoverificado'
    orden                    column:'orden'
    empresa                  column:'empresa'
  }

  // id,cc,propietario,ciruc,tipociruc,contacto,anionacimientoaniosposesion,relacionadocon,tiporelacion,conviveconconyuge,empresa,documentoverificado

}
