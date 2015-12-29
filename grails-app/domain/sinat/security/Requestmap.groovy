package sinat.security

import org.springframework.http.HttpMethod

class Requestmap {

	String url
	String configAttribute
	HttpMethod httpMethod

	static mapping = {
		cache true
	}

	static constraints = {
		url blank: false, unique: 'httpMethod'
		configAttribute blank: false
		httpMethod nullable: true
	}

  static def fillData() {
    ['/', '/index', '/index.gsp', '/**/favicon.png','/**/js/**', '/**/css/**', '/**/images/**',
     '/login', '/login.*', '/login/*','/logout', '/logout.*', '/logout/*'].each { url ->
      def rm = sinat.security.Requestmap.findByUrl(url)
      if(rm)
        rm.configAttribute = 'permitAll'
      else {
        rm = new sinat.security.Requestmap()
        rm.url = url
        rm.configAttribute = 'permitAll'
      }
      rm.save(flush:true)
    }
  }

}
