package sinat.express

import groovy.sql.Sql

class Lp {

  // campos de dominio
  String codigoCatastral
  DPA canton
  String codigoProvincia
  String codigoCanton
  Boolean pec

  // datos geo-espaciales
  Double minx
  Double miny
  Double maxx
  Double maxy
  Double superficie
  String source
  //-- campo "geom" ponerlo de manera manual debido a fallas en hibernate spatial
  //select dropGeometryColumn('geoms','lp','geom');
  //select addGeometryColumn('geoms','lp','geom',32717,'MULTIPOLYGON',2);

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {
    codigoCatastral(nullable:false,unique: true)
    canton(nullable:true)
    codigoProvincia(nullable:true,size:0..8)
    codigoCanton(nullable:true,size:0..8)
    pec(nullable:false, default:false)

    // datos geo-espaciales
    minx(nullable:true)
    miny(nullable:true)
    maxx(nullable:true)
    maxy(nullable:true)
    superficie(nullable:true)
    source(nullable:true, size:0..128)

    fechaCreacion(nullable:true, default: new Date())
    fechaActualizacion(nullable:true, default: new Date())
  }

  static mapping = {
    table "lp"
    version true
    cache false
    sort "codigocatastral"

    id              column: "id"
    // Mapping de ampos de dominio
    canton          column:"canton_id"
    codigoProvincia column:"codigoprovincia"
    codigoCanton    column:"codigocanton"
    codigoCatastral column:"codigocatastral"
    pec             column:"pec"

    // datos geo-espaciales
    minx            column:"minx"
    miny            column:"miny"
    maxx            column:"maxx"
    maxy            column:"maxy"
    superficie      column:"superficie"
    source          column:"source"

    fechaCreacion      column: "creacion"
    fechaActualizacion column: "actualizacion"
  }

  static void fillData() {

  }

  String toString() { codigoCatastral }

  String toString2() { codigoCatastral }

  static entityName = "Geometria de Predio"

  static entityNamePlural = "Geometrias de Predio"

}
