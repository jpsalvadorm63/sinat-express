
<%@ page import="sinat.express.Cobertura" %>
<div class="onecobertura">

  <div style="min-height: 32px;padding: 12px 0 8px 8px;float:left;">
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'tipoUso', 'error')} required">
      <div class="label">Tipo de Uso</div>
        <g:if test="${showing=='true'}">
          <g:field name="nathing" style="width:150px" disabled="${showing}" value="${coberturaInstance?.tipoUso}"/>
        </g:if>
        <g:if test="${showing=='false'}">
          <g:select disabled="${showing}" style="width:160px;"
                    id="tipoUso"
                    name="tipoUso.id"
                    from="${sinat.express.TipoUso.list()}"
                    optionKey="id"
                    required=""
                    value="${coberturaInstance?.tipoUso?.id}"
                    class="many-to-one"
                    noSelection="['':'']"
                    onchange="changeTipoUso(escape(this.value),${coberturaInstance?.id});" />
        </g:if>
    </div>
    <div style="clear:both;height:4px;"></div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'tipoCobertura', 'error')} required">
      <div class="label">Cobertura</div>
      <g:if test="${showing=='true'}">
          <g:field name="nathing" style="width:150px" disabled="${showing}" value="${coberturaInstance?.tipoCobertura}"/>
      </g:if>
      <g:if test="${showing=='false'}">
        <div id="ajaxCoberturas">
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
        </div>
      </g:if>
    </div>
    <div style="clear:both;height:4px;"></div>

    <div id="otros" class="${(deshabilitarOtros == null || deshabilitarOtros==true)?'desabilitado':''}" style="clear:both;">
      <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'otrosCultivo', 'error')} ">
        <div class="label">Otros Cultivos</div>
        <g:textField disabled="${showing}" name="otrosCultivo" maxlength="64" value="${coberturaInstance?.otrosCultivo}" style="width:150px;"/>
      </div>
    </div>

  </div>

  <div style="min-height: 32px;padding: 12px 0 12px 4px;float:left;">
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'superficie', 'error')} ">
      <div class="label">Superficie (ha)</div>
      <g:field disabled="${showing}" name="superficie" value="${fieldValue(bean: coberturaInstance, field: 'superficie')}"  onkeypress="return isNumberKey(event)"/>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'cargaAnimal', 'error')} ">
      <div class="label">Carga animal</div>
      <g:field disabled="${showing}" name="cargaAnimal" type="number" value="${coberturaInstance.cargaAnimal}"/>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'tecnologiaPredominante', 'error')} ">
      <div class="label">Tecnología Predominante</div>
      <g:if test="${showing=='true'}">
        <g:field name="nathing" style="width:120px" disabled="${showing}" value="${coberturaInstance?.tecnologiaPredominante}"/>
      </g:if>
      <g:if test="${showing=='false'}">
        <div id="ajaxTecnologiasPredominantes">
          <g:select disabled="${showing}"
                    style="width:130px;height:23px;"
                    id="tecnologiaPredominante"
                    name="tecnologiaPredominante.id"
                    from="${sinat.express.TipoTecnologiaPredominante.forTipoUso(coberturaInstance?.tipoUso)}"
                    optionKey="id"
                    required=""
                    value="${coberturaInstance?.tecnologiaPredominante?.id}"
                    noSelection="['':'']"
                    class="many-to-one" />
        </div>
      </g:if>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'oferta', 'error')} ">
      <div class="label">Valor Oferta (USD/ha)</div>
      <g:field disabled="${showing}" name="oferta" value="${fieldValue(bean: coberturaInstance, field: 'oferta')}" onkeypress="return isNumberKey(event)"/>
    </div>
    <div style="clear:both;height:4px;"></div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'rotacion', 'error')} ">
      <div class="label">Rotación</div>
      <g:textField disabled="${showing}" name="rotacion" maxlength="128" value="${coberturaInstance?.rotacion}"/>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'rendimiento', 'error')} ">
      <div class="label">Rendimiento</div>
      <g:textField disabled="${showing}" name="rendimiento" maxlength="64" value="${coberturaInstance?.rendimiento}"/>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'sistemaDeRiego', 'error')} ">
      <div class="label">Sistema de riego</div>
      <g:if test="${showing=='true'}">
        <g:field name="nathing" style="width:120px" disabled="${showing}" value="${coberturaInstance?.sistemaDeRiego}"/>
      </g:if>
      <g:if test="${showing=='false'}">
        <g:select disabled="${showing}"
                  name="sistemaDeRiego"
                  from="${coberturaInstance.constraints.sistemaDeRiego.inList}"
                  value="${coberturaInstance?.sistemaDeRiego}" valueMessagePrefix="cobertura.sistemaDeRiego"
                  noSelection="['':'']"/>
      </g:if>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'venta', 'error')} ">
      <div class="label">Valor Venta (USD/ha)</div>
      <g:field disabled="${showing}" name="venta" value="${fieldValue(bean: coberturaInstance, field: 'venta')}" onkeypress="return isNumberKey(event)"/>
    </div>
    <div style="clear:both;height:4px;"></div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'cosechasPorAnio', 'error')} ">
      <div class="label">Cosecha por año</div>
      <g:field disabled="${showing}" name="cosechasPorAnio" type="number" value="${coberturaInstance.cosechasPorAnio}"/>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'precioProducto', 'error')} ">
      <div class="label">Precio producto</div>
      <g:field disabled="${showing}" name="precioProducto" value="${fieldValue(bean: coberturaInstance, field: 'precioProducto')}" onkeypress="return isNumberKey(event)"/>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'mecanizacion', 'error')} ">
      <div class="label">Mecanización</div>
      <g:if test="${showing=='true'}">
        <g:field name="nathing"
                 style="width:120px"
                 disabled="${showing}"
                 value="${coberturaInstance?.mecanizacion}" />
      </g:if>
      <g:if test="${showing=='false'}">
        <g:select disabled="${showing}"
                  name="mecanizacion"
                  from="${coberturaInstance.constraints.mecanizacion.inList}"
                  value="${coberturaInstance?.mecanizacion}"
                  valueMessagePrefix="cobertura.mecanizacion"
                  noSelection="['':'']"/>
      </g:if>
    </div>
    <div class="fieldcontain fieldcontain2 ${hasErrors(bean: coberturaInstance, field: 'arriendo', 'error')} ">
      <div class="label">Valor Arriendo (USD/ha)</div>
      <g:field disabled="${showing}" name="arriendo" value="${fieldValue(bean: coberturaInstance, field: 'arriendo')}" onkeypress="return isNumberKey(event)"/>
    </div>
  </div>

</div>
