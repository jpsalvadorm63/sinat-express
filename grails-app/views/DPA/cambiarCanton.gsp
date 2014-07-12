<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'tipoUso.label', default: 'TipoUso')}" />
  <title>SINAT - Cambiar Cantón</title>
  <style>
  .scaffold-list {
    border : 1px #000000 solid;
    margin : 4px 4px 4px 4px;
    padding : 4px 8px 8px 8px;
    float : left;
    width : 30%;
    -moz-border-radius: 6px;
    border-radius: 6px;
  }

  scaffold-list h1 {
    text-align: center;
    font-weight: bolder;
  }

  .link1 {
    font-weight: bolder;
    color: darkgreen;
  }

  h1 {
    text-align: center;
    font-weight: bolder;
  }
  </style>
  <link type="text/css" href="${resource(dir: 'menu', file: 'menu.css')}" rel="stylesheet" />
  <script type="text/javascript" src="${resource(dir: 'menu', file: 'jquery.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
  <script type="text/javascript">
    function cambiaProvincia(provinciaId) {
      $.ajax({
        type:'POST',
        data:{provinciaId:provinciaId},
        url:'/express/DPA/updateProvincia',
        success:function(result) {
          $('#ajaxCantones').html(result);
        }
      });
    }
    function cambiaCanton(cantonId) {
      $.ajax({
        type:'POST',
        data:{cantonId:cantonId},
        url:'/express/DPA/updateCanton',
        success:function(result) {}
      });
    }
  </script>
</head>
<body>
  <div id="menu">
    <ul class="menu">
      <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
    </ul>
  </div>
  <div style="width:90%;padding:8px 0 8px 24px:clear:both;margin-top: 48px;">
    <div class="label" style="float:left;width:160px;">Seleccione un provincia</div>
    <g:select style="width:260px;"
              id="provincia"
              name="provincia.id"
              from="${sinat.express.DPA.provincias()}"
              optionKey="id"
              value="${provincia?.id}"
              onchange="cambiaProvincia(this.value)" />
  </div>
  <div style="width:90%;padding:8px 0 8px 24px:clear:both;margin-top: 16px;">
    <div class="label" style="float:left;width:160px;">Seleccione un cantón</div>
    <div id="ajaxCantones">
      <g:select style="width:260px;"
                id="canton"
                name="canton.id"
                from="${sinat.express.DPA.cantones(provincia)}"
                optionKey="id"
                value="${canton?.id}"
                onchange="cambiaCanton(this.value)" />
    </div>
  </div>
</body>
</html>