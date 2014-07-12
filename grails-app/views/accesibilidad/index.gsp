
<%@ page import="sinat.express.Accesibilidad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'accesibilidad.label', default: 'Accesibilidad')}" />
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
    <g:javascript library="jquery"/>
    <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
	</head>
	<body>
		<a href="#list-accesibilidad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

    <div id="menu">
      <ul class="menu">
        <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
      </ul>
    </div>

		<div id="list-accesibilidad" class="content scaffold-list" role="main">
			<h1>Accesibilidad</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="Código" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'accesibilidad.nombre.label', default: 'Nombre')}" />

					
					</tr>
				</thead>
				<tbody>
				<g:each in="${accesibilidadInstanceList}" status="i" var="accesibilidadInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: accesibilidadInstance, field: "codigo")}</td>
					
						<td>${fieldValue(bean: accesibilidadInstance, field: "nombre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<!--<div class="pagination">
				<g:paginate total="${accesibilidadInstanceCount ?: 0}" />
			</div> -->
		</div>
	</body>
</html>
