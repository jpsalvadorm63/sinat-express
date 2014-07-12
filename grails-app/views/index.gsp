<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>SINAT-EXPRESS</title>
  <link type="text/css" href="menu/menu.css" rel="stylesheet" />
  <script type="text/javascript" src="menu/jquery.js"></script>
  <script type="text/javascript" src="menu/menu.js"></script>
</head>
<body>
  <div id="menu">
    <g:if test="${session.canton != null}">
      <ul class="menu">
        <li><a class="parent" href="#"><span>Ficha de Campo</span></a>
          <div><ul>
            <li><a href="${createLink(uri: '/FichaCampo/index')}"><span>Lista de fichas</span></a></li>
            <li><a class="parent" href="#"><span>Reportes</span></a>
              <div><ul>
                <li><a href="${createLink(uri:'/DPA/informacionGeneralACsv/')}${session.canton}"><span>Información General</span></a></li>
                <li><a href="${createLink(uri:'/DPA/serviciosBasicosACsv/')}${session.canton}"><span>Servicios básicos</span></a></li>
                <li><a href="${createLink(uri:'/DPA/coberturaACsv/')}${session.canton}"><span>Cobertura</span></a></li>
                <li><a href="${createLink(uri:'/DPA/habitacionalACsv/')}${session.canton}"><span>Habitacional</span></a></li>
              </ul></div>
            </li>
            <li><a class="parent" href="#"><span>Administración</span></a>
              <div><ul>
                <li><a href="${createLink(uri:'/DPA/index')}"><span>DPA</span></a></li>
                <li><a href="${createLink(uri:'/tipoUso/index')}"><span>Tipos de Uso</span></a></li>
                <li><a href="${createLink(uri:'/accesibilidad/index')}"><span>Accesibilidad</span></a></li>
                <li><a href="${createLink(uri:'/servicioBasico/index')}"><span>Servicios Básicos</span></a></li>
                <li><a href="${createLink(uri:'/tipoMecanizacion/index')}"><span>Mecanización</span></a></li>
                <li><a href="${createLink(uri:'/tipoSistemaDeRiego/index')}"><span>Sistemas de Riego</span></a></li>
                <li><a href="${createLink(uri:'/tipoTecnologiaPredominante/index')}"><span>Tipos de Tecnología Predominante</span></a></li>
              </ul></div>
            </li>
          </ul></div>
        </li>
        <li><a href="${createLink(uri: '/fichaCampo/geoserver')}"><span>Mapa</span></a></li>
        <li id="provCanton"><a href="${createLink(uri: '/DPA/cambiarCanton')}"><span>Seleccionar Cantón</span></a></li>
        <li class="last"><a href="#"><span>Ayuda</span></a></li>
      </ul>
    </g:if>
    <g:if test="${session.canton == null}">
      <ul class="menu">
        <li class="last"><a href="${createLink(uri: '/DPA/cambiarCanton')}"><span>Seleccionar Provincia/Cantón</span></a></li>
      </ul>
    </g:if>
  </div>
</body>
</html>
