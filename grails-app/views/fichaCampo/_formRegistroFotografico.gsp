<div class="bloque" id="registroFotografico" style="overflow-x: hidden;">

  <div class="title" style="border-top:1px solid black;">V. REGISTRO FOTOGRÁFICO</div>

  <div class="photos">

    <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
      <g:each in="${photos}">
        <div class='photo'>
          <div class="photoFile" onclick="viewPhoto('${claveCatastral}','${it}')">${it}</div>
          <div class="deleteFile" onclick="deletePhoto('${claveCatastral}','${it}')">X</div>
        </div>
      </g:each>
    </g:if>

    <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
      <g:each in="${photos}">
        <div class="photoFile2" onclick="viewPhoto('${claveCatastral}','${it}')">${it}</div>
      </g:each>
    </g:if>

    <div class="photoFile2" style='text-align: center;font-weight: bold;' onclick="clearPhoto()">ocultar la foto !</div>

    <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
      <%def path = new File("/var/fic/${fichaCampoInstance.codigoCatastral}")%>
      <uploadr:add name="${'uploadPhotoRW' + fichaCampoInstance.id}"
                   path="/var/fic/${fichaCampoInstance.codigoCatastral}"
                   direction="up"
                   maxVisible="4"
                   allowedExtensions="gif,giff,png,jpg,jpeg,JPEG,JPG,PNG,GIF,GIFF"
                   maxSize="1048576"
                   style="width:90%;">
      </uploadr:add>
    </g:if>

  </div>

  <g:if test="${photos.size() > 0}">
    <div id="photoContainer">
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
