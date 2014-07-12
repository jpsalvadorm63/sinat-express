
<%@ page import="sinat.express.DPA" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'DPA.label', default: 'DPA')}" />
  <title>SINAT - Distribución Política Administrativa</title>
  <style>
  .scaffold-list {
    background: ghostwhite;
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
</head>
<body>
<a href="#list-DPA" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="menu">
  <ul class="menu">
    <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
  </ul>
</div>

<div id="provincias" class="content scaffold-list" role="main">
  <h1 style="">Provincias</h1>
  <table>
    <thead>
    <tr>
      <g:sortableColumn property="codigo" title="${message(code: 'DPA.codigo.label', default: 'Codigo')}" />
      <g:sortableColumn property="nombre" title="${message(code: 'DPA.nombre.label', default: 'Nombre')}" />
      <th>+</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${DPAInstanceList}" status="i" var="DPAInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td>${fieldValue(bean: DPAInstance, field: "codigo")}</td>
        <td>${fieldValue(bean: DPAInstance, field: "nombre")}</td>
        <td class="link1"><g:remoteLink action="cantonesRep" id="${fieldValue(bean: DPAInstance, field: "codigo")}" update="cantonesLista">+</g:remoteLink></td>
      </tr>
    </g:each>
    </tbody>
  </table>
  <div class="pagination">
    <g:paginate total="${DPAInstanceCount ?: 0}" />
  </div>
</div>

<div id="cantones" class="content scaffold-list" role="main">
  <h1 style="">Cantones</h1>
  <div id="cantonesLista"></div>
</div>

</body>
</html>
