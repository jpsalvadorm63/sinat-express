<%@ page import="sinat.express.FichaCampo" %>

<div class="bloqueHead" style="padding-left: 40px;">

  <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'codigoCatastral', 'error')} ">
    <div class="label">Código Catastral</div>
    <g:textField name="codigoCatastral" maxlength="16" value="${fichaCampoInstance?.codigoCatastral}"/>
  </div>
  <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'grupo', 'error')} ">
    <div class="label">Grupo</div>
    <g:textField name="grupo" maxlength="64" value="${fichaCampoInstance?.grupo}"/>
  </div>
  <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'fecha', 'error')} required">
    <div class="label">Fecha<span class="required-indicator">*</span></div>
    <g:datePicker name="fecha" precision="day" value="${fichaCampoInstance?.fecha}"/>
  </div>
  <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'numeroFicha', 'error')} ">
    <div class="label"># Ficha</div>
    <g:textField name="numeroFicha" maxlength="64" value="${fichaCampoInstance?.numeroFicha}"/>
  </div>
  <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'numeroPunto', 'error')} ">
    <div class="label"># Punto</div>
    <g:textField name="numeroPunto" maxlength="64" value="${fichaCampoInstance?.numeroPunto}"/>
  </div>

</div>

<div class="bloque">

  <div class="title">INFORMACIÓN GENERAL</div>

  <div class="bloque2">
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'zonaHomogenea', 'error')} ">
      <div class="label">Zona Homogenea</div>
      <g:textField name="zonaHomogenea" maxlength="64" value="${fichaCampoInstance?.zonaHomogenea}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'sector', 'error')} ">
      <div class="label">Sector o Caserío</div>
      <g:textField name="sector" maxlength="64" value="${fichaCampoInstance?.sector}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'provincvia', 'error')} required">
      <div class="label">Provincia</div>
      <g:select style="width:160px;"
                id="provincia"
                name="provincia.id"
                from="${sinat.express.DPALP.provincias()}"
                optionKey="id"
                required=""
                value="${fichaCampoInstance?.provincia?.id}"
                class="many-to-one"
                onchange="${remoteFunction(controller:'fichaCampo', action:'cantones',params:'\'id=\'+escape(this.value)',onSuccess:'ajaxCantones(data);' )}" />
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'canton', 'error')} required">
      <div class="label">Cantón</div>
        <div id="ajaxCantones">
          <g:select style='width:160px;'
                    id='canton'
                    name='canton.id'
                    from='${sinat.express.DPALP.cantones(fichaCampoInstance?.provincia)}'
                    optionKey='id'
                    required=''
                    value='${fichaCampoInstance?.canton?.id}'
                    class='many-to-one'/>
        </div>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'parroquia', 'error')} required">
      <div class="label">Parroquia</div>
        <div id="ajaxParroquias">
          <g:select style="width:160px;"
                  id="parroquia"
                  name="parroquia.id"
                  from="${sinat.express.DPALP.parroquias(fichaCampoInstance?.canton)}"
                  optionKey="id"
                  required=""
                  value="${fichaCampoInstance?.parroquia?.id}"
                  class="many-to-one"/>
        </div>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'coordenadaX', 'error')} ">
      <div class="label">Coordenada UTM X</div>
      <g:textField name="coordenadaX" maxlength="16" value="${fichaCampoInstance?.coordenadaX}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'coordenadaY', 'error')} ">
      <div class="label">Coordenada UTM Y</div>
      <g:textField name="coordenadaY" maxlength="16" value="${fichaCampoInstance?.coordenadaY}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'altitud', 'error')} required">
      <div class="label">Altitud</div>
      <g:textField name="altitud" value="${fieldValue(bean: fichaCampoInstance, field: 'altitud')}" required=""/>
    </div>
  </div>

  <div class="bloque2">
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombrePropietario', 'error')} ">
      <div class="label">Nombre Propietario</div>
      <g:textField name="nombrePropietario" maxlength="64" value="${fichaCampoInstance?.nombrePropietario}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombreArrendatario', 'error')} ">
      <div class="label">Nombre Arrendatario</div>
      <g:textField name="nombreArrendatario" maxlength="64" value="${fichaCampoInstance?.nombreArrendatario}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'nombreAdministrador', 'error')} ">
      <div class="label">Nombre Administrador</div>
      <g:textField name="nombreAdministrador" maxlength="64" value="${fichaCampoInstance?.nombreAdministrador}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'encuestado', 'error')} ">
      <div class="label">Encuestado</div>
      <g:textField name="encuestado" maxlength="64" value="${fichaCampoInstance?.encuestado}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'superficieTotal', 'error')} required">
      <div class="label">Superficie Total</div>
      <g:field name="superficieTotal" value="${fieldValue(bean: fichaCampoInstance, field: 'superficieTotal')}" required=""/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'construccion', 'error')} ">
      <div class="label">Construcción</div>
      <g:select name="construccion" from="${fichaCampoInstance.constraints.construccion.inList}" value="${fichaCampoInstance?.construccion}" valueMessagePrefix="fichaCampo.construccion" noSelection="['': '']"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'legalizacion', 'error')} ">
      <div class="label">Legalizacion</div>
      <g:select name="legalizacion" from="${fichaCampoInstance.constraints.legalizacion.inList}" value="${fichaCampoInstance?.legalizacion}" valueMessagePrefix="fichaCampo.legalizacion" noSelection="['': '']"/>
    </div>
  </div>

  <div class="bloque2">
    hola3
  </div>

</div>
