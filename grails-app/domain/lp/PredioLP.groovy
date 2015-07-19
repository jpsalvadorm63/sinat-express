package lp

import externos.DPALP

class PredioLP {

  String  codigoCatastral
  String  codigoCatastralAnterior
  String  tipo
  String  estado
  String  empresa
  String  sector
  DPALP   dpalp
  DPALP   parroquia
  Double  superficie
  Integer anioPosesion
  Boolean areaMae
  Boolean esPublico
  String  observaciones
  // legal --
  String  conTitulo
  Boolean tituloVerificado
  String  requierePerfeccionamientoLegal
  String  formaAdquicision
  Boolean posesionMayorA5Anios
  // riego --
  String disponibilidadRiego
  String riegoXGravedad
  String riegoXAspersión
  String riegoXGoteo
  String riegoXBombeo
  String riegoXOtro


  static constraints = {
    codigoCatastral                nullable: false,  maxSize: 24, unique: true
    codigoCatastralAnterior         nullable: true,  maxSize: 32, unique: false
    tipo                            nullable: false, inList:['rural','urbano','pec'], default:'rural', maxSize: 24
    estado                          nullable: true,  inList:['activo','no activo','historico','?'], maxSize: 24
    empresa                         nullable: true,  inList:['inypsa','nip','seresco','?'], maxSize: 24
    sector                          nullable: true,  maxSize: 64
    dpalp                           nullable: true
    parroquia                       nullable: true
    superficie                      nullable: true
    anioPosesion                    nullable: true
    areaMae                         nullable: true
    esPublico                       nullable: true
    observaciones                   nullable: true,  maxSize:254
    // legal --
    conTitulo                       nullable: true,  maxSize:16
    tituloVerificado                nullable: true
    requierePerfeccionamientoLegal  nullable: true,  maxSize:32
    formaAdquicision                nullable: true,  maxSize:64
    posesionMayorA5Anios            nullable: true
    // riego --
    disponibilidadRiego             nullable: true,  maxSize:32
    riegoXGravedad                  nullable: true,  maxSize:8
    riegoXAspersión                 nullable: true,  maxSize:8
    riegoXGoteo                     nullable: true,  maxSize:8
    riegoXBombeo                    nullable: true,  maxSize:8
    riegoXOtro                      nullable: true,  maxSize:8
  }

  static mapping = {
    table              name:'prediolp', schema: 'lp'
    version            false
    cache              false

    id                             column:'id'
    codigoCatastral                column:'codigocatastral', index: 'ccidx'
    codigoCatastralAnterior        column:'codigocatastralanterior', index: 'ccidx2'
    tipo                           column:'tipo'
    estado                         column:'estado'
    empresa                        column:'empresa'
    sector                         column:'sector'
    dpalp                          column:'dpalp_id'
    parroquia                      column:'parroquia_id'
    superficie                     column:'superficie'
    anioPosesion                   column:'aniosposesion'
    areaMae                        column:'areamae'
    esPublico                      column:'espublico'
    observaciones                  column:'observaciones'
    // legal --
    conTitulo                      column:'contitulo'
    tituloVerificado               column:'tituloverificado'
    requierePerfeccionamientoLegal column:'requiereperfeccionamientolegal'
    formaAdquicision               column:'formadquicision'
    posesionMayorA5Anios           column:'posesionmayora5anios'
    // riego --
    disponibilidadRiego            column:'disponibilidadriego'
    riegoXGravedad                 column:'riegoxgravedad'
    riegoXAspersión                column:'riegoxaspersion'
    riegoXGoteo                    column:'riegoxgoteo'
    riegoXBombeo                   column:'riegoxbombeo'
    riegoXOtro                     column:'riegoxotro'
  }

}
