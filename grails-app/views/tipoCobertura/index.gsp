
<%@ page import="sinat.express.TipoCobertura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoCobertura.label', default: 'TipoCobertura')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    <style>
    .scaffold-list {
      border : 1px #000000 solid;
      margin : 4px 4px 4px 4px;
      padding : 4px 8px 8px 8px;
      float : left;
      width : 30%;
      -moz-border-radius: 6px;
      border-radius: 6px;
    }

    scaffold-list h1 {
      text-align: center;
      font-weight: bolder;
    }

    .link1 {
      font-weight: bolder;
      color: darkgreen;
    }

    h1 {
      text-align: center;
      font-weight: bolder;
    }
    </style>
    <link type="text/css" href="${resource(dir: 'menu', file: 'menu.css')}" rel="stylesheet" />
    <script type="text/javascript" src="${resource(dir: 'menu', file: 'jquery.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
  </head>
	<body>
		<a href="#list-tipoCobertura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

    <div id="menu">
      <ul class="menu">
        <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
      </ul>
    </div>

		<div id="list-tipoCobertura" class="content scaffold-list" role="main" style="width:40%">
			<h1>Tipos de Cobertura Vegetal</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="codigo" title="Código" />
						<g:sortableColumn property="nombre" title="${message(code: 'tipoCobertura.nombre.label', default: 'Nombre')}" />
            <g:sortableColumn property="nombre" title="${message(code: 'tipoCobertura.requiereDescripcion.label', default: 'Requiere Descripción')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoCoberturaInstanceList}" status="i" var="tipoCoberturaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${tipoCoberturaInstance.id}">${fieldValue(bean: tipoCoberturaInstance, field: "codigo")}</g:link></td>
						<td>${fieldValue(bean: tipoCoberturaInstance, field: "nombre")}</td>
            <td><span style='text-align: center;${tipoCoberturaInstance.requiereDescripcion?"color:red;font-weight:bolder;":"color:silver;"}'> ${tipoCoberturaInstance.requiereDescripcion?"SI":"NO"}</span></td>
					</tr>
				</g:each>
				</tbody>
			</table>
      <if test="${tipoCoberturaInstanceCount>24}">
        <div class="pagination">
          <g:paginate total="${tipoCoberturaInstanceCount ?: 0}" />
        </div>
      </if>
		</div>

	</body>
</html>
