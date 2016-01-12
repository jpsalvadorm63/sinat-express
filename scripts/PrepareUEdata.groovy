includeTargets << grailsScript("_GrailsInit")

target(prepareUEdata: "Prepara los datos propios de la UE - una sola vez para versiones >= 0.78") {
    sinat.express.KV.setLocUE()
    sinat.express.FichaCampo.list().each {
        if(it.origen == null) {
            it.origen = 'UE'
            it.save(flush:true)
        }
        if(it.statusLevantamiento == null && it.origen == 'UE') {
            it.statusLevantamiento = 'PARA ENTREGA'
            it.save(flush:true)
        }
        if(it.statusControl == null && it.origen == 'UE') {
            it.statusControl = 'ACEPTADA'
            it.save(flush:true)
        }
    }
    0
}

setDefaultTarget(prepareUEdata)
