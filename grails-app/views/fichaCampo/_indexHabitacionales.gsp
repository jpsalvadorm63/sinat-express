
<div id="intHabitacional"></div>

<g:each in="${habitacionalInstanceList}" status="i" var="habitacionalInstance">
  <div class="bloque partbloque" id="hab${habitacionalInstance.id}" style="position:relative;">
    <g:render template="formHabitacional" model="[habitacionalInstance:habitacionalInstance]" />
    <div class="opts" style="top:4px;right:8px">
      <input type="button" value="editar" onclick="editHabitacional(${habitacionalInstance.id},'hab${habitacionalInstance.id}');">
      <input type="button" value="borrar" onclick="deleteHabitacional(${habitacionalInstance.id});">
    </div>
  </div>
</g:each>

<div class="options" style="top:3px;">
  <div class="boton" id="addHabitacional" onclick="addHabitacional();">+</div>
</div>
