<script type="text/javascript">
  function updateCobertura(coberturaInstanceId) {
     var serData = $('#cob' + coberturaInstanceId + ' form').serialize() ;
     $.ajax({
       type:'POST',
       data:serData,
       url:'/express/fichaCampo/updateCobertura',
       success:function(result) {
         if(result == 'error1') {
           alert('error, los valores de venta, oferta y arriendo son todos nulos . . . al menos uno debe tener valor');
         } else
         if(result == 'error2') {
           alert('error, debe seleccionar una cobertura');
         } else
         if(result == 'error3') {
           alert('error, debe seleccionar una tecnología predominante');
         } else {
           jQuery('#coberturas').html(result);
           intCobertura = false;
         }
       }
     });
   }
</script>
<g:form url="">
    <fieldset class="form">
      <g:hiddenField name="id" value="${coberturaInstance?.id}"/>
      <g:render template="formCobertura"/>
    </fieldset>
    <div class="options" style="top:4px;right:8px;">
      <input type="button" value="actualizar" onclick="updateCobertura('${coberturaInstance.id}');">
      <input type="button" value="cancelar" onclick="cancelEditCobertura(${coberturaInstance.id});">
    </div>
</g:form>
