package sinat.security

class Usuario {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

  String toString() {
    return username
  }

  static def fillData() {

    // set initial users
    [  'admin' : [password:'sigTierras',rol:'ROLE_ADMIN'],
       'publico' : [password:'publico',rol:'ROLE_OBSERVER'],
       'público' : [password:'público',rol:'ROLE_OBSERVER']  ].each { username, conf ->
      if(!Usuario.findByUsername(username)) {
        def usuarioInstance = new Usuario()
        usuarioInstance.username = username
        usuarioInstance.password = conf.password
        usuarioInstance.save(flush:true)
      }
    }

    // set user -> rol
    [  'admin' : [password:'sigTierras',rol:'ROLE_ADMIN'],
       'publico' : [password:'publico',rol:'ROLE_OBSERVER'],
       'público' : [password:'público',rol:'ROLE_OBSERVER']  ].each { username, conf ->
      def usuarioInstance = Usuario.findByUsername(username)
      def rolInstance = Rol.findByAuthority(conf.rol)
      if(rolInstance) {
        if(!UsuarioRol.exists(usuarioInstance.id,rolInstance.id)) {
          UsuarioRol.create(usuarioInstance,rolInstance,true)
        }
      } else {
        // TODO: reportar error:
      }
    }

  }

}
