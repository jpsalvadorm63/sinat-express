<div class="bloque" id="caracteristicasPredio">

  <div class="title" style="border-top: 1px solid black;">II. CARACTERÍSTICAS DEL PREDIO</div>

  <div class="bloque2" style="width:25%;margin:16px 4px 16px 16px;">
    <div class="title">a. Servicios básicos</div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'sbAguaPotable', 'error')} ">
      <div class='label' style="width:85%;">Agua de consumo por red pública</div>
      <g:checkBox disabled="${showing}" name="sbAguaPotable" value="${fichaCampoInstance?.sbAguaPotable}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'sbEnergiaElectrica', 'error')} ">
      <div class='label' style="width:85%;">Energia eléctrica por red pública</div>
      <g:checkBox disabled="${showing}" name="sbEnergiaElectrica" value="${fichaCampoInstance?.sbEnergiaElectrica}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'sbAlcantarillado', 'error')} ">
      <div class='label' style="width:85%;">Alcantarillado público</div>
      <g:checkBox disabled="${showing}" name="sbAlcantarillado" value="${fichaCampoInstance?.sbAlcantarillado}"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'sbOtro', 'error')} ">
      <div class="label" style="width:85%;">Comunicaciones</div>
      <g:checkBox disabled="${showing}" name="sbComunicaciones" value="${fichaCampoInstance?.sbComunicaciones}"/>
    </div>
  </div>

  <div class="bloque2" style="width:23%;margin:16px 4px 16px 4px;">
    <div class="title">b. Accesibilidad</div>
    <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
      <g:textField disabled="${showing}" name="accesibilidad" maxlength="64" value="${fichaCampoInstance?.accesibilidad?.nombre}" style="height:14px; width:160px;margin: 16px;"/>
    </g:if>
    <g:if test="${actionName?.toUpperCase() != 'SHOWING'}">
      <g:select disabled="${showing}" style="margin:16px;width:160px;"
                id="accesibilidad"
                name="accesibilidad.id"
                from="${sinat.express.Accesibilidad?.list()}"
                optionKey="id"
                required=""
                value="${fichaCampoInstance?.accesibilidad?.id}"
                class="many-to-one"
                noSelection="['':'']"/>
    </g:if>
  </div>

  <div class="bloque2" style="width:24%;margin:16px 4px 16px 4px;">
    <div class="title">c. Cultivos del Sector</div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'cultivosSector1', 'error')} ">
      <g:textField disabled="${showing}" name="cultivosSector1" maxlength="32" value="${fichaCampoInstance?.cultivosSector1}" style="height:14px; width:85%;margin-left: 8px;"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'cultivosSector2', 'error')} ">
      <g:textField disabled="${showing}" name="cultivosSector2" maxlength="32" value="${fichaCampoInstance?.cultivosSector2}" style="height:14px; width:85%;margin-left: 8px"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'cultivosSector3', 'error')} ">
      <g:textField disabled="${showing}" name="cultivosSector3" maxlength="32" value="${fichaCampoInstance?.cultivosSector3}" style="height:14px; width:85%;margin-left: 8px"/>
    </div>
    <div class="fieldcontain ${hasErrors(bean: fichaCampoInstance, field: 'cultivosSector4', 'error')} ">
      <g:textField disabled="${showing}" name="cultivosSector4" maxlength="32" value="${fichaCampoInstance?.cultivosSector4}" style="height:14px; width:85%;margin-left: 8px"/>
    </div>
  </div>

  <g:if test="${actionName?.toUpperCase() == 'SHOWING'}">
    <div class="options" style="top:3px;">
      <g:submitToRemote url="[action: 'editCaractreriasticasPredio',id:fichaCampoInstance.id]" update="caracteristicasPredio" value="Editar"/>
    </div>
  </g:if>

  <g:if test="${actionName?.toUpperCase() == 'EDITING'}">
    <div class="options" style="top:3px;">
    <g:submitToRemote url="[action: 'updateCaractreriasticasPredio',id:fichaCampoInstance.id]" update="caracteristicasPredio" value="aceptar"/>
    <g:submitToRemote url="[action: 'cancelCaractreriasticasPredio',id:fichaCampoInstance.id]" update="caracteristicasPredio" value="cancelar"/>
    </div>
  </g:if>

</div>
