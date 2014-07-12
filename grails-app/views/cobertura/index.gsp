
<%@ page import="sinat.express.Cobertura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cobertura.label', default: 'Cobertura')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cobertura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>

		<div id="list-cobertura" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="cobertura.fichaCampo.label" default="Ficha Campo" /></th>
					
						<g:sortableColumn property="cobertura" title="${message(code: 'cobertura.cobertura.label', default: 'Cobertura')}" />
					
						<g:sortableColumn property="superficie" title="${message(code: 'cobertura.superficie.label', default: 'Superficie')}" />
					
						<g:sortableColumn property="rendimiento" title="${message(code: 'cobertura.rendimiento.label', default: 'Rendimiento')}" />
					
						<g:sortableColumn property="cargaAnimal" title="${message(code: 'cobertura.cargaAnimal.label', default: 'Carga Animal')}" />
					
						<g:sortableColumn property="rotacion" title="${message(code: 'cobertura.rotacion.label', default: 'Rotacion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${coberturaInstanceList}" status="i" var="coberturaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${coberturaInstance.id}">${fieldValue(bean: coberturaInstance, field: "fichaCampo")}</g:link></td>
					
						<td>${fieldValue(bean: coberturaInstance, field: "cobertura")}</td>
					
						<td>${fieldValue(bean: coberturaInstance, field: "superficie")}</td>
					
						<td>${fieldValue(bean: coberturaInstance, field: "rendimiento")}</td>
					
						<td>${fieldValue(bean: coberturaInstance, field: "cargaAnimal")}</td>
					
						<td>${fieldValue(bean: coberturaInstance, field: "rotacion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${coberturaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
