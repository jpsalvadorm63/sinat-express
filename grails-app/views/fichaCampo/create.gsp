<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'fichaCampo.label', default: 'FichaCampo')}"/>
  <title><g:message code="default.create.label" args="[entityName]"/></title>

  <script language="JavaScript">
    function ajaxCantones(data) {
      document.getElementById('ajaxParroquias').innerHTML = '<select id=\'parroquia\' class=\'many-to-one\' required=\'\' name=\'parroquia.id\' style=\'width:160px;\'></select>' ;
      document.getElementById('ajaxCantones').innerHTML = data;
    }
    function ajaxParroquias(data) {
      document.getElementById('ajaxParroquias').innerHTML = data;
    }
  </script>
  <link type="text/css" href="${resource(dir: 'css', file: 'fichaCampo.css')}" rel="stylesheet" />
  <link type="text/css" href="${resource(dir: 'menu', file: 'menu.css')}" rel="stylesheet" />
  <script type="text/javascript" src="${resource(dir: 'menu', file: 'jquery.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
</head>
<body>

<a href="#create-fichaCampo" class="skip" tabindex="-1">
  <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div id="menu">
  <ul class="menu">
    <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
    <li><g:link class="list" action="index"><span>Fichas de Investigación de Campo</span></g:link></li>
  </ul>
</div>

<!--<li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
    <li><g:link class="list" action="index"><span>Fichas de Campo</span></g:link></li>-->

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
  <g:form url="[resource: fichaCampoInstance, action: 'save']">
    <fieldset class="form">
      <g:hiddenField name="fechaCreacion" value="${g.formatDate(value:fichaCampoInstance?.fechaCreacion,format:'yyyy-MM-dd HH:mm:ss')}" />
      <g:hiddenField name="fechaActualizacion" value="${g.formatDate(value:fichaCampoInstance?.fechaActualizacion,format:'yyyy-MM-dd HH:mm:ss')}" />
      <g:render template="form"/>
    </fieldset>
    <fieldset class="buttons">
      <g:submitButton name="create" class="save" value="Crear Ficha"/>
    </fieldset>
  </g:form>
</div>
</body>
</html>
