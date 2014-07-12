<html>
<head>
  <meta name='layout' content='main'/>
  <title><g:message code="springSecurity.login.title"/></title>
  <style type='text/css' media='screen'>
  #login {
    margin: 15px 0px;
    padding: 0px;
    text-align: center;
  }

  #login .inner {
    width: 340px;
    padding-bottom: 6px;
    margin: 60px auto;
    text-align: left;
    border:1px solid #a9a9a9;
    -moz-box-shadow: 4px 4px 4px #ccc;
    -webkit-box-shadow: 4px 4px 4px #ccc;
    -khtml-box-shadow: 4px 4px 4px #ccc;
    box-shadow: 4px 4px 4px #ccc;
    border-radius: 12px;
    background:#f5f5f5 url(${resource(dir: 'images', file: 'empresa.png')}) no-repeat center 8px;-moz-border-radius: 16px;
  }

  #login .inner .fheader {
    padding: 18px 26px 14px 26px;
    background-color: #f7f7ff;
    margin: 0px 0 14px 0;
    color: #2e3741;
    font-size: 18px;
    font-weight: bold;
  }

  #login .inner .cssform p {
    clear: left;
    margin: 0;
    padding: 4px 0 3px 0;
    padding-left: 105px;
    margin-bottom: 20px;
    height: 1%;
  }

  #login .inner .cssform input[type='text'] {
    width: 120px;
  }

  #login .inner .cssform label {
    font-weight: bold;
    float: left;
    text-align: right;
    margin-left: -105px;
    width: 110px;
    padding-top: 3px;
    padding-right: 10px;
  }

  #login #remember_me_holder {
    padding-left: 120px;
  }

  #login #submit {
    margin-left: 15px;
  }

  #login #remember_me_holder label {
    float: none;
    margin-left: 0;
    text-align: left;
    width: 200px
  }

  #login .inner .login_message {
    padding: 6px 25px 20px 25px;
    color: #c33;
  }

  #login .inner .text_ {
    width: 120px;
  }

  #login .inner .chk {
    height: 12px;
  }
  </style>
</head>

<body>
<div id="menu"><ul class="menu"></ul></div>
<div id='login'>
  <div class='inner'>
    <div style="text-align:center;font-size:16px;font-weight:bolder;margin: 104px 0 16px 0">Cambio de Clave</div>

    <g:if test='${flash.message}'>
      <div class='login_message' style="text-align: center;">${flash.message}</div>
    </g:if>

    <form method='POST' id='loginForm' class='cssform' autocomplete='off'>
      <g:hiddenField name="modo" value="forma" />
      <p>
        <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
        <input type='text' class='text_' name='j_username' readonly="true" id='username' value="${usuario}"/>
      </p>

      <p>
        <label for='oldPassword'>Clave Actual:</label>
        <input type='password' class='text_' name='j_oldPassword' id='oldPassword'/>
      </p>

      <p>
        <label for='newPassword1'>Nueva Clave:</label>
        <input type='password' class='text_' name='j_newPassword1' id='newPassword1'/>
      </p>

      <p>
        <label for='newPassword2'>Repita Nueva Clave:</label>
        <input type='password' class='text_' name='j_newPassword2' id='newPassword2'/>
      </p>

      <p>
        <input type='submit' id="submit" value=' ACEPTAR ! '/>
      </p>

    </form>
  </div>
</div>
<script type='text/javascript'>
  <!--
  (function() {
    document.forms['loginForm'].elements['j_oldPassword'].focus();
  })();
  // -->
</script>
</body>
</html>
