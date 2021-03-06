package sinat.express

import grails.transaction.Transactional
import java.text.MessageFormat

@Transactional
class FicService {

    def test() { "¡ sinat.express.FicService !" }

    def fic2json(String codigoCatastral) {
        def fic = sinat.express.FichaCampo.findByCodigoCatastral(codigoCatastral)
        return fic2json(fic)
        /* ------------------------------------------------ TEST in groovy consoles:
        def fs = ctx.ficService
        def f = fs.fic2json('1002540140179')
        String s = groovy.json.JsonOutput.prettyPrint(f.toString())
        println s
        0
        ------------------------------------------------------------------------- */
    }

    def fic2json(FichaCampo myFic) {
        def builder = new groovy.json.JsonBuilder()
        def cobs = Cobertura.findAllByFichaCampo(myFic)
        def habs = Habitacional.findAllByFichaCampo(myFic)
        builder.fic {
            encabezado {
                codigoCatastral      myFic.codigoCatastral
                responsable          myFic.responsable
                fecha                myFic.fecha.format("'D'yyyyMMdd'H'hhmmss")
                numeroFicha          myFic.numeroFicha
                gad                  myFic.gad
            }
            ubicacionGeografica {
                zonaHomogenea        myFic.zonaHomogenea
                provincia            myFic.provincia?.codigo
                canton               myFic.canton?.codigo
                parroquia            myFic.parroquia?.codigo
                sector               myFic.sector
                coordenadaX          myFic.coordenadaX
                coordenadaY          myFic.coordenadaY
                altitud              myFic.altitud
            }
            identificacionDelPredio {
                nombrePropietario    myFic.nombrePropietario
                nombreArrendatario   myFic.nombreArrendatario
                nombreAdministrador  myFic.nombreAdministrador
                encuestado           myFic.encuestado
                superficieTotal      myFic.superficieTotal
                construccion         myFic.construccion
                legalizacion         myFic.legalizacion
            }
            confiabilidad {
                confiabilidad        myFic.confiabilidad
            }
            serviciosBasicos {
                sbEnergiaElectrica   myFic.sbEnergiaElectrica
                sbAguaPotable        myFic.sbAguaPotable
                sbAlcantarillado     myFic.sbAlcantarillado
                sbComunicaciones     myFic.sbComunicaciones
            }
            accesibilidad {
                accesibilidad        myFic.accesibilidad?.codigo
            }
            cultivosDelSector {
                cultivosSector1      myFic.cultivosSector1
                cultivosSector2      myFic.cultivosSector2
                cultivosSector3      myFic.cultivosSector3
                cultivosSector4      myFic.cultivosSector4
            }
            observaciones {
                observaciones        myFic.observaciones
            }
            camposDeControl {
                minx                 myFic.minx
                miny                 myFic.miny
                maxx                 myFic.maxx
                maxy                 myFic.maxy
                fechaCreacion        myFic.fechaCreacion.format("'D'yyyyMMdd'H'hhmmss")
                fechaActualizacion   myFic.fechaActualizacion.format("'D'yyyyMMdd'H'hhmmss")
                statusLevantamiento  myFic.statusLevantamiento
                statusControl        myFic.statusControl
                origen               myFic.origen
            }
            cobertura (
                cobs.collect { Cobertura cob ->
                    [ tipoUso : TipoUso.get(cob.tipoUso?.id)?.codigo,
                      tipoCobertura : TipoCobertura.get(cob.tipoCobertura?.id)?.codigo,
                      superficie : cob.superficie,
                      rendimiento : cob.rendimiento,
                      cargaAnimal : cob.cargaAnimal,
                      rotacion : cob.rotacion,
                      cosechasPorAnio : cob.cosechasPorAnio,
                      tecnologiaPredominante : TipoTecnologiaPredominante.get(cob.tecnologiaPredominante?.id)?.codigo,
                      sistemaDeRiego : cob.sistemaDeRiego,
                      permanenciaDeRiego : cob.permanenciaDeRiego,
                      mecanizacion : cob.mecanizacion,
                      venta : cob.venta,
                      oferta : cob.oferta,
                      arriendo : cob.arriendo,
                      precioProducto : cob.precioProducto,
                      otrosCultivo : cob.otrosCultivo,
                      fechaCreacion : cob.fechaCreacion.format("'D'yyyyMMdd'H'hhmmss"),
                      fechaActualizacion : cob.fechaActualizacion.format("'D'yyyyMMdd'H'hhmmss") ]
                }
            )
            habitacional (
                habs.collect { Habitacional hab ->
                    [ superficie : hab.superficie,
                      sbEnergiaElectrica : hab.sbEnergiaElectrica,
                      sbAguaPotable : hab.sbAguaPotable,
                      sbAlcantarillado : hab.sbAlcantarillado,
                      sbComunicaciones : hab.sbComunicaciones,
                      legalizacion : hab.legalizacion,
                      venta : hab.venta,
                      oferta : hab.oferta,
                      arriendo : hab.arriendo,
                      fechaCreacion : hab.fechaCreacion.format("'D'yyyyMMdd'H'hhmmss"),
                      fechaActualizacion : hab.fechaActualizacion.format("'D'yyyyMMdd'H'hhmmss")
                    ]
                }
            )
        }

        return builder

        /* ------------------------------------------------ TEST in groovy consoles:
        // eduardo batson: 0992562241 / trabajo (02)2394900 048
        def fs = ctx.ficService
        def fic = sinat.express.FichaCampo.findByCodigoCatastral('0106500480267')
        def coberturas = sinat.express.Cobertura.findAllByFichaCampo(fic)
        def f = fs.fic2json(fic)
        println f.getClass()
        String s = groovy.json.JsonOutput.prettyPrint(f.toString())
        //String s = f.toString()
        println s
        def jsonReader = new groovy.json.JsonSlurper()
        def f2 = jsonReader.parseText(s)
        println f2.fic.ubicacionGeografica.coordenadaY.getClass()
        0
        ------------------------------------------------------------------------- */

    }

    def json2fic(String s) {
        def jsonReader = new groovy.json.JsonSlurper()
        def fj = jsonReader.parseText(s)

        FichaCampo fc = new FichaCampo()
        fc.codigoCatastral = fj.fic.encabezado.codigoCatastral
        fc.responsable = fj.fic.encabezado.responsable
        fc.fecha       = string2date(fj.fic.encabezado.fecha).toTimestamp()
        fc.numeroFicha = fj.fic.encabezado.numeroFicha
        fc.gad         = fj.fic.encabezado.gad

        fc.zonaHomogenea = fj.fic.ubicacionGeografica.zonaHomogenea
        fc.provincia     = externos.DPALP.findByCodigo(fj.fic.ubicacionGeografica.provincia)
        fc.canton        = externos.DPALP.findByCodigo(fj.fic.ubicacionGeografica.canton)
        fc.parroquia     = externos.DPALP.findByCodigo(fj.fic.ubicacionGeografica.parroquia)
        fc.sector        = fj.fic.ubicacionGeografica.sector
        fc.coordenadaX   = fj.fic.ubicacionGeografica.coordenadaX
        fc.coordenadaY   = fj.fic.ubicacionGeografica.coordenadaY
        fc.altitud       = fj.fic.ubicacionGeografica.altitud?.toFloat()

        fc.nombrePropietario   = fj.fic.identificacionDelPredio.nombrePropietario
        fc.nombreArrendatario  = fj.fic.identificacionDelPredio.nombreArrendatario
        fc.nombreAdministrador = fj.fic.identificacionDelPredio.nombreAdministrador
        fc.encuestado          = fj.fic.identificacionDelPredio.encuestado
        fc.superficieTotal     = fj.fic.identificacionDelPredio.superficieTotal
        fc.construccion        = fj.fic.identificacionDelPredio.construccion
        fc.legalizacion        = fj.fic.identificacionDelPredio.legalizacion

        fc.confiabilidad = fj.fic.confiabilidad.confiabilidad

        fc.sbEnergiaElectrica = fj.fic.serviciosBasicos.sbEnergiaElectrica
        fc.sbAguaPotable      = fj.fic.serviciosBasicos.sbAguaPotable
        fc.sbAlcantarillado   = fj.fic.serviciosBasicos.sbAlcantarillado
        fc.sbComunicaciones   = fj.fic.serviciosBasicos.sbComunicaciones

        fc.accesibilidad = Accesibilidad.findByCodigo(fj.fic.accesibilidad.accesibilidad)

        fc.cultivosSector1 = fj.fic.cultivosDelSector.cultivosSector1
        fc.cultivosSector2 = fj.fic.cultivosDelSector.cultivosSector2
        fc.cultivosSector3 = fj.fic.cultivosDelSector.cultivosSector3
        fc.cultivosSector4 = fj.fic.cultivosDelSector.cultivosSector4

        fc.minx                = fj.fic.camposDeControl.minx
        fc.miny                = fj.fic.camposDeControl.miny
        fc.maxx                = fj.fic.camposDeControl.maxx
        fc.maxy                = fj.fic.camposDeControl.maxy
        fc.fechaCreacion       = string2dateTime(fj.fic.camposDeControl.fechaCreacion)?.toTimestamp()
        fc.fechaActualizacion  = string2dateTime(fj.fic.camposDeControl.fechaActualizacion)?.toTimestamp()
        fc.statusLevantamiento = fj.fic.camposDeControl.statusLevantamiento
        fc.statusControl       = fj.fic.camposDeControl.statusControl
        fc.origen              = fj.fic.camposDeControl.origen

        fc.observaciones = fj.fic.observaciones.observaciones

        return fc
    }

    def json2coberturas(String s) {
        def jsonReader = new groovy.json.JsonSlurper()
        def fj = jsonReader.parseText(s)
        def coberturas = []

        fj.fic.cobertura.each { cob ->
            def myCob = new Cobertura()
            myCob.tipoUso = TipoUso.findByCodigo(cob.tipoUso)
            myCob.tipoCobertura = TipoCobertura.findByCodigo(cob.tipoCobertura)
            myCob.superficie = cob.superficie
            myCob.rendimiento = cob.rendimiento
            myCob.cargaAnimal = cob.cargaAnimal
            myCob.rotacion = cob.rotacion
            myCob.cosechasPorAnio = cob.cosechasPorAnio
            myCob.tecnologiaPredominante = TipoTecnologiaPredominante.findByCodigo(cob.tecnologiaPredominante)
            myCob.sistemaDeRiego = cob.sistemaDeRiego
            myCob.permanenciaDeRiego = cob.permanenciaDeRiego
            myCob.mecanizacion = cob.mecanizacion
            myCob.venta = cob.venta
            myCob.oferta = cob.oferta
            myCob.arriendo = cob.arriendo
            myCob.precioProducto = cob.precioProducto
            myCob.otrosCultivo = cob.otrosCultivo
            myCob.fechaCreacion = string2dateTime(cob.fechaCreacion)
            myCob.fechaActualizacion = string2dateTime(cob.fechaActualizacion)

            coberturas.add(myCob)
        }

        return coberturas
    }

    def json2habitacional(String s) {
        def jsonReader = new groovy.json.JsonSlurper()
        def fj = jsonReader.parseText(s)
        def habitacionales = []

        fj.fic.habitacional.each { hab ->
            Habitacional myHab = new Habitacional()
            myHab.superficie = hab.superficie
            myHab.sbEnergiaElectrica = hab.sbEnergiaElectrica
            myHab.sbAguaPotable = hab.sbAguaPotable
            myHab.sbAlcantarillado = hab.sbAlcantarillado
            myHab.sbComunicaciones = hab.sbComunicaciones
            myHab.legalizacion = hab.legalizacion
            myHab.venta = hab.venta
            myHab.oferta = hab.oferta
            myHab.arriendo = hab.arriendo
            myHab.fechaCreacion = string2dateTime(hab.fechaCreacion)
            myHab.fechaActualizacion = string2dateTime(hab.fechaActualizacion)

            habitacionales << myHab
        }

        return habitacionales
    }

            Date string2date(String dd) {
                if(dd != null) {
                    def y =  dd[1..4].toInteger()
                    def m =  dd[5..6].toInteger()
                    def d =  dd[7..8].toInteger()
                    return new Date(y-1900,m-1,d,0,0,0)
                } else
                    return null
            }

            Date string2dateTime(String dt) {
                if(dt !=  null) {
                    def y =  dt[1..4].toInteger()
                    def m =  dt[5..6].toInteger()
                    def d =  dt[7..8].toInteger()
                    def h =  dt[10..11].toInteger()
                    def mi = dt[12..13].toInteger()
                    def s =  dt[14..15].toInteger()
                    return new Date(y-1900, m-1, d, h, mi, s)
                } else
                    return null
            }

    def pushFicInHystory(FichaCampo fic) {
        def hdir = '/var/fic/history'
        File fhdir = new File(hdir)
        if( !fhdir.exists() || ( fhdir.exists() && fhdir.isDirectory() ) )
            fhdir.mkdir()
        fic2file(fic,hdir,'H')
    }

    def fic2file(FichaCampo fic, String ddir, String prefix) {
        if(fic != null) {
            String ficFileName = ddir + '/' + prefix + '-' + fic.codigoCatastral + '.json'
            try {
                new File(ficFileName).withWriter { out ->
                    out.println fic2json(fic)
                }
            } catch (e) {
                println ficFileName + ' NOT GENERATED . . .'
            }

        }
    }

    def files2fic(String ddir, String prefix) {
        if(ddir != null) {
            def fdir = new File(ddir)
            if(fdir.exists() && fdir.isDirectory()) {
                fdir.listFiles().each { File f ->
                    def absPath = f.name
                    if( absPath.startsWith(prefix) && absPath.endsWith('.json') ) {
                        absPath = f.absolutePath
                        def jsonText = new File(absPath).text
                        FichaCampo fic = json2fic(jsonText)
                        if(evalFicForLoading(fic)) {
                            if(fic.id == null) {
                                fic.save(flush:true)
                                if(fic.hasErrors()) {
                                    fic.errors.each { err ->
                                        registerImportEvent( 'ERROR',
                                                "CREACION DE FIC",
                                                "${fic.numeroFicha} | ${fic.codigoCatastral}",
                                                err.getFieldError().field,
                                                err.getFieldError().rejectedValue.toString(),
                                                err.getFieldError().defaultMessage,
                                                err.getFieldError().getArguments())
                                    }
                                } else {
                                }
                                loadCoberturas(fic,jsonText)
                                loadHabitacional(fic,jsonText)
                            } else {
                                FichaCampo myFc = FichaCampo.get(fic.id)
                                myFc.properties = fic.properties
                                myFc.save(flush:true)
                                if(myFc.hasErrors()) {
                                    fic.errors.each { err ->
                                        registerImportEvent( 'ERROR',
                                                "ACTUALIZACION DE FIC",
                                                "${myFc.numeroFicha} | ${myFc.codigoCatastral}",
                                                err.getFieldError().field,
                                                err.getFieldError().rejectedValue.toString(),
                                                err.getFieldError().defaultMessage,
                                                err.getFieldError().getArguments())
                                    }
                                } else {
                                }
                                Cobertura.findAllByFichaCampo(myFc).each { it.delete(flush:true) }
                                Habitacional.findAllByFichaCampo(myFc).each { it.delete(flush:true) }
                                loadCoberturas(myFc,jsonText)
                                loadHabitacional(myFc,jsonText)
                            }
                        }
                    }
                }
            }
        }
    }

    def registerImportEvent(type, op, refs, field, regValue, msg, args) {
        println "${type}, ${op}, ${refs}, ${field}, ${regValue}, ${MessageFormat.format(msg, args)}"
    }

    def loadCoberturas(FichaCampo fic,String s) {
        def coberturas = json2coberturas(s)
        coberturas.each { Cobertura cob ->
            cob.fichaCampo = FichaCampo.findByCodigoCatastral(fic.codigoCatastral)
            cob.save(flush:true)
        }
    }

    def loadHabitacional(FichaCampo fic,String s) {
        def habitaciones = json2habitacional(s)
        habitaciones.each { Habitacional hab ->
            hab.fichaCampo = FichaCampo.findByCodigoCatastral(fic.codigoCatastral)
            hab.save(flush:true)
        }
    }

    boolean evalFicForLoading(FichaCampo fic) {
        boolean loadFic = ( ( fic != null ) &&
                            ( fic.statusLevantamiento == 'PARA ENTREGA' ) &&
                            ( fic.codigoCatastral != null) )
        if(loadFic) {
            FichaCampo rFic = FichaCampo.findByCodigoCatastral(fic.codigoCatastral)
            loadFic = ( rFic == null ) ||
                      ( ( rFic.statusControl == null || rFic.statusControl == 'RECHAZADA' ) )
            if( loadFic && rFic && ( rFic.statusControl == 'RECHAZADA' || rFic.statusControl == null ) ) {
                fic.id = rFic.id
            }
        }
        return loadFic
    }

}
