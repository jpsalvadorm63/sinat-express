<div id="list-tipoCobertura" class="content scaffold-list" role="main" style="width:100%">
  <h1>${tipoUsoInstance.nombre} - Coberturas</h1>
  <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
  </g:if>
  <table>
    <thead>
    <tr>
      <g:sortableColumn style="width:15%;" property="codigo" title="Código" />
      <g:sortableColumn style="width:45%;" property="nombre" title="${message(code: 'tipoCobertura.nombre.label', default: 'Nombre')}" />
      <g:sortableColumn property="nombre" title="${message(code: 'tipoCobertura.requiereDescripcion.label', default: 'Requiere Descripción')}" />
    </tr>
    </thead>
    <tbody>
    <g:each in="${tipoCoberturaInstanceList}" status="i" var="tipoCoberturaInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td>${fieldValue(bean: tipoCoberturaInstance, field: "codigo")}</td>
        <td>${fieldValue(bean: tipoCoberturaInstance, field: "nombre")}</td>
        <td style='text-align: center;'>
          <span style='${tipoCoberturaInstance.requiereDescripcion?"color:red;font-weight:bolder;":"color:silver;"}'>
            ${tipoCoberturaInstance.requiereDescripcion?"SI":"NO"}
          </span>
        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
  <g:if test="${tipoCoberturaInstanceCount > 16}">
    <div class="pagination">
      <g:paginate total="${tipoCoberturaInstanceCount ?: 0}" />
    </div>
  </g:if>
</div>
