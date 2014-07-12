<div class="bloque" id="observaciones">
  <div class="title" style="border-top: 1px solid black;white-space:nowrap;">VI. OBSERVACIONES</div>

  <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'observaciones', 'error')} " style="padding: 0 16px 16px 16px;">
    <div class="label">Observaciones:</div>
    <g:textArea style="margin:0;width:99%;height:96px;" disabled="${showing}" name="observaciones" maxlength="800" value="${fichaCampoInstance?.observaciones}"/>
  </div>

  <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
    <div class="options" style="top:3px;">
      <g:submitToRemote url="[action: 'editObservaciones',id:fichaCampoInstance.id]" update="observaciones" value="Editar"/>
    </div>
  </g:if>

  <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
    <div class="options" style="top:3px;">
      <g:submitToRemote url="[action: 'updateObservaciones',id:fichaCampoInstance.id]" update="observaciones" value="aceptar"/>
      <g:submitToRemote url="[action: 'cancelObservaciones',id:fichaCampoInstance.id]" update="observaciones" value="cancelar"/>
    </div>
  </g:if>

</div>
