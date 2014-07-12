package sinat.express

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TipoCoberturaController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 24, 100)
    respond TipoCobertura.list(params), model: [tipoCoberturaInstanceCount: TipoCobertura.count()]
  }

}
