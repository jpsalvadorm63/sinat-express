package lp

class CcCiruc {

  String  ciruc
  String  cc
  Integer orden
  String  tipo
  String  empresa

  static constraints = {
    ciruc       maxSize: 16, nullable: false
    cc          maxSize: 24, nullable: true
    orden       nullable: true
    tipo        maxSize: 8, inList: ['CI','RUC','OTRO','?'], nullable: false
    empresa     nullable: true, maxSize: 24
  }

  static mapping = {
    table       name:'ccciruc', schema: 'lp'
    version     false
    cache       false

    id          column:'id'
    ciruc       column:'ciruc'
    cc          column:'cc'
    orden       column:'orden'
    tipo        column:'tipo'
    empresa     column:'empresa'
  }

}
