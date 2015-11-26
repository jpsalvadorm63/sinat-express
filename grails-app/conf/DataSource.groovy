dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.postgresql.Driver"
    // dialect = org.hibernate.dialect.PostgreSQLDialect
    dialect = net.sf.hibernate.dialect.PostgreSQLDialect
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {

    development {
        dataSource {
            dbCreate = "update"
            url="jdbc:postgresql://localhost:5432/ficdb"
            username = "postgres"
            password = "sqlgis1606"
        }
    }

    test {
      dataSource {
        dbCreate = "update"
        url="jdbc:postgresql://172.21.1.25:5432/ficdb"
        username = "postgres"
        password = "postgres"
      }
    }

    production {
      dataSource {
        dbCreate = "update"
        url="jdbc:postgresql://127.0.0.1:5432/ficdb"
        username = "postgres"
        password = "sqlgis2101"
        properties {
          // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
          jmxEnabled = true
          initialSize = 5
          maxActive = 50
          minIdle = 5
          maxIdle = 25
          maxWait = 10000
          maxAge = 10 * 60000
          timeBetweenEvictionRunsMillis = 5000
          minEvictableIdleTimeMillis = 60000
          validationQueryTimeout = 3
          validationInterval = 15000
          testOnBorrow = true
          testWhileIdle = true
          testOnReturn = false
          jdbcInterceptors = "ConnectionState"
          defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
          validationQuery="SELECT 1"
        }
      }
    }

}
