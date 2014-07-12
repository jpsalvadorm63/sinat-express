
<%@ page import="sinat.express.TipoUso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoUso.label', default: 'TipoUso')}" />
		<title>SINAT - Tipos de uso</title>
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
	</head>
	<body>

  <a href="#list-tipoUso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div id="menu">
    <ul class="menu">
      <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
    </ul>
  </div>

  <div style="float:left;width:320px;">
    <div id="list-tipoUso" class="content scaffold-list" role="main" style=" width: 290px;">
      <h1>Tipos de Uso</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <table>
        <thead>
        <tr>
          <g:sortableColumn property="codigo" title="Código" />
          <g:sortableColumn property="nombre" title="${message(code: 'tipoUso.nombre.label', default: 'Nombre')}" />
          <th class="sortable" style="text-align: center;">Coberturas</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${tipoUsoInstanceList}" status="i" var="tipoUsoInstance">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: tipoUsoInstance, field: "codigo")}</td>
            <td>${fieldValue(bean: tipoUsoInstance, field: "nombre")}</td>
            <td style="text-align: center;">
              <g:if test="${tipoUsoInstance.hasCoberturas}">
                <g:remoteLink action="coberturas" id="${tipoUsoInstance.id}" update="coberturas"> + </g:remoteLink>
              </g:if>
            </td>
          </tr>
        </g:each>
        </tbody>
      </table>
      <!-- <div class="pagination">
				<g:paginate total="${tipoUsoInstanceCount ?: 0}" />
			</div> -->
    </div>
  </div>
  <div id="coberturas" style="float:left;width:400px;"></div>
	</body>
</html>
