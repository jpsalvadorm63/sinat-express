<g:render template="formHabitacional" model="[habitacionalInstance:habitacionalInstance]" />
<div class="opts" style="top:4px;right:8px">
  <input type="button" value="editar" onclick="editHabitacional(${habitacionalInstance.id},'hab${habitacionalInstance.id}');">
  <input type="button" value="borrar" onclick="deleteHabitacional(${habitacionalInstance.id});">
</div>

