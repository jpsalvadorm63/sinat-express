package sinat.express

import grails.transaction.Transactional
import groovy.sql.Sql
import groovy.sql.GroovyRowResult

class GeomsService {

  def dataSource

  @Transactional
  String loadDataToLp(fromTable,geomColumn,campoCodigoCatastral) {
    def sqlPredios = """
      SELECT
        'fromTable' source,
        campoCodigoCatastral "codigoCatastral",
        SUBSTRING(campoCodigoCatastral FROM 1 FOR 2) "codigoProvincia",
        SUBSTRING(campoCodigoCatastral FROM 3 FOR 2) "codigoCanton",
        'false' pec,
        round(cast(st_xmin(geomColumn) as numeric),1) minx,
        round(cast(st_ymin(geomColumn) as numeric),1) miny,
        round(cast(st_xmax(geomColumn) as numeric),1) maxx,
        round(cast(st_ymax(geomColumn) as numeric),1) maxy,
        round(cast(st_area(geomColumn)/10000.0 as numeric),3) superficie
      FROM
        fromTable
      WHERE
            CHAR_LENGTH(campoCodigoCatastral) = 13
        AND geom IS NOT NULL;"""

    def sqlPECs = """
      select
        'fromTable' source,
        codigocata "codigoCatastral",
        SUBSTRING(campoCodigoCatastral FROM 1 FOR 2) "codigoProvincia",
        SUBSTRING(campoCodigoCatastral FROM 3 FOR 2) "codigoCanton",
        'true' pec,
        round(cast(st_xmin(geomColumn) as numeric),1) minx,
        round(cast(st_ymin(geomColumn) as numeric),1) miny,
        round(cast(st_xmax(geomColumn) as numeric),1) maxx,
        round(cast(st_ymax(geomColumn) as numeric),1) maxy,
        round(cast(st_area(geomColumn)/10000.0 as numeric),3) superficie
      from
        ( select
            campoCodigoCatastral,
            st_union(geomColumn) geom
          from
            fromTable
          where
            char_length(campoCodigoCatastral) = 8
          group by
            campoCodigoCatastral ) p """

    sqlPredios = sqlPredios.replaceAll('fromTable',fromTable).
                    replaceAll('geomColumn',geomColumn).
                    replaceAll('campoCodigoCatastral',campoCodigoCatastral)

    sqlPECs = sqlPECs.replaceAll('fromTable',fromTable).
        replaceAll('geomColumn',geomColumn).
        replaceAll('campoCodigoCatastral',campoCodigoCatastral)

    def sql = new Sql(dataSource)
    int errores = 0, ok = 0, nuevos = 0

    [sqlPECs,sqlPredios].each { strsql ->

      sql.eachRow(strsql) { data ->
        String codigoCatastral = data['codigoCatastral']
        String codigoCanton = data['codigoProvincia'] + data['codigoProvincia']

        def lp = Lp.findByCodigoCatastral(codigoCatastral)
        if(!lp) {
          lp = new Lp()
          lp.fechaCreacion = new Date()
          nuevos++
        }

        lp.source = data.source
        lp.codigoCatastral = data.codigoCatastral
        lp.codigoProvincia = data.codigoProvincia
        lp.codigoCanton = data.codigoCanton
        lp.pec = data.pec
        lp.minx = data.minx
        lp.miny = data.miny
        lp.maxx = data.maxx
        lp.maxy = data.maxy
        lp.superficie = data.superficie
        lp.canton = DPA.findByCodigo(codigoCanton)
        lp.fechaActualizacion = new Date()
        lp.save(flush:true)
        /*if(lp.hasErrors())
          errores++
        else*/
          ok++
        println lp
      }
    }

    println "resultado: ${errores} errores, ${ok} correctos, ${nuevos} nuevos"
    return "resultado: ${errores} errores, ${ok} correctos, ${nuevos} nuevos"
  }

}
