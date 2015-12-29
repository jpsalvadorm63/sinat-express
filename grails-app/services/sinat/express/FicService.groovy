package sinat.express

import grails.transaction.Transactional

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
        }

        return builder

        /* ------------------------------------------------ TEST in groovy consoles:
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
        def codigoCatastral = fj.fic.encabezado.codigoCatastral

        FichaCampo fc = FichaCampo.findByCodigoCatastral(codigoCatastral)
        if(fc == null) {
            fc = new FichaCampo()
            fc.codigoCatastral = codigoCatastral
        } else {
            pushFicInHystory(fc)
        }

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

        fj.fic.coberturas.each { cob ->
            println "cob.tipoCobertura=${cob.tipoCobertura}"
        }
        fc.observaciones = fj.fic.observaciones.observaciones
        return fc
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

    }

}