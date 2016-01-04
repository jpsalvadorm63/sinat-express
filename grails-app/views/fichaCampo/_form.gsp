<%@ page import="sinat.express.FichaCampo" %>

<div class="bloque" id="informacionGeneral">

    <div class="title" style="position: relative;">
        I. INFORMACIÓN GENERAL
        <span id="extra" style="position: absolute; top:4px; left:16px;color: white;">*</span>
    </div>

    <div class="bloque2" style="width:23%;margin:16px 8px 16px 16px;background-color:lightsteelblue;">

        <div class="title">Ficha de Investigación</div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'numeroFicha', 'error')}"
             style="margin-top:4px;">
            <div class="label" style="width:100%;">
                Código de Ficha<span class="required-indicator">*</span>
            </div>
            <g:textField disabled="${showing}"
                         name="numeroFicha"
                         maxlength="64"
                         value="${fichaCampoInstance?.numeroFicha}"
                         style="margin-left:8px;"
                         required="" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'responsable', 'error')}"
             style="margin-top:4px;">
            <div class="label" style="width:100%;">Responsable</div>
            <g:textField disabled="${showing}"
                         name="responsable"
                         maxlength="64"
                         value="${fichaCampoInstance?.responsable}"
                         style="margin-left:8px;width:170px;" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'fecha', 'error')} required"
             style="margin-top:4px;">
            <div class="label" style="clear:both;">
                Fecha<span class="required-indicator">*</span>
            </div>
            <div style="clear:both;padding: 0 0 0 8px;" >
                <g:datePicker disabled="${showing}"
                              name="fecha"
                              precision="day"
                              value="${fichaCampoInstance?.fecha}"
                              required=""/>
            </div>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'confiabilidad', 'error')} required"
             style="margin-top:4px;">
             <div class="label" style="width:100%;">Confiabilidad</div>
             <g:select disabled="${showing}"
                       name="confiabilidad"
                       style="width:80px;margin-left:8px;"
                       from="${fichaCampoInstance.constraints.confiabilidad.inList}"
                       value="${fieldValue(bean: fichaCampoInstance, field: 'confiabilidad')}"
                       valueMessagePrefix="fichaCampo.legalizacion"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'statusLevantamiento', 'error')} required"
             style="margin-top:4px;">
            <div class="label" style="width:100%;">Estatus en levantamiento</div>
            <g:if test="${actionName=='SHOWING'}">
                <g:textField disabled="${showing}"
                             name="statusLevantamiento"
                             maxlength="64"
                             value="${fichaCampoInstance?.statusLevantamiento}"
                             style="margin-left:8px;width:170px;" />
            </g:if>
            <g:else>
                <g:select name="statusLevantamiento"
                          style="width:160px;margin-left:8px;"
                          from="${fichaCampoInstance.constraints.statusLevantamiento.inList}"
                          value="${fieldValue(bean: fichaCampoInstance, field: 'statusLevantamiento')}" />
            </g:else>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'statusControl', 'error')}"
             style="margin-top:4px;">
             <div class="label" style="width:100%;">Control en Unidad Ejecutora</div>
             <g:textField disabled="true"
                          name="statusControl"
                          maxlength="64"
                          value="${fichaCampoInstance?.statusControl}"
                          style="margin-left:8px;width:170px;" />
        </div>
    </div>

    <div class="bloque2" style="width:33%;margin:16px 8px 16px 8px;">

        <div class="title">a. Ubicación Geográfica</div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'coordenadaX', 'error')} ">
            <div class="label">Coordenada UTM X</div>
            <g:field type="number" step="any" disabled="${showing}"
                     name="coordenadaX" maxlength="6"
                     value="${fichaCampoInstance?.coordenadaX}"
                     onkeypress="return isIntegerKey(event)"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'coordenadaY', 'error')} ">
            <div class="label">Coordenada UTM Y</div>
            <g:textField disabled="${showing}"
                         name="coordenadaY"
                         maxlength="9"
                         value="${fichaCampoInstance?.coordenadaY}"
                         onkeypress="return isIntegerKey(event)" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'altitud', 'error')} required">
            <div class="label">Altitud</div>
            <g:field type="number"
                     disabled="${showing}"
                     name="altitud"
                     maxlength="4"
                     min="0"
                     max="6800"
                     value="${fichaCampoInstance?.altitud?.toInteger()}"
                     onkeypress="return isJustNumberKey(event)" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'zonaHomogenea', 'error')} required">
            <div class="label">
                Zona Homogenea<span class="required-indicator">*</span>
            </div>
            <g:textField disabled="${showing}"
                         name="zonaHomogenea"
                         maxlength="64"
                         value="${fichaCampoInstance?.zonaHomogenea}"
                         required=""/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'sector', 'error')} ">
            <div class="label">Sector o Caserío</div>
            <g:textField disabled="${showing}"
                         name="sector"
                         maxlength="64"
                         value="${fichaCampoInstance?.sector}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'provincia', 'error')} required">
            <div class="label">Provincia</div>
            <g:select disabled="${showing}"
                      style="width:160px;"
                      id="provincia"
                      name="provincia.id"
                      from="${externos.DPALP.provincias()}"
                      optionKey="id"
                      optionValue="nombreYCodigo"
                      required=""
                      value="${fichaCampoInstance?.provincia?.id}"
                      class="many-to-one"
                      onchange="${remoteFunction(controller:'fichaCampo', action:'cantones',params:'\'id=\'+escape(this.value)',onSuccess:'ajaxCantones(data);' )}" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'canton', 'error')} required">
            <div class="label">Cantón</div>
            <div id="ajaxCantones">
                <g:select disabled="${showing}"
                          style='width:160px;'
                          id='canton'
                          name='canton.id'
                          from='${externos.DPALP.cantones(fichaCampoInstance?.provincia)}'
                          optionKey='id'
                          optionValue="nombreYCodigo"
                          required=''
                          value='${fichaCampoInstance?.canton?.id}'
                          class='many-to-one'/>
            </div>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'parroquia', 'error')} required">
            <div class="label">Parroquia</div>
            <div id="ajaxParroquias">
                <g:select disabled="${showing}"
                          style="width:160px;"
                          id="parroquia"
                          name="parroquia.id"
                          from="${externos.DPALP.parroquias(fichaCampoInstance?.canton)}"
                          optionKey="id"
                          optionValue="nombreYCodigo"
                          required=''
                          value="${fichaCampoInstance?.parroquia?.id}"
                          class="many-to-one"/>
            </div>
        </div>
    </div>

    <div class="bloque2" style="width:33%;margin:16px 8px 16px 8px;">

        <div class="title">b. Identificación del predio</div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombrePropietario', 'error')} required">
            <div class="label">
                Nombre Propietario<span class="required-indicator">*</span>
            </div>
            <g:textField disabled="${showing}"
                         name="nombrePropietario"
                         maxlength="64"
                         value="${fichaCampoInstance?.nombrePropietario}"
                         required="" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombreArrendatario', 'error')} required">
            <div class="label">Nombre Arrendatario</div>
            <g:textField disabled="${showing}"
                         name="nombreArrendatario"
                         maxlength="64"
                         value="${fichaCampoInstance?.nombreArrendatario}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombreAdministrador', 'error')} ">
            <div class="label">Nombre Administrador</div>
            <g:textField disabled="${showing}"
                         name="nombreAdministrador"
                         maxlength="64"
                         value="${fichaCampoInstance?.nombreAdministrador}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'encuestado', 'error')} required">
            <div class="label">Encuestado<span class="required-indicator">*</span></div>
            <g:textField disabled="${showing}"
                         name="encuestado"
                         maxlength="64"
                         value="${fichaCampoInstance?.encuestado}"
                         required="" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'superficieTotal', 'error')} required">
            <div class="label">Superficie Total (ha)<span class="required-indicator">*</span></div>
            <g:field type="floatNumber"
                     disabled="${showing}"
                     name="superficieTotal"
                     maxlength="7"
                     value="${fieldValue(bean: fichaCampoInstance, field: 'superficieTotal')}"
                     onkeypress="return isNumberKey(event)"
                     required=""/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'codigoCatastral', 'error')} required">
            <div class="label">Código Catastral<span class="required-indicator">*</span></div>
            <g:textField disabled="${showing}"
                         name="codigoCatastral"
                         maxlength="16"
                         value="${fichaCampoInstance?.codigoCatastral}"
                         required="" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'construccion', 'error')} required">
            <div class="label">
                Construcción<span class="required-indicator">*</span>
            </div>
            <g:select disabled="${showing}"
                      name="construccion"
                      style="width:160px;"
                      from="${fichaCampoInstance.constraints.construccion.inList}"
                      value="${fichaCampoInstance?.construccion}"
                      valueMessagePrefix="fichaCampo.construccion"
                      noSelection="['':'']"
                      required=""/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'legalizacion', 'error')} required">
                <div class="label">Legalizacion<span class="required-indicator">*</span></div>
                <g:select disabled="${showing}"
                          name="legalizacion"
                          style="width:160px;"
                          from="${fichaCampoInstance.constraints.legalizacion.inList}"
                          value="${fichaCampoInstance?.legalizacion}"
                          valueMessagePrefix="fichaCampo.legalizacion"
                          noSelection="['':'']"
                          required=""/>
        </div>

    </div>

    <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
        <div class="options">
            <g:if test="${sinat.express.KV.isLocUE()}">
                <g:submitToRemote update="informacionGeneral"
                                  url="[action: 'aprobarFic', id:fichaCampoInstance.id]"
                                  value="aprobar" />
                <g:submitToRemote update="informacionGeneral"
                                  url="[action: 'rechazarFic', id:fichaCampoInstance.id]"
                                  value="rechazar" />
            </g:if>
            <!--
            <g:submitButton name="json" value="json" />
            -->
            <g:submitToRemote url="[action: 'editInformacionGeneral', id:fichaCampoInstance.id]"
                              update="informacionGeneral"
                              value="Editar" />
        </div>
    </g:if>

    <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
        <div class="options">
            <g:submitToRemote url="[action: 'updateInformacionGeneral', id:fichaCampoInstance.id]"
                              update="informacionGeneral"
                              value="aceptar"/>
            <g:submitToRemote url="[action: 'cancelInformacionGeneral', id:fichaCampoInstance.id]"
                              update="informacionGeneral"
                              value="cancelar"/>
        </div>
    </g:if>

</div>

<script>
<!--
$("span#extra").text("${(fichaCampoInstance?.origen == null)?'*':fichaCampoInstance?.origen}");

document.getElementById("json").onclick = function () {
    location.href = "/${g.meta(name:'app.name')}/fichaCampo/showFicAsJson/${fichaCampoInstance.id}";
};
-->
</script>
