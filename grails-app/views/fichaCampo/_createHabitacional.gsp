<g:form url="[action: 'saveHabitacional']">
  <div class="bloque" style="position:relative;">
    <fieldset class="form">
      <g:hiddenField name="fichaCampo.id" value="${fichaCampoInstance?.id}"/>
      <g:hiddenField name="fechaCreacion" value="${g.formatDate(value:habitacionalInstance?.fechaCreacion,format:'yyyy-MM-dd HH:mm:ss')}" />
      <g:hiddenField name="fechaActualizacion" value="${g.formatDate(habitacionalInstance?.fechaActualizacion,format:'yyyy-MM-dd HH:mm:ss')}" />
      <g:render template="formHabitacional" />
    </fieldset>
    <div class="opts">
      <input type="button" value="aceptar" onclick="createHabitacional();">
      <input type="button" value="cancelar" onclick="cancelHabitacional();">
    </div>
  </div>
</g:form>
