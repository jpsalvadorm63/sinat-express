package sinat.express

class Habitacional {

    // campos de dominio
    FichaCampo fichaCampo
    String superficie

    // II.b Servicios Básicos
    boolean sbEnergiaElectrica
    boolean sbAguaPotable
    boolean sbAlcantarillado
    boolean sbComunicaciones

    String legalizacion
    Double venta
    Double oferta
    Double arriendo

    // campos de control
    Date fechaCreacion
    Date fechaActualizacion

    static constraints = {
        fichaCampo(nullable:false)
        superficie(nullable:true)
        sbEnergiaElectrica(deafult:false)
        sbAguaPotable(deafult:false)
        sbAlcantarillado(deafult:false)
        sbComunicaciones(deafult:false,nullable:true)
        legalizacion(size:1..16,nullable:false,unique:false,inList:['CON ESCRITURAS','SIN ESCRITURAS'],default:'CON ESCRITURAS')
        venta(nullable:true)
        oferta(nullable:true)
        arriendo(nullable:true)

        fechaCreacion(default: new Date())
        fechaActualizacion(default: new Date())
    }

    static mapping = {
        table   "habitacional"
        version true
        cache   false

        id                    column:"id"
        fichaCampo            column:"fichacampo"
        superficie                 column:"superficie"
        sbEnergiaElectrica    column:"sbenergiaelectrica"
        sbAguaPotable         column:"sbaguapotable"
        sbAlcantarillado      column:"sbalcantarillado"
        sbComunicaciones      column:"sbcomunicaciones"
        legalizacion          column:"legalizacion"
        venta                 column:"venta"
        oferta                column:"oferta"
        arriendo              column:"arriendo"

        fechaCreacion         column:"creacion"
        fechaActualizacion    column:"actualizacion"
    }

    static void fillData() {
    }

    String toString() { superficie }

    String toString2() { "${fichaCampo.codigoCatastral} ${superficie}" }

    static entityName = "Habitacional"

    static entityNamePlural = "Habitacionales"

}
