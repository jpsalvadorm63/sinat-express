<table>
  <thead>
  <tr>
    <th>Código</th>
    <th>Nombre</th>
    <th>Reportes</th>
  </tr>
  </thead>
  <tbody>
  <g:each in="${DPAInstanceList}" status="i" var="DPAInstance">
    <tr class="${((i % 2) == 0 ? 'even' : 'odd')}">
      <td>${fieldValue(bean: DPAInstance, field: "codigo")}</td>
      <td>${fieldValue(bean: DPAInstance, field: "nombre")}</td>
      <td class="link1">
        <g:link controller="DPA" action="informacionGeneralACsv" id="${DPAInstance.id}">Información General</g:link>
        <g:link controller="DPA" action="serviciosBasicosACsv" id="${DPAInstance.id}">Serv_Básicos</g:link>
        <g:link controller="DPA" action="coberturaACsv" id="${DPAInstance.id}">Cobertura</g:link>
        <g:link controller="DPA" action="habitacionalACsv" id="${DPAInstance.id}">Habitacional</g:link>
      </td>
    </tr>
  </g:each>
  </tbody>
</table>