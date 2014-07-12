<%@ page import="sinat.express.Cobertura" %>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'fichaCampo', 'error')} required">
	<label for="fichaCampo">
		<g:message code="cobertura.fichaCampo.label" default="Ficha Campo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fichaCampo" name="fichaCampo.id" from="${sinat.express.FichaCampo.list()}" optionKey="id" required="" value="${coberturaInstance?.fichaCampo?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'cobertura', 'error')} ">
	<label for="cobertura">
		<g:message code="cobertura.cobertura.label" default="Cobertura" />
		
	</label>
	<g:textField name="cobertura" maxlength="64" value="${coberturaInstance?.cobertura}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'superficie', 'error')} ">
	<label for="superficie">
		<g:message code="cobertura.superficie.label" default="Superficie" />
		
	</label>
	<g:field name="superficie" value="${fieldValue(bean: coberturaInstance, field: 'superficie')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'rendimiento', 'error')} ">
	<label for="rendimiento">
		<g:message code="cobertura.rendimiento.label" default="Rendimiento" />
		
	</label>
	<g:textField name="rendimiento" maxlength="64" value="${coberturaInstance?.rendimiento}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'cargaAnimal', 'error')} ">
	<label for="cargaAnimal">
		<g:message code="cobertura.cargaAnimal.label" default="Carga Animal" />
		
	</label>
	<g:field name="cargaAnimal" type="number" value="${coberturaInstance.cargaAnimal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'rotacion', 'error')} ">
	<label for="rotacion">
		<g:message code="cobertura.rotacion.label" default="Rotacion" />
		
	</label>
	<g:textField name="rotacion" maxlength="128" value="${coberturaInstance?.rotacion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'cosechasPorAnio', 'error')} ">
	<label for="cosechasPorAnio">
		<g:message code="cobertura.cosechasPorAnio.label" default="Cosechas Por Anio" />
		
	</label>
	<g:field name="cosechasPorAnio" type="number" value="${coberturaInstance.cosechasPorAnio}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'tecnologiaPredominante', 'error')} ">
	<label for="tecnologiaPredominante">
		<g:message code="cobertura.tecnologiaPredominante.label" default="Tecnologia Predominante" />
		
	</label>
	<g:select name="tecnologiaPredominante" from="${coberturaInstance.constraints.tecnologiaPredominante.inList}" value="${coberturaInstance?.tecnologiaPredominante}" valueMessagePrefix="cobertura.tecnologiaPredominante" noSelection="['': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'sistemaDeRiego', 'error')} ">
	<label for="sistemaDeRiego">
		<g:message code="cobertura.sistemaDeRiego.label" default="Sistema De Riego" />
		
	</label>
	<g:select name="sistemaDeRiego" from="${coberturaInstance.constraints.sistemaDeRiego.inList}" value="${coberturaInstance?.sistemaDeRiego}" valueMessagePrefix="cobertura.sistemaDeRiego" noSelection="['': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'mecanizacion', 'error')} ">
	<label for="mecanizacion">
		<g:message code="cobertura.mecanizacion.label" default="Mecanizacion" />
		
	</label>
	<g:select name="mecanizacion" from="${coberturaInstance.constraints.mecanizacion.inList}" value="${coberturaInstance?.mecanizacion}" valueMessagePrefix="cobertura.mecanizacion" noSelection="['': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'fechaCreacion', 'error')} required">
	<label for="fechaCreacion">
		<g:message code="cobertura.fechaCreacion.label" default="Fecha Creacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaCreacion" precision="day"  value="${coberturaInstance?.fechaCreacion}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: coberturaInstance, field: 'fechaActualizacion', 'error')} required">
	<label for="fechaActualizacion">
		<g:message code="cobertura.fechaActualizacion.label" default="Fecha Actualizacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaActualizacion" precision="day"  value="${coberturaInstance?.fechaActualizacion}"  />

</div>

