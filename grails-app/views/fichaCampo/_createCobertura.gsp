<script type="text/javascript">
  function createCobertura() {
    var serData = $('#intCobertura form').serialize() ;
    $.ajax({
      type:'POST',
      data:serData,
      url:'/express/fichaCampo/saveCobertura',
      success:function(result) {
        if(result == 'error0') {
          alert('error, no ha seleccionado un Tipo de Uso');
        } else
        if(result == 'error1') {
          alert('error, los valores de venta, oferta y arriendo son todos nulos . . . al menos uno debe tener valor');
        } else
        if(result == 'error2') {
          alert('error, debe seleccionar una cobertura');
        } else
        if(result == 'error3') {
          alert('error, debe seleccionar una tecnología predominante');
        } else {
          intCobertura = false ;
          $("#coberturas").html(result);
        }
      }
    });

  }
</script>
<g:form url="[action: 'saveCobertura']">
  <div class="bloque" style="position:relative;">
    <fieldset class="form">
      <g:hiddenField name="fechaCreacion" value="${g.formatDate(value:coberturaInstance?.fechaCreacion,format:'yyyy-MM-dd HH:mm:ss')}" />
      <g:hiddenField name="fechaActualizacion" value="${g.formatDate(coberturaInstance?.fechaActualizacion,format:'yyyy-MM-dd HH:mm:ss')}" />
      <g:hiddenField name="fichaCampo.id" value="${fichaCampoInstance?.id}"/>
      <g:render template="formCobertura"/>
    </fieldset>
    <div class="opts">
      <input type="button" value="aceptar" onclick="createCobertura();">
      <input type="button" value="cancelar" onclick="cancelCreateCobertura();">
    </div>
  </div>
</g:form>
