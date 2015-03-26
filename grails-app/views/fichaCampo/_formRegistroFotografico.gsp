<div class="bloque" id="registroFotografico" style="overflow-x: hidden;">

  <div class="title" style="border-top:1px solid black;">V. REGISTRO FOTOGRÁFICO</div>

  <div style="float:left;width:30%;height:auto;">
    <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
      <%def path = new File("/var/fic/${fichaCampoInstance.codigoCatastral}")%>
      <uploadr:add name="${'uploadPhotoRW' + fichaCampoInstance.id}"
                   path="/var/fic/${fichaCampoInstance.codigoCatastral}"
                   direction="up"
                   maxVisible="4"
                   allowedExtensions="gif,giff,png,jpg,jpeg,JPEG,JPG,PNG,GIF,GIFF"
                   maxSize="1048576">
      </uploadr:add>
    </g:if>
    <g:each in="${photos}">
      <div style="clear:both;width:100%;display: table-cell;">
        <div class="toshow" style="width:80%;float:left;" onclick="viewPhoto('${claveCatastral}','${it}')">${it}</div>
        <div class="toshow" style="width:18%;float:right" onclick="deletePhoto('${claveCatastral}','${it}')">X</div>
      </div>
    </g:each>
    <div class="clearshow" onclick="clearPhoto()">ocultar la foto !</div>
  </div>

  <g:if test="${photos.size() > 0}">
    <div style="float:left;width:70%;height:auto;">
      <img id="photovw" src="" style="max-width:100%;height:auto;"/>
    </div>
  </g:if>

  <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
    <div class="options" style="top:3px;">
      <g:submitToRemote url="[action: 'editRegistroFotografico',id:fichaCampoInstance.id]" update="registroFotografico" value="Editar"/>
    </div>
  </g:if>

  <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
    <div class="options" style="top:3px;">
      <g:submitToRemote url="[action: 'updateRegistroFotografico',id:fichaCampoInstance.id]" update="registroFotografico" value="aceptar"/>
      <!-- <g:submitToRemote url="[action: 'cancelRegistroFotografico',id:fichaCampoInstance.id]" update="registroFotografico" value="cancelar"/>  -->
    </div>
  </g:if>

</div>
