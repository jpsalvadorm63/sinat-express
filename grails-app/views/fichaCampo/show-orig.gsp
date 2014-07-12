
<%@ page import="sinat.express.FichaCampo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fichaCampo.label', default: 'FichaCampo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fichaCampo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fichaCampo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fichaCampo">
			
				<g:if test="${fichaCampoInstance?.codigoCatastral}">
				<li class="fieldcontain">
					<span id="codigoCatastral-label" class="property-label"><g:message code="fichaCampo.codigoCatastral.label" default="Codigo Catastral" /></span>
					
						<span class="property-value" aria-labelledby="codigoCatastral-label"><g:fieldValue bean="${fichaCampoInstance}" field="codigoCatastral"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.grupo}">
				<li class="fieldcontain">
					<span id="grupo-label" class="property-label"><g:message code="fichaCampo.grupo.label" default="Grupo" /></span>
					
						<span class="property-value" aria-labelledby="grupo-label"><g:fieldValue bean="${fichaCampoInstance}" field="grupo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="fichaCampo.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${fichaCampoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.numeroFicha}">
				<li class="fieldcontain">
					<span id="numeroFicha-label" class="property-label"><g:message code="fichaCampo.numeroFicha.label" default="Numero Ficha" /></span>
					
						<span class="property-value" aria-labelledby="numeroFicha-label"><g:fieldValue bean="${fichaCampoInstance}" field="numeroFicha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.numeroPunto}">
				<li class="fieldcontain">
					<span id="numeroPunto-label" class="property-label"><g:message code="fichaCampo.numeroPunto.label" default="Numero Punto" /></span>
					
						<span class="property-value" aria-labelledby="numeroPunto-label"><g:fieldValue bean="${fichaCampoInstance}" field="numeroPunto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.zonaHomogenea}">
				<li class="fieldcontain">
					<span id="zonaHomogenea-label" class="property-label"><g:message code="fichaCampo.zonaHomogenea.label" default="Zona Homogenea" /></span>
					
						<span class="property-value" aria-labelledby="zonaHomogenea-label"><g:fieldValue bean="${fichaCampoInstance}" field="zonaHomogenea"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.sector}">
				<li class="fieldcontain">
					<span id="sector-label" class="property-label"><g:message code="fichaCampo.sector.label" default="Sector" /></span>
					
						<span class="property-value" aria-labelledby="sector-label"><g:fieldValue bean="${fichaCampoInstance}" field="sector"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.coordenadaX}">
				<li class="fieldcontain">
					<span id="coordenadaX-label" class="property-label"><g:message code="fichaCampo.coordenadaX.label" default="Coordenada X" /></span>
					
						<span class="property-value" aria-labelledby="coordenadaX-label"><g:fieldValue bean="${fichaCampoInstance}" field="coordenadaX"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.coordenadaY}">
				<li class="fieldcontain">
					<span id="coordenadaY-label" class="property-label"><g:message code="fichaCampo.coordenadaY.label" default="Coordenada Y" /></span>
					
						<span class="property-value" aria-labelledby="coordenadaY-label"><g:fieldValue bean="${fichaCampoInstance}" field="coordenadaY"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.altitud}">
				<li class="fieldcontain">
					<span id="altitud-label" class="property-label"><g:message code="fichaCampo.altitud.label" default="Altitud" /></span>
					
						<span class="property-value" aria-labelledby="altitud-label"><g:fieldValue bean="${fichaCampoInstance}" field="altitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.nombrePropietario}">
				<li class="fieldcontain">
					<span id="nombrePropietario-label" class="property-label"><g:message code="fichaCampo.nombrePropietario.label" default="Nombre Propietario" /></span>
					
						<span class="property-value" aria-labelledby="nombrePropietario-label"><g:fieldValue bean="${fichaCampoInstance}" field="nombrePropietario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.nombreArrendatario}">
				<li class="fieldcontain">
					<span id="nombreArrendatario-label" class="property-label"><g:message code="fichaCampo.nombreArrendatario.label" default="Nombre Arrendatario" /></span>
					
						<span class="property-value" aria-labelledby="nombreArrendatario-label"><g:fieldValue bean="${fichaCampoInstance}" field="nombreArrendatario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.nombreAdministrador}">
				<li class="fieldcontain">
					<span id="nombreAdministrador-label" class="property-label"><g:message code="fichaCampo.nombreAdministrador.label" default="Nombre Administrador" /></span>
					
						<span class="property-value" aria-labelledby="nombreAdministrador-label"><g:fieldValue bean="${fichaCampoInstance}" field="nombreAdministrador"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.encuestado}">
				<li class="fieldcontain">
					<span id="encuestado-label" class="property-label"><g:message code="fichaCampo.encuestado.label" default="Encuestado" /></span>
					
						<span class="property-value" aria-labelledby="encuestado-label"><g:fieldValue bean="${fichaCampoInstance}" field="encuestado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.superficieTotal}">
				<li class="fieldcontain">
					<span id="superficieTotal-label" class="property-label"><g:message code="fichaCampo.superficieTotal.label" default="Superficie Total" /></span>
					
						<span class="property-value" aria-labelledby="superficieTotal-label"><g:fieldValue bean="${fichaCampoInstance}" field="superficieTotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.construccion}">
				<li class="fieldcontain">
					<span id="construccion-label" class="property-label"><g:message code="fichaCampo.construccion.label" default="Construccion" /></span>
					
						<span class="property-value" aria-labelledby="construccion-label"><g:fieldValue bean="${fichaCampoInstance}" field="construccion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.legalizacion}">
				<li class="fieldcontain">
					<span id="legalizacion-label" class="property-label"><g:message code="fichaCampo.legalizacion.label" default="Legalizacion" /></span>
					
						<span class="property-value" aria-labelledby="legalizacion-label"><g:fieldValue bean="${fichaCampoInstance}" field="legalizacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.confiabilidad}">
				<li class="fieldcontain">
					<span id="confiabilidad-label" class="property-label"><g:message code="fichaCampo.confiabilidad.label" default="Confiabilidad" /></span>
					
						<span class="property-value" aria-labelledby="confiabilidad-label"><g:fieldValue bean="${fichaCampoInstance}" field="confiabilidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.zonaUsoAlternativo}">
				<li class="fieldcontain">
					<span id="zonaUsoAlternativo-label" class="property-label"><g:message code="fichaCampo.zonaUsoAlternativo.label" default="Zona Uso Alternativo" /></span>
					
						<span class="property-value" aria-labelledby="zonaUsoAlternativo-label"><g:formatBoolean boolean="${fichaCampoInstance?.zonaUsoAlternativo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.valoracionVenta}">
				<li class="fieldcontain">
					<span id="valoracionVenta-label" class="property-label"><g:message code="fichaCampo.valoracionVenta.label" default="Valoracion Venta" /></span>
					
						<span class="property-value" aria-labelledby="valoracionVenta-label"><g:fieldValue bean="${fichaCampoInstance}" field="valoracionVenta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.valoracionOferta}">
				<li class="fieldcontain">
					<span id="valoracionOferta-label" class="property-label"><g:message code="fichaCampo.valoracionOferta.label" default="Valoracion Oferta" /></span>
					
						<span class="property-value" aria-labelledby="valoracionOferta-label"><g:fieldValue bean="${fichaCampoInstance}" field="valoracionOferta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.valoracionArriendo}">
				<li class="fieldcontain">
					<span id="valoracionArriendo-label" class="property-label"><g:message code="fichaCampo.valoracionArriendo.label" default="Valoracion Arriendo" /></span>
					
						<span class="property-value" aria-labelledby="valoracionArriendo-label"><g:fieldValue bean="${fichaCampoInstance}" field="valoracionArriendo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.registroFotografico}">
				<li class="fieldcontain">
					<span id="registroFotografico-label" class="property-label"><g:message code="fichaCampo.registroFotografico.label" default="Registro Fotografico" /></span>
					
						<span class="property-value" aria-labelledby="registroFotografico-label"><g:fieldValue bean="${fichaCampoInstance}" field="registroFotografico"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.observacion}">
				<li class="fieldcontain">
					<span id="observacion-label" class="property-label"><g:message code="fichaCampo.observacion.label" default="Observacion" /></span>
					
						<span class="property-value" aria-labelledby="observacion-label"><g:fieldValue bean="${fichaCampoInstance}" field="observacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.provincia}">
				<li class="fieldcontain">
					<span id="provincia-label" class="property-label"><g:message code="fichaCampo.provincia.label" default="Provincia" /></span>
					
						<span class="property-value" aria-labelledby="provincia-label"><g:link controller="DPA" action="show" id="${fichaCampoInstance?.provincia?.id}">${fichaCampoInstance?.provincia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.canton}">
				<li class="fieldcontain">
					<span id="canton-label" class="property-label"><g:message code="fichaCampo.canton.label" default="Canton" /></span>
					
						<span class="property-value" aria-labelledby="canton-label"><g:link controller="DPA" action="show" id="${fichaCampoInstance?.canton?.id}">${fichaCampoInstance?.canton?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.parroquia}">
				<li class="fieldcontain">
					<span id="parroquia-label" class="property-label"><g:message code="fichaCampo.parroquia.label" default="Parroquia" /></span>
					
						<span class="property-value" aria-labelledby="parroquia-label"><g:link controller="DPA" action="show" id="${fichaCampoInstance?.parroquia?.id}">${fichaCampoInstance?.parroquia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.fechaCreacion}">
				<li class="fieldcontain">
					<span id="fechaCreacion-label" class="property-label"><g:message code="fichaCampo.fechaCreacion.label" default="Fecha Creacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaCreacion-label"><g:formatDate date="${fichaCampoInstance?.fechaCreacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fichaCampoInstance?.fechaActualizacion}">
				<li class="fieldcontain">
					<span id="fechaActualizacion-label" class="property-label"><g:message code="fichaCampo.fechaActualizacion.label" default="Fecha Actualizacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaActualizacion-label"><g:formatDate date="${fichaCampoInstance?.fechaActualizacion}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:fichaCampoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${fichaCampoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
