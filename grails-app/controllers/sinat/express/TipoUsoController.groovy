package sinat.express

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TipoUsoController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 24, 100)
    respond TipoUso.list(params), model: [tipoUsoInstanceCount: TipoUso.count()]
  }

  def coberturas(Integer max) {
    params.max = Math.min(max ?: 16, 100)
    def tipoUsoInstance = TipoUso.get(params.id)
    def tipoCoberturaInstanceCount = TipoCobertura.findAllByTipoUso(tipoUsoInstance).size()
    def tipoCoberturaInstanceList = TipoCobertura.findAllByTipoUso(tipoUsoInstance,params)
    render template:"coberturas", model:[tipoCoberturaInstanceCount:tipoCoberturaInstanceCount,
                                         tipoCoberturaInstanceList:tipoCoberturaInstanceList,
                                         tipoUsoInstance:tipoUsoInstance]
  }

}
