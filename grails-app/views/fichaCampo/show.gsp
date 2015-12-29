<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'fichaCampo.label', default: 'FichaCampo')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">

    <link type="text/css" href="${resource(dir: 'css', file: 'fichaCampo.css')}" rel="stylesheet" />
    <link type="text/css" href="${resource(dir: 'menu', file: 'menu.css')}" rel="stylesheet" />
    <script type="text/javascript" src="${resource(dir: 'jquery', file: 'jquery-1.11.0.min.js')}"></script>

    <script type="text/css" src="${resource(dir: 'jquery-ui/development-bundle/themes/ui-lightness', file: 'jquery-ui-1.10.4.custom.css')}"></script>
    <script type="text/javascript" src="${resource(dir: 'jquery-ui/js', file: 'jquery-ui-1.10.4.custom.min.js')}"></script>
    <script type="text/css" src="${resource(dir: 'jquery-ui/development-bundle/themes/ui-lightness', file: 'jquery-ui.css')}"></script>
    <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>

    <script language="JavaScript">

        var fichaCampoInstanceId = ${fichaCampoInstance?.id} ;
        var indicadorDeOtrosCultivosAgricolas = false ;
        var intHabitacional = false ;
        var intCobertura = false ;

        function fechaHora() {
            setTimeout(fechaHora,60000);
            var fecha = (new Date()).toLocaleDateString();
            $.ajax({
                url:'/express/fichaCampo/ahora',
                success: function(result) {
                $("#fechaServer").html(result);
                }
            });
        }

        function ajaxCantones(data) {
            document.getElementById('ajaxParroquias').innerHTML = '<select id=\'parroquia\' class=\'many-to-one\' required=\'\' name=\'parroquia.id\' style=\'width:160px;\'></select>' ;
            document.getElementById('ajaxCantones').innerHTML = data;
        }

        function ajaxParroquias(data) {
            document.getElementById('ajaxParroquias').innerHTML = data;
        }

        function showBloque(elementName) {
            document.getElementById(elementName).style.display = "block";
        }

        function iluminar(elementName,mycolor) {
            document.getElementById(elementName).style.backgroundColor = mycolor;
        }

        function noIluminar(elementName) {
            document.getElementById(elementName).style.backgroundColor = document.getElementById('fichaCampo').style.backgroundColor ;
        }

        function ajaxCoberturas(data) {
            document.getElementById('ajaxCoberturas').innerHTML = data;
        }

        function addHabitacional() {
            if(intHabitacional) {
                alert('Operación NO permitida, ya esta creando o editando un registro habitacional');
            } else {
                intHabitacional = true;
                $.ajax({
                    url:'/express/fichaCampo/createHabitacional',
                    data:{id:fichaCampoInstanceId},
                    error: function() {
                        intHabitacional = false;
                    },
                    success: function(result) {
                        $("#intHabitacional").html(result);
                        $('#intHabitacional').addClass('ingrsandoHabitacional');
                    }
                });
            }
        }

        function createHabitacional() {
            var serData = $('#intHabitacional form').serialize()  ;
            $.ajax({
                url:'/express/fichaCampo/saveHabitacional',
                data:serData,
                success: function(result) {
                    if(result == 'error1') {
                        alert('error, los valores de venta, oferta y arriendo son todos nulos . . . al menos uno debe tener valor');
                    } else {
                    $("#habitacionales").html(result);
                    $('#intHabitacional').removeClass('ingrsandoHabitacional');
                        intHabitacional = false ;
                    }
                }
            });
        }

        function cancelUpdateHabitacional(divHab,habitacionalInstanceId) {
            if(intHabitacional) {
                $.ajax({
                    url:'/express/fichaCampo/cancelUpdateHabitacional',
                    data:{habitacionalInstanceId:habitacionalInstanceId},
                    success: function(result) {
                        $("#habitacionales").html(result);
                    }
                });
                intHabitacional = false ;
            }
        }

        function cancelHabitacional() {
            if(intHabitacional) {
                $("#intHabitacional").html('');
                intHabitacional = false ;
            }
        }

        function editHabitacional(habitacionalInstanceId,divHab) {
            if(intHabitacional) {
                alert('Operación NO permitida, ya esta creando o editando un registro habitacional');
            } else {
                $.ajax({
                    url: '/express/fichaCampo/editHabitacional',
                    data: {id: habitacionalInstanceId},
                    success: function (result) {
                    $("#" + divHab).html(result).addClass('ingrsandoHabitacional');
                        intHabitacional = true;
                    }
                });
            }
        }

        function deleteHabitacional(habitacionalInstanceId) {
            if(confirm('Se borrará el habitacional')) {
                $.ajax({
                    url:'/express/fichaCampo/deleteHabitacional',
                    data:{id:habitacionalInstanceId},
                    success: function(result){
                        $("#habitacionales").html(result);
                    }
                });
            }
        }

        function updateHabitacional(divHab,habitacionalInstanceId) {
            var serData = $('#' + divHab + ' form').serialize() ;
            $.ajax({
                type:'POST',
                data:serData,
                url:'/express/fichaCampo/updateHabitacional',
                    success:function(result) {
                    if(result == 'error1') {
                        alert('error, los valores de venta, oferta y arriendo son todos nulos . . . al menos uno debe tener valor');
                    } else {
                        jQuery('#' + divHab).html(result).removeClass('ingrsandoHabitacional');
                        intHabitacional = false;
                    }
                }
            });
        }

        function viewPhoto(cc,photoFile) {
            //$('#photoContainer').attr('visibility','visible');
            $('img#photovw').attr('src','/express/fichaCampo/displayPhoto?cc=' + cc + '&photo=' + photoFile);
        }

        function deletePhoto(cc,photoFile) {
            if (confirm("¿Desea borrar foto " + photoFile + ", correspondioente al predio " + cc + " ?") == true) {
                $.ajax({
                    type:'POST',
                    data:{cc:cc,photo:photoFile},
                    url:'/express/fichaCampo/deletePhoto',
                    success:function(result) {
                        alert(result.msg)
                    }
                });
            }
            clearPhoto();
        }

        function clearPhoto() {
            $('img#photovw').attr('src','');
            $('div#photoContainer').attr('visibility','hidden');
        }

        function isNumberKey(evt){
            var charCode = (evt.which) ? evt.which : event.keyCode
            if(charCode == 44)
                return true;
            else
                return !(charCode > 31 && (charCode < 48 || charCode > 57));
        }

        function isIntegerKey(evt){
            var charCode = (evt.which) ? evt.which : event.keyCode
            return !(charCode > 31 && (charCode < 48 || charCode > 57));
        }

    </script>

    <style>

        input, select, textarea {
          color: black;
        }

        input[type=checkbox] {
          color:black;
        }

        .ui-widget-overlay {
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 1120px;
          background: silver;
          opacity: 0.8;
        }

        .ui-dialog-content {
          background-color: lightgoldenrodyellow;
          min-height: 16px;
          border: 1px darkgray solid ;
          padding: 4px;
        }

        div.boton {
          float:right;
          cursor:pointer;
          background-color:#FCFCFC;
          border:1px solid #444444;
          border-radius:3px;
          font-size:11px;
          padding:1px 6px 1px 6px;
        }

        .desabilitado {
          visibility:hidden;
        }

        .test2 {
          margin:8px;
          border: 4px gray solid;
          width:98%;
          min-height:24px;
        }

        input[type=checkbox][disabled] {
          /*outline:1px solid dar;*/
        }

        .clearshow,
        .showing,
        .toshow {
          color: white;
          border-radius: 4px;
          text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
          padding: 8px;
          margin: 2px;
          font-size: 120%;
          height: 16px;
        }

        .showing {
          background: rgb(202, 60, 60); /* this is a maroon */
        }

        .toshow {
          background: rgb(28, 184, 65); /* this is a green */
        }

        .clearshow {
          background: rgb(66, 184, 221); /* this is a light blue */
        }

        .photos {
          width:30%;
          max-width:30%;
          padding:0;
          margin:0;
          float:left;
        }

        .photoDisplay {
          width:70%;
          max-width:70%;
          padding:0;
          margin:0;
          background-color:green;
          float:right;
        }

        .photo {
          min-height: 16px;
          margin: 0px;
          padding: 0px;
          display: -webkit-flex;
          display: flex;
          width: 100%;
        }

        .photoFile {
          float:left;
          margin: 4px 2px 4px 4px;
          padding: 2px;
          border: 1px solid #cccc33;
          border-radius: 4pt;
          background: #dddd88;
          text-align:left;
          cursor: pointer;
          width:90%;
        }

        .photoFile2 {
          float:left;
          margin: 4px 4px 4px 4px;
          padding: 2px;
          border: 1px solid #cccc33;
          border-radius: 4pt;
          background: #dddd88;
          text-align:left;
          cursor: pointer;
          width:94%;
        }

        .deleteFile {
          float:right;
          margin: 4px 4px 4px 2px;
          padding: 2px;
          border: 1px solid #8888bb;
          border-radius: 4pt;
          background: #ccccff;
          text-align: center;
          cursor: pointer;
          width:10%;
        }

        #photoContainer {
          float:left;
          width:70%;
          height:auto;
          /*visibility: hidden;*/
        }

    </style>

    <asset:javascript src="uploadr.manifest.js"/>
    <asset:javascript src="uploadr.demo.manifest.js"/>
    <asset:stylesheet href="uploadr.manifest.css"/>
    <asset:stylesheet href="uploadr.demo.manifest.css"/>

</head>
<body>

    <a href="#create-fichaCampo" class="skip" tabindex="-1">
        <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
    </a>

    <div id="menu">
        <ul class="menu">
            <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
            <li><g:link class="create" action="create"><span>Crear Ficha</span></g:link></li>
            <li><g:link class="list" action="index"><span>Fichas de Investigación de Campo</span></g:link></li>
        </ul>
    </div>

    <div id="fichaCampo" class="content scaffold-create" role="main">
      <div class="metadato">
        <div class="mdlabel" style="width:5%;">Forma</div>
        <div class="mddata" style="width:25%;">FICHA DE INVESTIGACIÓN DE CAMPO</div>
        <div class="mdlabel">Operación</div>   <div class="mddata" style="width:10%;">${opname}</div>
        <div class="mdlabel">Usuario</div>     <div class="mddata"><sec:username/></div>
        <div class="mdlabel">Hoy es</div> <div class="mddata">${new Date().format("ddMMMyyy, hh:mm a").toUpperCase()}</div>
      </div>

      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${fichaCampoInstance}">
        <ul class="errors" role="alert">
        <g:eachError bean="${fichaCampoInstance}" var="error">
          <li<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
        </ul>
      </g:hasErrors>

      <fieldset class="form">
        <g:render template="form"/>
      </fieldset>
      <fieldset class="form">
        <g:render template="formCaracteristicasPredio"/>
      </fieldset>
      <fieldset class="form">
        <div class="bloque">
          <div class="title" style="border-top: 1px solid black;">III. DESCRIPCION DE COBERTURAS</div>
          <div class="bloque" id="coberturas">
            <g:render template="indexCoberturas"/>
          </div>
        </div>
      </fieldset>
      <fieldset class="form">
        <div class="bloque">
          <div class="title" style="border-top: 1px solid black;">IV. HABITACIONAL</div>
          <div class="bloque" id="habitacionales">
            <g:render template="indexHabitacionales"/>
          </div>
        </div>
      </fieldset>
      <fieldset class="form">
        <g:render template="formRegistroFotografico"/>
      </fieldset>
      <fieldset class="form">
        <g:render template="formObservaciones"/>
      </fieldset>

    </div>

</body>
</html>
