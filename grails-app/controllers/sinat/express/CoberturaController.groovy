package sinat.express


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CoberturaController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond Cobertura.list(params), model: [coberturaInstanceCount: Cobertura.count()]
  }

  def show(Cobertura coberturaInstance) {
    respond coberturaInstance
  }

  def create() {
    respond new Cobertura(params)
  }

  @Transactional
  def save(Cobertura coberturaInstance) {
    if (coberturaInstance == null) {
      notFound()
      return
    }

    if (coberturaInstance.hasErrors()) {
      respond coberturaInstance.errors, view: 'create'
      return
    }

    coberturaInstance.save flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.created.message', args: [message(code: 'coberturaInstance.label', default: 'Cobertura'), coberturaInstance.id])
        redirect coberturaInstance
      }
      '*' { respond coberturaInstance, [status: CREATED] }
    }
  }

  def edit(Cobertura coberturaInstance) {
    respond coberturaInstance
  }

  @Transactional
  def update(Cobertura coberturaInstance) {
    if (coberturaInstance == null) {
      notFound()
      return
    }

    if (coberturaInstance.hasErrors()) {
      respond coberturaInstance.errors, view: 'edit'
      return
    }

    coberturaInstance.save flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'Cobertura.label', default: 'Cobertura'), coberturaInstance.id])
        redirect coberturaInstance
      }
      '*' { respond coberturaInstance, [status: OK] }
    }
  }

  @Transactional
  def delete(Cobertura coberturaInstance) {

    if (coberturaInstance == null) {
      notFound()
      return
    }

    coberturaInstance.delete flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cobertura.label', default: 'Cobertura'), coberturaInstance.id])
        redirect action: "index", method: "GET"
      }
      '*' { render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'coberturaInstance.label', default: 'Cobertura'), params.id])
        redirect action: "index", method: "GET"
      }
      '*' { render status: NOT_FOUND }
    }
  }
}
