<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>

		<g:javascript library="application"/>

    <script>
      var myVar=setInterval(function(){myTimer()},60000);
      function myTimer() {
        $('#ping').html('<span style="font-weight:bold;">ping !</span>');
        $.ajax({
          url:'/express/fichaCampo/ping',
          success: function(data) {
            $('#ping').html(data);
          },
          error: function(data) {
            $('#ping').html('<span style="color:red;font-weight:bold;">error!</span>');
          }
        });
      }
    </script>



    <style>

      body {
        /*background: url("${resource(dir: 'images/empresa', file: 'box-bg.png')}") repeat;*/
      }

      div.main_container {
        margin: 0 auto;
        width: 900px;
        min-height: 1080px;
        border:0;
        background : transparent url(${resource(dir: 'images', file: 'empresa/LogoColor.png')}) no-repeat top 32px right 24px;
      }

      div.main_top {
        clear: both;
        border-width:0;
        height:100px;
        width:100%;
      }

      div.app_top {
        height : 120px;
        text-align : right;
        margin : 0;
        width : 100%;
        clear : both;
        color: darkgray;
        padding-top: 4px;
        padding-right: 8px;
      }

      div.app_top span {
        font-weight : bolder;
        color: gray;
      }

      div.app_top a {
        color : gray;
        text-decoration:none;
        border-bottom:1px #ffff00 dotted;
        /*font-weight : bolder;*/
        margin:0 4px 0 4px;
      }

      div#menu {
        margin-bottom: 16px;
        margin-left: 4px;
      }

    </style>
	</head>
	<body onload="${pageProperty(name:'body.onload')}">
  <div class="main_container">
    <div class="main_top" >
      <div class="app_top">
        <div style="margin-left:30%; text-align: right;">
          Aplicacion: <span><g:meta name="app.name"/> <g:meta name="app.version"/></span> |
          <g:if test="${util.AppSession.getSessionVar(session.id,'canton') != null}">
            Provincia:
            <span>${externos.DPALP.findByCodigo(util.AppSession.getSessionVar(session.id,'canton')).superior?.nombre?.toLowerCase().capitalize()}</span> |
            Cantón:
            <span>${externos.DPALP.findByCodigo(util.AppSession.getSessionVar(session.id,'canton'))?.nombre?.toLowerCase().capitalize()}</span> |
          </g:if>
          <sec:ifLoggedIn>
            Usuario: <span><sec:username/></span> |
            <g:link controller="logout">Terminar Sesión</g:link>
          </sec:ifLoggedIn>
          <!--
          <sec:access expression="!hasRole('ROLE_ADMIN')">
            | <g:link controller="login" action="changePass2">Cambiar clave</g:link>
          </sec:access>
          -->
          <sec:ifNotLoggedIn>
            <g:link controller='login' action='auth'>Ingresar al Sistema</g:link>
          </sec:ifNotLoggedIn>
          | net: <span id="ping">+</span>
        </div>
      </div>
    </div>

    <g:layoutBody/>

    <r:layoutResources />
  </div>

	</body>
</html>



