import externos.DPALP
import sinat.express.TipoUso
import sinat.express.ServicioBasico
import sinat.express.Accesibilidad
import sinat.express.TipoCobertura
import sinat.express.TipoTecnologiaPredominante
import sinat.express.TipoSistemaDeRiego
import sinat.express.TipoMecanizacion
import sinat.security.Rol
import sinat.security.Usuario
import sinat.security.Requestmap

class BootStrap {

    //def mailServic

    def init = { servletContext ->
      Rol.fillData()
      Usuario.fillData()
      Requestmap.fillData()

      DPALP.fillData()
      TipoUso.fillData()
      ServicioBasico.fillData()
      Accesibilidad.fillData()
      TipoCobertura.fillData()
      TipoTecnologiaPredominante.fillData()
      TipoSistemaDeRiego.fillData()
      TipoMecanizacion.fillData()
      /*
      mailService.sendMail {
        to "salvadorjp63@yahoo.com.au"
        subject "Hello to mutliple recipients"
        html "<b>Hola</b> mundo . . ."
      }
      */
    }

    def destroy = {
    }

}
