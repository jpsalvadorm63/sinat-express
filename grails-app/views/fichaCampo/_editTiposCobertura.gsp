<g:if test="${coberturas0.size() == 0}">
  <g:select disabled="${showing}"
            style="width:160px;"
            id="tipoCobertura"
            name="tipoCobertura.id"
            from="${coberturas0}"
            optionKey="id"
            required=""
            value="${coberturaInstance?.tipoCobertura?.id}"
            class="many-to-one"
            noSelection="['':'']"
            onchange="changeTipoCobertura(this.value,${coberturaInstance?.id});"/>
</g:if>
<g:if test="${coberturas0.size() > 0}">
  <g:select disabled="${showing}"
            style="width:160px;"
            id="tipoCobertura"
            name="tipoCobertura.id"
            from="${coberturas0}"
            optionKey="id"
            required=""
            value="${coberturaInstance?.tipoCobertura?.id}"
            noSelection="['':'']"
            onchange="changeTipoCobertura(this.value,${coberturaInstance?.id});"/>
</g:if>
