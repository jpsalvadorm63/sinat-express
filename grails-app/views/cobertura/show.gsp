
<%@ page import="sinat.express.Cobertura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cobertura.label', default: 'Cobertura')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cobertura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cobertura" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cobertura">
			
				<g:if test="${coberturaInstance?.fichaCampo}">
				<li class="fieldcontain">
					<span id="fichaCampo-label" class="property-label"><g:message code="cobertura.fichaCampo.label" default="Ficha Campo" /></span>
					
						<span class="property-value" aria-labelledby="fichaCampo-label"><g:link controller="fichaCampo" action="show" id="${coberturaInstance?.fichaCampo?.id}">${coberturaInstance?.fichaCampo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.cobertura}">
				<li class="fieldcontain">
					<span id="cobertura-label" class="property-label"><g:message code="cobertura.cobertura.label" default="Cobertura" /></span>
					
						<span class="property-value" aria-labelledby="cobertura-label"><g:fieldValue bean="${coberturaInstance}" field="cobertura"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.superficie}">
				<li class="fieldcontain">
					<span id="superficie-label" class="property-label"><g:message code="cobertura.superficie.label" default="Superficie" /></span>
					
						<span class="property-value" aria-labelledby="superficie-label"><g:fieldValue bean="${coberturaInstance}" field="superficie"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.rendimiento}">
				<li class="fieldcontain">
					<span id="rendimiento-label" class="property-label"><g:message code="cobertura.rendimiento.label" default="Rendimiento" /></span>
					
						<span class="property-value" aria-labelledby="rendimiento-label"><g:fieldValue bean="${coberturaInstance}" field="rendimiento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.cargaAnimal}">
				<li class="fieldcontain">
					<span id="cargaAnimal-label" class="property-label"><g:message code="cobertura.cargaAnimal.label" default="Carga Animal" /></span>
					
						<span class="property-value" aria-labelledby="cargaAnimal-label"><g:fieldValue bean="${coberturaInstance}" field="cargaAnimal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.rotacion}">
				<li class="fieldcontain">
					<span id="rotacion-label" class="property-label"><g:message code="cobertura.rotacion.label" default="Rotacion" /></span>
					
						<span class="property-value" aria-labelledby="rotacion-label"><g:fieldValue bean="${coberturaInstance}" field="rotacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.cosechasPorAnio}">
				<li class="fieldcontain">
					<span id="cosechasPorAnio-label" class="property-label"><g:message code="cobertura.cosechasPorAnio.label" default="Cosechas Por Anio" /></span>
					
						<span class="property-value" aria-labelledby="cosechasPorAnio-label"><g:fieldValue bean="${coberturaInstance}" field="cosechasPorAnio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.tecnologiaPredominante}">
				<li class="fieldcontain">
					<span id="tecnologiaPredominante-label" class="property-label"><g:message code="cobertura.tecnologiaPredominante.label" default="Tecnologia Predominante" /></span>
					
						<span class="property-value" aria-labelledby="tecnologiaPredominante-label"><g:fieldValue bean="${coberturaInstance}" field="tecnologiaPredominante"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.sistemaDeRiego}">
				<li class="fieldcontain">
					<span id="sistemaDeRiego-label" class="property-label"><g:message code="cobertura.sistemaDeRiego.label" default="Sistema De Riego" /></span>
					
						<span class="property-value" aria-labelledby="sistemaDeRiego-label"><g:fieldValue bean="${coberturaInstance}" field="sistemaDeRiego"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.mecanizacion}">
				<li class="fieldcontain">
					<span id="mecanizacion-label" class="property-label"><g:message code="cobertura.mecanizacion.label" default="Mecanizacion" /></span>
					
						<span class="property-value" aria-labelledby="mecanizacion-label"><g:fieldValue bean="${coberturaInstance}" field="mecanizacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.fechaCreacion}">
				<li class="fieldcontain">
					<span id="fechaCreacion-label" class="property-label"><g:message code="cobertura.fechaCreacion.label" default="Fecha Creacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreacion-label"><g:formatDate date="${coberturaInstance?.fechaCreacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${coberturaInstance?.fechaActualizacion}">
				<li class="fieldcontain">
					<span id="fechaActualizacion-label" class="property-label"><g:message code="cobertura.fechaActualizacion.label" default="Fecha Actualizacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaActualizacion-label"><g:formatDate date="${coberturaInstance?.fechaActualizacion}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:coberturaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${coberturaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
