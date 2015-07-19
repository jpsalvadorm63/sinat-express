package lp

class Migrador {
  String codigoCatastral
  String tipo
  String empresa
  String migrar
  String migrado

  static constraints = {
    codigoCatastral nullable: false, maxSize: 24
    tipo inList:['predio','propietario'], maxSize: 24
    empresa inList:['inypsa','nip','ceresco'],maxSize: 24
    migrar maxSize: 254, nullable: true
    migrado inList:['si','no'], maxSize: 24
  }

  static mapping = {
    table name: 'migrador', schema: 'lp'
    version false
    cache false

    id              column: 'id'
    codigoCatastral column: 'codigocatastral', index: 'migradorccidx'
    tipo            column: 'tipo'
    empresa         column: 'empresa'
    migrar          column: 'migrar'
    migrado         column: 'migrado'
  }

}
