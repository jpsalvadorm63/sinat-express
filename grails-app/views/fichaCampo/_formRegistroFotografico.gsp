<div class="bloque" id="registroFotografico">
  <div class="title" style="border-top: 1px solid black;">V. REGISTRO FOTOGRÁFICO</div>

  <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'observaciones', 'error')} " style="padding: 0 16px 16px 16px;">
    <div class="label">Ubicación:</div>
    <g:textField style="margin:0;width:99%;" disabled="${showing}" name="registroFotografico" maxlength="240" value="${fichaCampoInstance?.registroFotografico}"/>
  </div>

  <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
    <div class="options" style="top:3px;">
      <g:submitToRemote url="[action: 'editRegistroFotografico',id:fichaCampoInstance.id]" update="registroFotografico" value="Editar"/>
    </div>
  </g:if>

  <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
    <div class="options" style="top:3px;">
      <g:submitToRemote url="[action: 'updateRegistroFotografico',id:fichaCampoInstance.id]" update="registroFotografico" value="aceptar"/>
      <g:submitToRemote url="[action: 'cancelRegistroFotografico',id:fichaCampoInstance.id]" update="registroFotografico" value="cancelar"/>
    </div>
  </g:if>
</div>
