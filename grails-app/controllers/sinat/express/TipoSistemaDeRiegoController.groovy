package sinat.express


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TipoSistemaDeRiegoController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond TipoSistemaDeRiego.list(params), model: [tipoSistemaDeRiegoInstanceCount: TipoSistemaDeRiego.count()]
  }

}
