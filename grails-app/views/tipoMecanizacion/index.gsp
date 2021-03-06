
<%@ page import="sinat.express.TipoMecanizacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoMecanizacion.label', default: 'TipoMecanizacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
		<a href="#list-tipoMecanizacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

    <div id="menu">
      <ul class="menu">
        <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
      </ul>
    </div>

		<div id="list-tipoMecanizacion" class="content scaffold-list" role="main">
			<h1>Tipos de Mecanización</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="Código" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'tipoMecanizacion.nombre.label', default: 'Nombre')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoMecanizacionInstanceList}" status="i" var="tipoMecanizacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: tipoMecanizacionInstance, field: "codigo")}</td>
					
						<td>${fieldValue(bean: tipoMecanizacionInstance, field: "nombre")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<!--<div class="pagination">
				<g:paginate total="${tipoMecanizacionInstanceCount ?: 0}" />
			</div>-->
		</div>
	</body>
</html>
