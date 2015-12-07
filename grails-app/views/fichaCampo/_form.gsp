<%@ page import="sinat.express.FichaCampo" %>

<div class="bloque" id="informacionGeneral">

    <div class="title">I. INFORMACIÓN GENERAL</div>

    <div class="bloque2" style="width:23%;margin:16px 8px 16px 16px;background-color:lightsteelblue;">
        <div class="title">Ficha de Investigación</div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'numeroFicha', 'error')}" style="margin-top:4px;">
            <div class="label" style="width:100%;">Código de Ficha</div>
            <g:textField disabled="disabled" name="numeroFicha" maxlength="64"
                         value="${fichaCampoInstance?.numeroFicha}" style="margin-left:8px;" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'responsable', 'error')}" style="margin-top:4px;">
            <div class="label" style="width:100%;">Responsable</div>
            <g:textField disabled="${showing}" name="responsable" maxlength="64"
                         value="${fichaCampoInstance?.responsable}" style="margin-left:8px;width:170px;" />
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'fecha', 'error')} required" style="margin-top:4px;">
            <div class="label" style="width:100%;">Fecha<span class="required-indicator">*</span></div>
            <div style="margin-left:8px;" >
                <g:datePicker disabled="${showing}" name="fecha" precision="day" value="${fichaCampoInstance?.fecha}"/>
            </div>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'confiabilidad', 'error')} required"
             style="margin-top:4px;">
            <div class="label" style="width:100%;">Confiabilidad</div>
            <g:select disabled="${showing}" name="confiabilidad" style="width:80px;margin-left:8px;"
                      from="${fichaCampoInstance.constraints.confiabilidad.inList}"
                      value="${fieldValue(bean: fichaCampoInstance, field: 'confiabilidad')}"
                      valueMessagePrefix="fichaCampo.legalizacion"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'statusLevantamiento', 'error')} required"
             style="margin-top:4px;">
            <div class="label" style="width:100%;">Estatus en levantamiento ${actionName}</div>
            <g:if test="${actionName=='SHOWING'}">
                <g:textField disabled="${showing}"
                             name="statusLevantamiento"
                             maxlength="64"
                             value="${fichaCampoInstance?.statusLevantamiento}"
                             style="margin-left:8px;width:170px;" />
            </g:if>
            <g:else>
                <g:select name="statusLevantamiento" style="width:160px;margin-left:8px;"
                          from="${fichaCampoInstance.constraints.statusLevantamiento.inList}"
                          value="${fieldValue(bean: fichaCampoInstance, field: 'statusLevantamiento')}"
                />
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
      <g:textField disabled="${showing}" name="coordenadaX" maxlength="6" value="${fichaCampoInstance?.coordenadaX}" onkeypress="return isIntegerKey(event)"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'coordenadaY', 'error')} ">
      <div class="label">Coordenada UTM Y</div>
      <g:textField disabled="${showing}" name="coordenadaY" maxlength="9" value="${fichaCampoInstance?.coordenadaY}" onkeypress="return isIntegerKey(event)"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'altitud', 'error')} required">
      <div class="label">Altitud</div>
      <g:textField disabled="${showing}" name="altitud" maxlength="4" value="${fieldValue(bean: fichaCampoInstance, field: 'altitud')}" onkeypress="return isNumberKey(event)"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'zonaHomogenea', 'error')} ">
      <div class="label">Zona Homogenea</div>
      <g:textField disabled="${showing}" name="zonaHomogenea" maxlength="64" value="${fichaCampoInstance?.zonaHomogenea}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'sector', 'error')} ">
      <div class="label">Sector o Caserío</div>
      <g:textField disabled="${showing}" name="sector" maxlength="64" value="${fichaCampoInstance?.sector}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'provincia', 'error')} required">
      <div class="label">Provincia</div>
      <g:select disabled="${showing}" style="width:160px;"
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
        <g:select disabled="${showing}" style='width:160px;'
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
        <g:select disabled="${showing}" style="width:160px;"
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
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombrePropietario', 'error')}">
      <div class="label">Nombre Propietario</div>
      <g:textField disabled="${showing}" name="nombrePropietario" maxlength="64" value="${fichaCampoInstance?.nombrePropietario}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombreArrendatario', 'error')} required">
      <div class="label">Nombre Arrendatario</div>
      <g:textField disabled="${showing}" name="nombreArrendatario" maxlength="64" value="${fichaCampoInstance?.nombreArrendatario}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombreAdministrador', 'error')} ">
      <div class="label">Nombre Administrador</div>
      <g:textField disabled="${showing}" name="nombreAdministrador" maxlength="64" value="${fichaCampoInstance?.nombreAdministrador}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'encuestado', 'error')} required">
      <div class="label">Encuestado</div>
      <g:textField disabled="${showing}" name="encuestado" maxlength="64" value="${fichaCampoInstance?.encuestado}" required="" />
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'superficieTotal', 'error')}">
      <div class="label">Superficie Total (ha)</div>
      <g:field disabled="${showing}" name="superficieTotal" maxlength="7" value="${fieldValue(bean: fichaCampoInstance, field: 'superficieTotal')}" onkeypress="return isNumberKey(event)"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'codigoCatastral', 'error')} required">
      <div class="label">Código Catastral</div>
      <g:textField disabled="${showing}" name="codigoCatastral" maxlength="16" value="${fichaCampoInstance?.codigoCatastral}" />
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'construccion', 'error')} ">
      <div class="label">Construcción</div>
      <g:select disabled="${showing}"
                name="construccion"
                style="width:160px;"
                from="${fichaCampoInstance.constraints.construccion.inList}"
                value="${fichaCampoInstance?.construccion}"
                valueMessagePrefix="fichaCampo.construccion"
                noSelection="['':'']"
                required=''/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'legalizacion', 'error')} ">
      <div class="label">Legalizacion</div>
      <g:select disabled="${showing}"
                name="legalizacion"
                style="width:160px;"
                from="${fichaCampoInstance.constraints.legalizacion.inList}"
                value="${fichaCampoInstance?.legalizacion}"
                valueMessagePrefix="fichaCampo.legalizacion"
                noSelection="['':'']"
                required=''/>
    </div>
  </div>

  <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
    <div class="options">
      <g:submitToRemote url="[action: 'editInformacionGeneral',id:fichaCampoInstance.id]" update="informacionGeneral" value="Editar"/>
    </div>
  </g:if>

  <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
    <div class="options">
      <g:submitToRemote url="[action: 'updateInformacionGeneral',id:fichaCampoInstance.id]" update="informacionGeneral" value="aceptar"/>
      <g:submitToRemote url="[action: 'cancelInformacionGeneral',id:fichaCampoInstance.id]" update="informacionGeneral" value="cancelar"/>
    </div>
  </g:if>

</div>
