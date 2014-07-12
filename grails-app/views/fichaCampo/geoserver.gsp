<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'fichaCampo.label', default: 'FichaCampo')}"/>
<title><g:message code="default.create.label" args="[entityName]"/></title>
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">

<link rel="stylesheet" type="text/css" href="${resource(dir: 'openlayers/theme/default', file: 'style.css')}"/>
<style type="text/css">

#map {
  clear: both;
  position: relative;
  width: 100%;
  height: 600px;
  border: 1px solid darkslateblue;
}

#wrapper {
  width: 924px;
}

#location {
  float: right;
}

</style>
<script type="text/javascript" src="${resource(dir: 'openlayers', file: 'OpenLayers.js')}"></script>
<script type="text/javascript">

  var fichaCampoInstanceId = ${(fichaCampoInstance != null)?fichaCampoInstance.id:-1} ;
  var codigoCatastral = '${fichaCampoInstance?.codigoCatastral}';

  var map;

  var catastro;
  var pecs;
  var fics;
  var zh;
  var fic;
  var minx = 715679.5539 + 1000;
  var miny = 9731074.0878 + 1000;
  var maxx = 745359.5046 - 1000;
  var maxy = 9754512.8741 - 1000;
  var boxMinx = ${(fichaCampoInstance != null)?fichaCampoInstance.minx:'minx'} ;
  var boxMiny = ${(fichaCampoInstance != null)?fichaCampoInstance.miny:'miny'} ;
  var boxMaxx = ${(fichaCampoInstance != null)?fichaCampoInstance.maxx:'maxx'} ;
  var boxMaxy = ${(fichaCampoInstance != null)?fichaCampoInstance.maxy:'maxy'} ;

  var pureCoverage = false;
  // pink tile avoidance
  OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
  // make OL compute scale according to WMS spec
  OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;

  function initMaps() {

    $('li#optCreateFicha').css('display','none');

    format = 'image/png';

    var bounds = new OpenLayers.Bounds(minx,miny,maxx,maxy);
    var box  = bounds;
    if(codigoCatastral != '')
      box = new OpenLayers.Bounds(boxMinx,boxMiny,boxMaxx,boxMaxy);
    var options = {
      controls: [],
      maxExtent: bounds,
      maxResolution: 60.823727261718886,
      projection: "EPSG:32717",
      units: 'm',
      numZoomLevels: 24
    };
    map = new OpenLayers.Map('map', options);

    catastro = new OpenLayers.Layer.WMS(
        "Catastro","${wms}",
        {
          LAYERS: 'sinatexpress:levantamientoPredial',
          STYLES: '',
          transparent:false,
          format: format
        },
        {
          singleTile: true,
          buffer: 0,
          displayOutsideMaxExtent: true,
          isBaseLayer: true,
          yx : {'EPSG:32717' : false}
        }
    );
    map.addLayer(catastro);
    pecs = new OpenLayers.Layer.WMS(
        "PECs","${wms}",
        {
          LAYERS: 'sinatexpress:pecs',
          STYLES: '',
          transparent:true,
          format: format
        },
        {
          singleTile: true,
          buffer: 0,
          displayOutsideMaxExtent: true,
          isBaseLayer: false,
          yx : {'EPSG:32717' : false}
        }
    );
    map.addLayer(pecs);
    fics = new OpenLayers.Layer.WMS(
        "Fichas de Investigación","${wms}",
        {
          LAYERS: 'sinatexpress:fics',
          STYLES: '',
          transparent:true,
          format: format
        },
        {
          singleTile: true,
          buffer: 0,
          displayOutsideMaxExtent: true,
          isBaseLayer: false,
          yx : {'EPSG:32717' : false}
        }
    );
    map.addLayer(fics);
    zh = new OpenLayers.Layer.WMS(
        "Zona Homogenea","${wms}",
        {
          LAYERS: 'sinatexpress:zh',
          STYLES: '',
          transparent:true,
          format: format
        },
        {
          singleTile: true,
          buffer: 0,
          displayOutsideMaxExtent: true,
          isBaseLayer: false,
          yx : {'EPSG:32717' : false}
        }
    );
    map.addLayer(zh);
    if(codigoCatastral != '') {
      fic = selectPredio(codigoCatastral);
      map.addLayer(fic);
    }

    map.events.register('click', map, function (e) {
      var lonLat = map.getLonLatFromPixel(e.xy);
      $.ajax({
        url:'/express/fichaCampo/selectPredio',
        data: {
          longitud: lonLat.lon,
          latitud: lonLat.lat
        },
        success: function(data) {
          $('div#info').html(data.info);
          if(data.codigoCatastral != '') {
            codigoCatastral = data.codigoCatastral;
            if(fic) {
              map.removeLayer(fic);
              fic.destroy();
            }
            fic = selectPredio(codigoCatastral);
            map.addLayer(fic);
          }
          if(data.id != '' ) {
            $('li#optShowFicha').css('display','block');
            $('li#optShowFicha a').attr('href','/express/fichaCampo/show/' + data.id);
            $('li#optCreateFicha').css('display','none');
          } else
          if(data.codigoCatastral != '' ) {
            $('li#optShowFicha').css('display','none');
            $('li#optCreateFicha').css('display','block');
            $('li#optCreateFicha a').attr('href','/express/fichaCampo/create/' + data.codigoCatastral);
          } else {
            $('li#optShowFicha').css('display','none');
            $('li#optCreateFicha').css('display','none');
          }
        }
      });
    });

    map.addControl(new OpenLayers.Control.Navigation());
    map.addControl(new OpenLayers.Control.PanZoomBar({position: new OpenLayers.Pixel(2, 15)}));
    map.addControl(new OpenLayers.Control.Scale( document.getElementById('scale') ));
    map.addControl(new OpenLayers.Control.ScaleLine());
    map.addControl(new OpenLayers.Control.MousePosition({element:document.getElementById('location')}));
    map.addControl(new OpenLayers.Control.LayerSwitcher());
    map.addControl(new OpenLayers.Control.OverviewMap());
    map.addControl(new OpenLayers.Control.KeyboardDefaults());

    //map.zoomToExtent(box);
    map.zoomTo(1);

  }

  function selectPredio(codigoCatastral) {
    var layer = new OpenLayers.Layer.WMS(
        "Ficha actual","${wms}",
        {
          LAYERS: 'sinatexpress:fic',
          STYLES: '',
          transparent:true,
          format: format,
          viewparams:"codigocatastral:" + codigoCatastral
        },
        {
          singleTile: true,
          buffer: 0,
          displayOutsideMaxExtent: true,
          isBaseLayer: false,
          yx : {'EPSG:32717' : false}
        }
    );
    return layer;
  }

</script>
<link type="text/css" href="${resource(dir: 'menu', file: 'menu.css')}" rel="stylesheet" />
<script type="text/javascript" src="${resource(dir: 'jquery', file: 'jquery-1.11.0.min.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
</head>
<body onload="initMaps();">

<div id="menu">
  <ul class="menu">
    <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
    <li id="optShowFicha"><g:link class="create" action="show" id="${fichaCampoInstance?.id}"><span>Ficha</span></g:link></li>
    <li id="optCreateFicha"><g:link class="create" action="create"><span>Crear Ficha</span></g:link></li>
    <li><g:link class="list" action="index"><span>Fichas de Investigación de Campo</span></g:link></li>
  </ul>
</div>

<div id="map" class="content scaffold-create" role="main"></div>
<div id="wrapper">
  <div id="location" style="margin-right: 24px;"></div>
  <div id="scale"></div>
</div>
<div id="info" style="width:100%;clear:both"></div>

</body>
</html>
