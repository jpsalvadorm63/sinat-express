import sinat.express.DPA
import sinat.express.TipoUso
import sinat.express.ServicioBasico
import sinat.express.Accesibilidad
import sinat.express.TipoCobertura
import sinat.express.TipoTecnologiaPredominante
import sinat.express.TipoSistemaDeRiego
import sinat.express.TipoMecanizacion

class BootStrap {

    //def mailService

    def init = { servletContext ->
      sinat.security.Rol.fillData()
      sinat.security.Usuario.fillData()
      sinat.security.Requestmap.fillData()

      DPA.fillData()
      TipoUso.fillData()
      ServicioBasico.fillData()
      Accesibilidad.fillData()
      TipoCobertura.fillData()
      TipoTecnologiaPredominante.fillData()
      TipoSistemaDeRiego.fillData()
      TipoMecanizacion.fillData()
      /*mailService.sendMail {
        to "salvadorjp63@yahoo.com.au"
        subject "Hello to mutliple recipients"
        html "<b>Hola</b> mundo . . ."
      }*/
    }

    def destroy = {
    }

}
