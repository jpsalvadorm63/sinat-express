
<script type="text/javascript">

  function changeTipoCobertura(tipoCoberturaId,coberturaInstanceId) {
    var element ;
    if(coberturaInstanceId)
      element = "#cob" + coberturaInstanceId ;
    else
      element = "#intCobertura" ;
    var selector = element + " select#tipoCobertura option[value='" + tipoCoberturaId + "']"
    var tipoCovertura = $(selector).text() ;
    habilitarOtro(tipoCovertura,element);
  }

  function habilitarOtro(tipoCobertura,element) {
    var idx = tipoCobertura.toUpperCase().indexOf('OTR');
    if(idx == 0) {
      $(element + ' #otros').removeClass('desabilitado');
    } else {
      $(element + ' #otros').addClass('desabilitado');
    }
  }

  function addCobertura() {
    if(intCobertura) {
      alert('Operación NO permitida, ya esta creando o editando una cobertura');
    } else {
      $.ajax({
        url: '/express/fichaCampo/createCobertura',
        data: {id: fichaCampoInstanceId},
        success: function (result) {
          $("#intCobertura").html(result).addClass('ingresandoCobertura');
          intCobertura = true;
        }
      });
    }
  };

  function cancelCreateCobertura() {
    $('#intCobertura').html('').removeClass();
    intCobertura = false;
  }

  function editCobertura(coberturaInstanceId,coberturaDiv) {
    if(intCobertura) {
      alert('Operación NO permitida, ya esta creando o editando una cobertura');
    } else {
      $.ajax({
        url: '/express/fichaCampo/editCobertura',
        data: {id: coberturaInstanceId},
        success: function (result) {
          $('#' + coberturaDiv).html(result).addClass('ingresandoCobertura');
          intCobertura = true;
        }
      });
    }
  }

  function cancelEditCobertura(coberturaInstanceId) {
    $.ajax({
      url:'/express/fichaCampo/cancelEditCobertura',
      data:{id:coberturaInstanceId},
      success: function(result){
        jQuery('#coberturas').html(result);
        intCobertura = false;
      }
    });
  }

  function deleteCobertura(coberturaInstanceId,tipoCobertura) {
    if(confirm('Se borrará la cobertura: ' + tipoCobertura)) {
      $.ajax({
        url:'/express/fichaCampo/deleteCobertura',
        data:{id:coberturaInstanceId},
        success: function(result){
          $("#coberturas").html(result);
        }
      });
    }
  }

  function changeTipoUso(tipo,coberturaInstanceId) {
    $.ajax({
      data:{id:tipo,coberturaInstanceId:coberturaInstanceId},
      url:'/express/fichaCampo/tiposDeCoberturas',
      success:function(result) {
        $('#ajaxCoberturas').html(result);
      }
    });

    $.ajax({
      data:{id:tipo, coberturaInstanceId:coberturaInstanceId},
      url:'/express/fichaCampo/tiposTecnologiaPredominante',
      success:function(result) {
        jQuery('#ajaxTecnologiasPredominantes').html(result);
      }
    });

    $.ajax({
      data:{tipoUso:tipo,coberturaInstanceId:coberturaInstanceId},
      url:'/express/fichaCampo/onChangeTipoUso',
      success:function(result) {
        jQuery('div#jsgenerated').html(result);
        onTipoUsoChange() ;
      }
    });
  }
</script>

<div id="intCobertura"></div>

<g:each in="${coberturaInstanceList}" status="i" var="coberturaInstance">
  <div class="bloque partbloque" id="cob${coberturaInstance.id}" style="position:relative;">
    <g:render
        template="formCobertura"
        model="${[coberturaInstance:coberturaInstance,showing:'true',
                  coberturas0:sinat.express.TipoCobertura.findAllByTipoUso(coberturaInstance.tipoUso),
                  deshabilitarOtros:((coberturaInstance.tipoCobertura != null)?coberturaInstance.tipoCobertura.nombre[0..2]:'XXX') != 'OTR'  ]}"/>
    <div class="opts" style="top:4px;right:8px">
      <input type="button" value="editar" onclick="editCobertura(${coberturaInstance.id},'cob${coberturaInstance.id}');">
      <input type="button" value="borrar" onclick="deleteCobertura(${coberturaInstance.id},'${coberturaInstance}');">
    </div>
  </div>
</g:each>

<div class="options" style="top:3px;">
  <div class="boton" id="addCobertura" onclick="addCobertura();">+</div>
</div>

<div id="jsgenerated" style="display: none;"></div>
