package lp

class Prediogr {

  String  cc
  String  gad
  Integer minx
  Integer miny
  Integer maxx
  Integer maxy

  // -- aumentar manualmente un campo geom para contener el plano del predio
  //select dropGeometryColumn('lp','prediogr','geom');
  //select addGeometryColumn('lp','prediogr','geom',32717,'MULTIPOLYGON',2);

  static constraints = {
    cc   nullable: false, maxSize: 24, unique: true
    gad  nullable: true, maxSize: 4, unique: false
    minx nullable: true
    miny nullable: true
    maxx nullable: true
    maxy nullable: true
  }

  static mapping = {
    table name: 'prediogr', schema: 'lp'
    version false
    cache false

    id   column: 'id'
    cc   column: 'cc', index:'prediogr_cc_idx'
    gad  column: 'gad', index:'prediogr_gad_idx'
    minx column: 'minx'
    miny column: 'miny'
    maxx column: 'maxx'
    maxy column: 'maxy'
  }

  // -- SCRIPT PARA GENERAR COLUMNA MULTIPOLIGONO
  //select dropGeometryColumn('lp','prediogr','geom');
  //select addGeometryColumn('lp','prediogr','geom',32717,'MULTIPOLYGON',2);
  //UPDATE fichacampo SET geom = null, minx = null, miny = null, maxx = null, maxy = null WHERE geom is not null
  // -- luego de importados los datos ejecutar las siguienes sentencias SQL
  //update lp.prediogr set minx = st_xmin(geom), miny = st_ymin(geom), maxx = st_xmax(geom), maxy = st_ymax(geom), gad = substring(cc from 1 for 4) where geom is not null ;
  //update lp.prediogr set minx = null, miny = null, maxx = null, maxy = null, gad = null where geom is null ;

}
