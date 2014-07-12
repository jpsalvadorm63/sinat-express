<table>
<thead>
<tr>
  <th>Código</th>
  <th>Nombre</th>
</tr>
</thead>
<tbody>
<g:each in="${DPAInstanceList}" status="i" var="DPAInstance">
  <tr class="${((i % 2) == 0 ? 'even' : 'odd')}">
    <td>${fieldValue(bean: DPAInstance, field: "codigo")}</td>
    <td>${fieldValue(bean: DPAInstance, field: "nombre")}</td>
  </tr>
</g:each>
</tbody>
</table>
