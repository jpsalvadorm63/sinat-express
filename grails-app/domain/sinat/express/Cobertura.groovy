package sinat.express

class Cobertura {

  // campos de dominio
  FichaCampo fichaCampo
  TipoUso tipoUso
  TipoCobertura tipoCobertura
  Double superficie
  String rendimiento
  Integer cargaAnimal
  String rotacion
  Integer cosechasPorAnio
  TipoTecnologiaPredominante tecnologiaPredominante
  String sistemaDeRiego
  String mecanizacion
  Double venta
  Double oferta
  Double arriendo
  Double precioProducto
  String otrosCultivo

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {
    fichaCampo(nullable:false)
    tipoUso(nullable: false)
    tipoCobertura(nullable:true)
    superficie(nullable:true)
    rendimiento(nullable:true, size: 0..64)
    cargaAnimal(nullable:true, default:0)
    rotacion(nullable:true, size: 0..128)
    cosechasPorAnio(nullable:true, default:0)
    tecnologiaPredominante(nullable:true)
    sistemaDeRiego(nullable:true, inList: ['GOTEO','ASPERCIÓN','GRAVEDAD','BOMBA','OTROS','NO TIENE'],default:'')
    mecanizacion(nullable:true, inList: ['FACIL','MODERADA','NO MECANIZABLE','NO TIENE'],default:'')
    venta(nullable:true)
    oferta(nullable:true)
    arriendo(nullable:true)
    precioProducto(nullable:true)
    otrosCultivo(nullable:true,size: 0..64)

    fechaCreacion(default: new Date())
    fechaActualizacion(default: new Date())
  }

  static mapping = {
    table "cobertura"
    version true
    cache false
    sort "id"

    id column: "id"
    // Mapping de ampos de dominio
    fichaCampo             column: 'fichacampo'
    tipoUso                column: 'tipouso_id'
    tipoCobertura          column: 'tipocobertura_id'
    superficie             column: 'superficie'
    rendimiento            column: 'rendimiento'
    cargaAnimal            column: 'cargaanimal'
    rotacion               column: 'rotacion'
    cosechasPorAnio        column: 'cosechasporanio'
    tecnologiaPredominante column: 'tecnologiapredominante_id'
    sistemaDeRiego         column: 'sistemaderiego'
    mecanizacion           column: 'mecanizacion'
    venta                  column: 'venta'
    oferta                 column: 'oferta'
    arriendo               column: 'arriendo'
    precioProducto         column: 'precioproducto'
    otrosCultivo           column: 'otroscultivos'

    fechaCreacion          column: "creacion"
    fechaActualizacion     column: "actualizacion"
  }

  static void fillData() {

  }

  String toString() { tipoUso?.nombre + ' - ' + tipoCobertura?.nombre }

  String toString2() { "uso: ${tipoUso?.nombre}, cobertura: ${tipoCobertura?.nombre}" }

  static entityName = "Cobertura"

  static entityNamePlural = "Coberturas"

}
