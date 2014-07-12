package sinat.importer

class FcController {

  def ingresaFicha() {
    String origen = params.origen
    String codCatastral = params.codCatastral
    def msge = '?'
    def busqueda = Predio.findByOrigenAndCodCatastral(origen,codCatastral)
    if(!busqueda) {
      def predio = new Predio(params)
      predio.properties = params
      predio.fechaCreacion = new Date()
      predio.fechaActualizacion = new Date()
      predio.save(flush: true)
      if(predio.hasErrors()) {
        msge = "Error: ${params}"
        predio.errors.each {
          println "${params.codCatastral} ${it}"
        }
      } else {
        msge = '0K'
      }
    } else {
      busqueda.properties = params
      busqueda.fechaActualizacion = new Date()
      busqueda.save(flush: true)
      msge = 'updated'
    }
    render "${params.codCatastral} ${msge}"
  }
}
