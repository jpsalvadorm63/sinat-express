    <div class="bloque2" style="width:25%;margin:0 4px 2px 16px;border: 0;">
        <div class="bloque2" style="width:auto;margin:0 4px 2px 4px;border:0;">
            <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'superficie', 'error')} " style="clear:both;">
                <div class="label" style="width: 70px;">Superficie (m2)</div>
                <g:field type="floatNumber"
                         name="superficie"
                         disabled="${showing}"
                         style="width:110px;"
                         value="${fieldValue(bean: habitacionalInstance, field: 'superficie')}"
                         onkeypress="return isNumberKey(event)" />
            </div>
        <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'legalizacion', 'error')}" style="clear:both;">
            <div class="label" style="width: 70px;">Legalización</div>
            <g:select name="legalizacion"
                      disabled="${showing}"
                      from="${habitacionalInstance?.constraints.legalizacion.inList}"
                      value="${habitacionalInstance?.legalizacion}"
                      valueMessagePrefix="habitacional.legalizacion"
                      noSelection="['': '']"/>
            </div>
        </div>
    </div>

<div class="bloque2" style="width:25%;margin:0 4px 2px 16px;border: 0;">
  <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'sbAguaPotable', 'error')} ">
    <div class="label" style="width:75%">Agua de consumo por red pública</div>
    <g:checkBox name="sbAguaPotable" disabled="${showing}" value="${habitacionalInstance?.sbAguaPotable}" />
  </div>
  <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'sbEnergiaElectrica', 'error')} ">
    <div class="label" style="width:75%">Energía eléctrica por red pública</div>
    <g:checkBox name="sbEnergiaElectrica" disabled="${showing}" value="${habitacionalInstance?.sbEnergiaElectrica}" />
  </div>
  <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'sbAlcantarillado', 'error')} ">
    <div class="label" style="width:75%">Alcantarillado público</div>
    <g:checkBox name="sbAlcantarillado" disabled="${showing}" value="${habitacionalInstance?.sbAlcantarillado}" />
  </div>
  <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'sbComunicaciones', 'error')} ">
    <div class="label" style="width:75%">Comunicaciones</div>
    <g:checkBox name="sbComunicaciones" disabled="${showing}" value="${habitacionalInstance?.sbComunicaciones}" />
  </div>
</div>

<div class="bloque2" style="margin:0 4px 2px 4px;border:0;">

    <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'venta', 'error')}" style="clear:both;">
        <div class="label" style="width:120px;">Valor Venta (USD/m2)</div>
        <g:field type="floatNumber"
                 name="venta"
                 disabled="${showing}"
                 value="${fieldValue(bean: habitacionalInstance, field: 'venta')}"
                 style="width:110px;float:left;"
                 onkeypress="return isNumberKey(event)"/>
    </div>
        <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'oferta', 'error')} " style="clear:both;">
        <div class="label" style="width:120px;">Valor Oferta (USD/m2)</div>
        <g:field type="floatNumber"
                 name="oferta"
                 disabled="${showing}"
                 value="${fieldValue(bean: habitacionalInstance, field: 'oferta')}"
                 style="width:110px;"
                 onkeypress="return isNumberKey(event)"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: habitacionalInstance, field: 'arriendo', 'error')} " style="clear:both;">
        <div class="label" style="width:120px;">Valor Arriendo (USD/m2)</div>
        <g:field type="floatNumber"
                 name="arriendo"
                 disabled="${showing}"
                 value="${fieldValue(bean: habitacionalInstance, field: 'arriendo')}"
                 style="width:110px;"
                 onkeypress="return isNumberKey(event)"/>
    </div>

</div>
