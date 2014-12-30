<%@ page import="sinat.express.FichaCampo" %>
<%@ page import="util.AppSession" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'fichaCampo.label', default: 'FichaCampo')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
  <style>
  .scaffold-list {
    border : 1px #000000 solid;
    background-color: whitesmoke;
    margin : 4px 4px 4px 4px;
    padding : 4px 8px 8px 8px;
    float : left;
    width : 98%;
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
</head>
<body>
<a href="#list-fichaCampo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="menu">
  <ul class="menu">
    <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
    <li><g:link class="create" action="create"><span>Nueva Ficha</span></g:link></li>
    <li><a href="${createLink(uri: '/fichaCampo/geoserver')}"><span>Mapa</span></a></li>
    <li><a href="#"><span>Ayuda</span></a></li>
  </ul>
</div>

<div id="list-fichaCampo" class="content scaffold-list" role="main" style="margin-top: 0;">
  <h1>Fichas de Investigación de Campo - FIC</h1>
  <p style="text-align: center;font-weight: bold;margin:2px 0 4px 0;">
    ${externos.DPALP.findByCodigo(AppSession.getSessionVar(session.id,'provincia'))} -
    ${externos.DPALP.findByCodigo(AppSession.getSessionVar(session.id,'canton'))}
  </p>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>
  <table>
    <thead>
    <tr>
      <g:sortableColumn property="parroquia" title="Parroquia" />
      <g:sortableColumn property="numeroFicha" title="${message(code: 'fichaCampo.numeroFicha.label', default: 'Numero Ficha')}" />
      <g:sortableColumn property="zonaHomogenea" title="${message(code: 'fichaCampo.zonaHomogenea.label', default: 'Zona Homogenea')}" />
      <g:sortableColumn property="fecha" title="${message(code: 'fichaCampo.fecha.label', default: 'Fecha')}" />
      <g:sortableColumn property="codigoCatastral" title="${message(code: 'fichaCampo.codigoCatastral.label', default: 'Código Catastral')}" />
      <g:sortableColumn property="superficieTotal" title="Superficie (ha)" />
      <g:sortableColumn property="legalizacion" title="Legalización" />
    </tr>
    </thead>
    <tbody>
    <g:each in="${fichaCampoInstanceList}" status="i" var="fichaCampoInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td>${fieldValue(bean: fichaCampoInstance, field: "parroquia")}</td>
        <td><span style="font-weight: bolder;color:darkred;">${fieldValue(bean: fichaCampoInstance, field: "numeroFicha")}</span></td>
        <td>${fieldValue(bean: fichaCampoInstance, field: "zonaHomogenea")}</td>
        <td>${fichaCampoInstance.fecha.format("ddMMMyyyy").toUpperCase()}</td>
        <td><g:link action="show" id="${fichaCampoInstance.id}">${fieldValue(bean: fichaCampoInstance, field: "codigoCatastral")}</g:link></td>
        <td>${fieldValue(bean: fichaCampoInstance, field: "superficieTotal")}</td>
        <td>${fieldValue(bean: fichaCampoInstance, field: "legalizacion")}</td>
      </tr>
    </g:each>
    </tbody>
  </table>
  <g:if test="${fichaCampoInstanceCount > 16}">
    <div class="pagination">
      <g:paginate total="${fichaCampoInstanceCount ?: 0}" />
    </div>
  </g:if>
</div>
</body>
</html>
