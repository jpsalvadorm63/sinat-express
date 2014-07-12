package sinat.security

class Rol {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

  String toString() {
    return authority
  }

  static def fillData() {
    ['ROLE_ADMIN','ROLE_MAINUSER','ROLE_USER','ROLE_OBSERVER'].each { rol ->
      if(!Rol.findByAuthority(rol)) {
        def rolInstance = new Rol()
        rolInstance.authority = rol
        rolInstance.save(flush:true)
      }
    }
  }


}
