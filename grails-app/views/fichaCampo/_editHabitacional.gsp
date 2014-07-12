<g:form url="[action: 'updateHabitacional']">
    <fieldset class="form">
      <g:hiddenField name="id" value="${habitacionalInstance?.id}"/>
      <g:render template="formHabitacional" />
    </fieldset>
    <div class="opts" style="top:4px;right:8px;">
      <input type="button" value="actualizar" onclick="updateHabitacional('hab${habitacionalInstance?.id}',${habitacionalInstance?.id});" />
      <input type="button" value="cancelar" onclick="cancelUpdateHabitacional('hab${habitacionalInstance?.id}',${habitacionalInstance?.id});" />
    </div>
</g:form>
