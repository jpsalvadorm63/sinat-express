package sinat.express

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HabitacionalController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond Habitacional.list(params), model: [habitacionalInstanceCount: Habitacional.count()]
  }

  def show(Habitacional habitacionalInstance) {
    respond habitacionalInstance
  }

  def create() {
    respond new Habitacional(params)
  }

  @Transactional
  def save(Habitacional habitacionalInstance) {
    if (habitacionalInstance == null) {
      notFound()
      return
    }

    if (habitacionalInstance.hasErrors()) {
      respond habitacionalInstance.errors, view: 'create'
      return
    }

    habitacionalInstance.save flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.created.message', args: [message(code: 'habitacionalInstance.label', default: 'Habitacional'), habitacionalInstance.id])
        redirect habitacionalInstance
      }
      '*' { respond habitacionalInstance, [status: CREATED] }
    }
  }

  def edit(Habitacional habitacionalInstance) {
    respond habitacionalInstance
  }

  @Transactional
  def update(Habitacional habitacionalInstance) {
    if (habitacionalInstance == null) {
      notFound()
      return
    }

    if (habitacionalInstance.hasErrors()) {
      respond habitacionalInstance.errors, view: 'edit'
      return
    }

    habitacionalInstance.save flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'Habitacional.label', default: 'Habitacional'), habitacionalInstance.id])
        redirect habitacionalInstance
      }
      '*' { respond habitacionalInstance, [status: OK] }
    }
  }

  @Transactional
  def delete(Habitacional habitacionalInstance) {

    if (habitacionalInstance == null) {
      notFound()
      return
    }

    habitacionalInstance.delete flush: true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Habitacional.label', default: 'Habitacional'), habitacionalInstance.id])
        redirect action: "index", method: "GET"
      }
      '*' { render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'habitacionalInstance.label', default: 'Habitacional'), params.id])
        redirect action: "index", method: "GET"
      }
      '*' { render status: NOT_FOUND }
    }
  }

}
