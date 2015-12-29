package sinat.express

import externos.DPALP
import groovy.sql.Sql

class FichaCampo {

    // 0. Encabezado
    String codigoCatastral
    String responsable
    Date fecha
    String numeroFicha
    String gad

    // I. Información General
    // I.a Ubicación Geográfica
    String zonaHomogenea
    DPALP provincia
    DPALP canton
    DPALP parroquia
    String sector
    String coordenadaX
    String coordenadaY
    Float altitud

    // I.b Información General
    String nombrePropietario
    String nombreArrendatario
    String nombreAdministrador
    String encuestado
    Float superficieTotal
    String construccion
    String legalizacion

    // I.c Confiabilidad
    String confiabilidad

    // II. características del predio
    // II.b Servicios Básicos
    boolean sbEnergiaElectrica
    boolean sbAguaPotable
    boolean sbAlcantarillado
    boolean sbComunicaciones
    // String sbOtro

    // II.c Accesibilidad
    Accesibilidad accesibilidad

    // II.d Cuoltivos del sector
    String cultivosSector1
    String cultivosSector2
    String cultivosSector3
    String cultivosSector4

    String observaciones
    String registroFotografico

    // campos de control
    Date fechaCreacion
    Date fechaActualizacion
    String statusLevantamiento
    String statusControl
    String origen

    // CAJA
    Double minx
    Double miny
    Double maxx
    Double maxy

    static constraints = {
        codigoCatastral(size:1..16,nullable:false,unique:true)
        responsable(size:0..64,nullable:true)
        fecha(nullable:false,unique:false)
        numeroFicha(size:1..64,nullable:false,unique:true)
        zonaHomogenea(size:1..64,nullable:false,unique:false)
        provincia(nullable:true , unique:false)
        canton(nullable:true , unique:false)
        parroquia(nullable:true , unique:false)
        gad(maxSize:4, unique:false,nullable:true)
        sector(size:1..64,nullable:true,unique:false)
        coordenadaX(maxSize:16,nullable:true)
        coordenadaY(maxSize:16,nullable:true)
        altitud (nullable:true)
        nombrePropietario(maxSize:64,nullable:false,unique:false)
        nombreArrendatario(maxSize:64,nullable:true,unique:false)
        nombreAdministrador(maxSize:64,nullable:true,unique:false)
        encuestado(maxSize:64,nullable:true,unique:false)
        superficieTotal(nullable:false,defoult:0)
        construccion(size:1..16,nullable:true,unique:false,inList:['SI POSEE','NO POSEE'],default:'')
        legalizacion(size:1..16,nullable:true,unique:false,inList:['CON ESCRITURAS','SIN ESCRITURAS'],default:'')
        confiabilidad(nullable:false,defoult:0,unique:false,inList:['100%','95%','90%','85%'],default:'')
        sbEnergiaElectrica(deafult:false)
        sbAguaPotable(deafult:false)
        sbAlcantarillado(deafult:false)
        sbComunicaciones(deafult:false,nullable:true)
        accesibilidad(nullable:true)
        cultivosSector1(size:0..32, nullable:true)
        cultivosSector2(size:0..32, nullable:true)
        cultivosSector3(size:0..32, nullable:true)
        cultivosSector4(size:0..32, nullable:true)
        registroFotografico(size:0..240, nullable:true)
        observaciones(size:0..800, nullable:true)
        minx(nullable:true)
        miny(nullable:true)
        maxx(nullable:true)
        maxy(nullable:true)

        fechaCreacion(default: new Date())
        fechaActualizacion(default: new Date())
        statusLevantamiento(size:0..48, nullable:true,inList: ['EN REVISION', 'PARA ENTREGA'])
        statusControl(size:0..48, nullable:true,inList: ['POR REVISAR','ACEPTADA','RECHAZADA'])
        origen(size:0..48, nullable:true,inList: ['INGEOMATICA','UE'])
    }

    static mapping = {
        table "fichacampo"
        version true
        cache false

        id                    column: "id"
        codigoCatastral	      column:"codigocatastral", index:'cc_idx'
        responsable           column:"responsable"
        fecha            	  column:"fregistro"
        numeroFicha     	  column:"numeroficha"
        zonaHomogenea   	  column:"zonahomogenea"
        provincia             column:"provincia_id"
        canton                column:"canton_id",       index:'canton_idx'
        gad                   column:"gad",             index:'gad_idx'
        parroquia             column:"parroquia_id"
        sector          	  column:"sector"
        coordenadaX     	  column:"coordenadax"
        coordenadaY      	  column:"coordenaday"
        altitud 			  column:"altitud"
        nombrePropietario 	  column:"nombrepropietario"
        nombreArrendatario 	  column:"nombrearrendatario"
        nombreAdministrador   column:"nombreadministrador"
        encuestado 			  column:"encuestado"
        superficieTotal       column:"superficietotal"
        construccion 		  column:"construccion"
        legalizacion 		  column:"legalizacion"
        confiabilidad 	      column:"confiabilidad"
        sbEnergiaElectrica    column:"sbenergiaelectrica"
        sbAguaPotable         column:"sbaguapotable"
        sbAlcantarillado      column:"sbalcantarillado"
        sbComunicaciones      column:"sbcomunicaciones"
        accesibilidad         column:"accesibilidad_id"
        cultivosSector1       column:"cultivossector1"
        cultivosSector2       column:"cultivossector2"
        cultivosSector3       column:"cultivossector3"
        cultivosSector4       column:"cultivossector4"

        minx                  column:"minx"
        miny                  column:"miny"
        maxx                  column:"maxx"
        maxy                  column:"maxy"

        registroFotografico   column:"registrofotografico"
        observaciones         column:"observaciones", type: "text"
        fechaCreacion         column:"creacion"
        fechaActualizacion    column:"actualizacion"
        statusLevantamiento   column:"statuslevantamiento"
        statusControl         column:"statuscontrol"
        origen                column:"origen"

    }

  static void fillData() {}

  String toString() { codigoCatastral }

  String toString2() { codigoCatastral }

  static entityName = "Ficha de Investigación Campo"

  static entityNamePlural = "Fichas de Investigación Campo"

  javax.sql.DataSource dataSource

  def afterInsert() {
    updatePolygon()
  }

  def afterUpdate() {
    updatePolygon()
  }

  static Sql sql = null

  def updatePolygon() {
    // **** DEPRECATED - - - ES UN ASUNTO DE ADMINISTRACÓN DE LA BASE DE DATOS
    // **** EL CODIGO ERA VÁLIDO SOLAMENTE PARA CHUNCHI
    /*if(sql == null)
      sql = new Sql(dataSource)
    String contador = "SELECT count(*) FROM chunchiforweb WHERE codigocata = '${codigoCatastral}' AND geom IS NOT NULL"
    def n = sql.firstRow(contador)[0]
    if( n > 0) {
      sql.executeUpdate("UPDATE fichacampo f SET geom = (SELECT ST_Multi(c.geom) FROM chunchiforweb c WHERE f.codigocatastral = c.codigocata) WHERE f.codigocatastral like '${codigoCatastral}';")
      sql.executeUpdate("update fichacampo set minx = st_xmin(geom),miny = st_ymin(geom),maxx = st_xmax(geom),maxy = st_ymax(geom) WHERE codigocatastral = '${codigoCatastral}'")
    } else {
      sql.executeUpdate("UPDATE fichacampo SET geom = null, minx = null, miny = null, maxx = null, maxy = null WHERE codigocatastral = '${codigoCatastral}'")
    }*/
  }

  String updatePolygon(String gadm, String dbTable, String campoCodigoCata, String campoGeon) {

    // -- EJEMPLO DE UTILIZACION EN SCRIPT:
    //  def gad = '0605' /*- Antonio Ante- */
    //  def dbTable = 'chunchiforweb'
    //  def ccCampo = 'codigocata'
    //  def geomCampo = 'geom'
    //  def canton = externos.DPALP.findByCodigo(gad)
    //
    //  //def gad = '1002' /*- Antoni Ante- */
    //  //def dbTable = 'lp.prediogr'
    //  //def ccCampo = 'cc'
    //  //def geomCampo = 'geom'
    //  //def canton = externos.DPALP.findByCodigo(gad)
    //
    //  def n = 0
    //  sinat.express.FichaCampo.findAllByCanton(canton).each { fc ->
    //    print "${fc.canton.nombre} - ${fc.canton.codigo}, cc: ${fc.codigoCatastral} "
    //    println fc.updatePolygon(gad, dbTable, ccCampo, geomCampo)
    //    n++
    //  }
    //  return n

    if( codigoCatastral != null && codigoCatastral.size() >= 4 && codigoCatastral[0..3] == gadm && dbTable != null ) {
        if(sql == null)
            sql = new Sql(dataSource)
        String contadorSql = "SELECT count(*) FROM " + dbTable + " WHERE " + campoCodigoCata + " = '" + codigoCatastral + "' AND " + campoGeon + " IS NOT NULL"
        def n = sql.firstRow(contadorSql)[0]
        if( n > 0) {
            String updateSql = "UPDATE fichacampo f SET geom = (SELECT ST_Multi(c." + campoGeon + ") FROM " + dbTable + " c WHERE f.codigocatastral = c." + campoCodigoCata + ") WHERE f.codigocatastral like '${codigoCatastral}';"
            sql.executeUpdate(updateSql)
            sql.executeUpdate("update fichacampo set minx = st_xmin(geom),miny = st_ymin(geom),maxx = st_xmax(geom),maxy = st_ymax(geom) WHERE codigocatastral = '${codigoCatastral}'")
            return 'OK'
        } else {
            sql.executeUpdate("UPDATE fichacampo SET geom = null, minx = null, miny = null, maxx = null, maxy = null WHERE codigocatastral = '${codigoCatastral}'")
            return 'NO OK'
        }
    } else
        return '-'
  }

  // -- cambio de DPA a DPALP --
  // borra las columnas provincia_id, canton_id y parroquia_id
  // correr el siguiente script:
  /*

  import sinat.express.FichaCampo

  FichaCampo.findAll().each { fc ->
    fc.updateDPALP().save(flush:true)
    println fc.provincia?.codigo + ', ' + fc.canton?.codigo + ', ' + fc.parroquia?.codigo
  }

   */

    FichaCampo updateDPALP() {
        // -- función para actualizar la división política administrativa en base al campo codigoCatastral
        // -- y su bùsqueda de instancias de la clase DPALP
        if(codigoCatastral?.length() >= 6 ) {
            provincia = DPALP.findByCodigo(codigoCatastral[0..1])
            canton = DPALP.findByCodigo(codigoCatastral[0..3])
            parroquia = DPALP.findByCodigo(codigoCatastral[0..5])
        }

        return this
    }

  // Para cambiar longitud de código catastral
  // SELECT atttypmod FROM pg_attribute WHERE attrelid = 'fichacampo'::regclass AND attname = 'codigocatastral';
  // UPDATE pg_attribute SET atttypmod = 16+4 WHERE attrelid = 'fichacampo'::regclass AND attname = 'codigocatastral';

}
