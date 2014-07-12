package sinat.express

import groovy.sql.Sql

class FichaCampo {

  // 0. Encabezado
  String codigoCatastral
  String responsable
  Date fecha
  String numeroFicha

  // I. Información General
  // I.a Ubicación Geográfica
  String zonaHomogenea
  DPA provincia
  DPA canton
  DPA parroquia
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

  /*
  Float valoracionVenta
  Float valoracionOferta
  Float valoracionArriendo
  String registroFotografico
  String observacion
  */
  String observaciones
  String registroFotografico

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  // CAJA
  Double minx
  Double miny
  Double maxx
  Double maxy

  static constraints = {
    codigoCatastral(size:1..13,nullable:false,unique:true)
    responsable(size:0..64,nullable:true)
    fecha(nullable:false,unique:false)
    numeroFicha(size:1..64,nullable:false,unique:true)
    zonaHomogenea(size:1..64,nullable:false,unique:false)
    provincia(nullable:true , unique:false)
    canton(nullable:true , unique:false)
    parroquia(nullable:true , unique:false)
    sector(size:1..64,nullable:true,unique:false)
    coordenadaX(size:1..16,nullable:true,unique:false)
    coordenadaY(size:1..16,nullable:true,unique:false)
    altitud (nullable:true)
    nombrePropietario(size:1..64,nullable:false,unique:false)
    nombreArrendatario(size:1..64,nullable:true,unique:false)
    nombreAdministrador(size:1..64,nullable:true,unique:false)
    encuestado(size:1..64,nullable:true,unique:false)
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
  }

  static mapping = {
    table "fichacampo"
    version true
    cache false

    id                    column: "id"
    codigoCatastral	      column:"codigocatastral"
    responsable           column:"responsable"
    fecha            	    column:"fregistro"
    numeroFicha     		  column:"numeroficha"
    zonaHomogenea   		  column:"zonahomogenea"
    provincia             column:"provincia_id"
    canton                column:"canton_id"
    parroquia             column:"parroquia_id"
    sector          		  column:"sector"
    coordenadaX     		  column:"coordenadax"
    coordenadaY      	    column:"coordenaday"
    altitud 			        column:"altitud"
    nombrePropietario 	  column:"nombrepropietario"
    nombreArrendatario 	  column:"nombrearrendatario"
    nombreAdministrador   column:"nombreadministrador"
    encuestado 			      column:"encuestado"
    superficieTotal       column:"superficietotal"
    construccion 		      column:"construccion"
    legalizacion 		      column:"legalizacion"
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
    /*
    valoracionVenta		    column:"valoracionventa"
    valoracionOferta	    column:"valoracionoferta"
    valoracionArriendo    column:"valoracionarriendo"
    */
    minx                  column:"minx"
    miny                  column:"miny"
    maxx                  column:"maxx"
    maxy                  column:"maxy"

    registroFotografico   column:"registrofotografico"
    observaciones         column:"observaciones", type: "text"
    fechaCreacion         column:"creacion"
    fechaActualizacion    column:"actualizacion"

  }

  static void fillData() {

  }

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
    if(sql == null)
      sql = new Sql(dataSource)
    String contador = "SELECT count(*) FROM chunchiforweb WHERE codigocata = '${codigoCatastral}' AND geom IS NOT NULL"
    def n = sql.firstRow(contador)[0]
    if( n > 0) {
      sql.executeUpdate("UPDATE fichacampo f SET geom = (SELECT ST_Multi(c.geom) FROM chunchiforweb c WHERE f.codigocatastral = c.codigocata) WHERE f.codigocatastral like '${codigoCatastral}';")
      sql.executeUpdate("update fichacampo set minx = st_xmin(geom),miny = st_ymin(geom),maxx = st_xmax(geom),maxy = st_ymax(geom) WHERE codigocatastral = '${codigoCatastral}'")
    } else {
      sql.executeUpdate("UPDATE fichacampo SET geom = null, minx = null, miny = null, maxx = null, maxy = null WHERE codigocatastral = '${codigoCatastral}'")
    }
  }

}
