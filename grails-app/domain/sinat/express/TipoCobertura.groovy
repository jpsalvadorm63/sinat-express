package sinat.express

class TipoCobertura {

  String codigo
  String nombre
  TipoUso tipoUso
  boolean requiereDescripcion

  // campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {

    codigo(size:1..8,nullable:false,unique:true)
    nombre(size:1..64,nullable:false,unique:true)
    tipoUso(nullable:false)
    requiereDescripcion(default:false)

    fechaCreacion(default: new Date())
    fechaActualizacion(default: new Date())
  }

  static mapping = {
    table "tipocobertura"
    version true
    cache true
    sort  nombre:"asc"

    id                  column: "id"
    codigo 					    column:"codigo"
    nombre 					    column:"nombre"
    tipoUso             column:"tipouso_id"
    requiereDescripcion column:"requiereDescripcion"

    fechaCreacion       column: "creacion"
    fechaActualizacion  column: "actualizacion"
  }

  static void fillData() {

    // - - - - - - Para agricultura
    def tipoUso = TipoUso.findByCodigo('agr') ;
    Map ingresar = ['Caz':'Arroz','Caa':'Avena','Cca':'Cebada','Cmz':'Maíz duro (costa)',
                               'Cms':'Maíz suave (sierra)','Cqa':'Quinua','Cto':'Trigo','Can':'Algodón',
                               'Csa':'Sandía','Cmo':'Melón','Cbi':'Brócoli','Cbc':'Cebolla blanca de rama',
                               'Ccl':'Cebolla colorada','Cpc':'Cebolla perla','Cpt':'Pimiento','Cao':'Ajo',
                               'Col':'Col','Cla':'Lechuga','Cpl':'Pepinillo','Cro':'Rábano',
                               'Cra':'Remolacha','Cza':'Zanahoria amarilla','Ctr':'Tomate riñón','Cav':'Arveja',
                               'Cch':'Chocho','Cfl':'Fréjol','Cha':'Haba','Cpa':'Papa','Cya':'Yuca',
                               'Csy':'Soya','Cmi':'Maní','Cfa':'Frutilla','Cbn':'Banano',
                               'Cna':'Naranjilla','Cmr':'Mandarina','Cmn':'Manzana','Cnj':'Naranja',
                               'Cmy':'Maracuyá','Cmu':'Mora','Cpy':'Papaya','Cpñ':'Piña',
                               'Cpx':'Pitahaya','Cpo':'Plátano','Cta':'Tomate de árbol','Ccz':'Caña de azúcar',
                               'Cac':'Abacá','Cpi':'Palmito','Cgh':'Gypsophila ','Flv':'Flores de verano',
                               'Flt':'Flores tropicales','Ccs':'Clavel',
                               'Crs':'Rosa','Cat':'Aguacate','Ccc':'Cacao','Ccf':'Café',
                               'Cln':'Limón','Cmg':'Mango','Cpf':'Palma africana','Ctc':'Tabaco',
                               'Cte':'Té','Opm':'Plantas medicinales','Cht ':'Huerto','Ota':'Otros cultivos agrícolas','Asg':'Asociación agrícola']
    ingresar.each {item ->
      String codigo = item.key
      String nombre = item.value
      if(!TipoCobertura.findByCodigo(codigo)) {
        TipoCobertura tipoCovertura = new TipoCobertura()
        tipoCovertura.codigo = codigo
        tipoCovertura.nombre = nombre
        tipoCovertura.tipoUso = tipoUso
        tipoCovertura.requiereDescripcion = false

        tipoCovertura.version = 1
        tipoCovertura.fechaCreacion = new Date()
        tipoCovertura.fechaActualizacion = new Date()

        tipoCovertura.save(flush:true)
      }
    }
    def temp = TipoCobertura.findByCodigo('Ota')
    if(!temp.requiereDescripcion) {
      temp.requiereDescripcion = true;
      temp.save(flush:true)
    }
    temp = TipoCobertura.findByCodigo('Asg')
    if(!temp.requiereDescripcion) {
      temp.requiereDescripcion = true;
      temp.save(flush:true)
    }

    // - - - - - - Para uso pecuario
    tipoUso = TipoUso.findByCodigo('pec') ;
    ingresar = ['Paa':'Alfalfa','Psc':'Pasto cultivado','Asp':'Asociación silvopastoril']
    ingresar.each {item ->
      String codigo = item.key
      String nombre = item.value
      if(!TipoCobertura.findByCodigo(codigo)) {
        TipoCobertura tipoCovertura = new TipoCobertura()
        tipoCovertura.codigo = codigo
        tipoCovertura.nombre = nombre
        tipoCovertura.tipoUso = tipoUso
        tipoCovertura.requiereDescripcion = false

        tipoCovertura.version = 1
        tipoCovertura.fechaCreacion = new Date()
        tipoCovertura.fechaActualizacion = new Date()

        tipoCovertura.save(flush:true)
      }
    }

    // - - - - - - Para uso forestal
    tipoUso = TipoUso.findByCodigo('for') ;
    ingresar = ['Bba':'Balsa','Bal':'Aliso','Bch':'Chuncho',
                'Bfz':'Fernan sanchez','Bcu':'Cutanga','Bna':'Jacarandá',
                'Bag':'Algarrobo','Bne':'Neem','Bco':'Caucho','Bta':'Teca',
                'Bcg':'Caña guadua o bambú','Bll':'Laurel','Bpo':'Pino',
                'Bpc':'Pachaco','Bse':'Sande','Bpr':'Ciprés','Bma':'Melina',
                'Bpe':'Pigüe','Bpp':'Pichango','Beo':'Eucalipto','Opf':'Otras plantaciones forestales']
    ingresar.each {item ->
      String codigo = item.key
      String nombre = item.value
      if(!TipoCobertura.findByCodigo(codigo)) {
        TipoCobertura tipoCovertura = new TipoCobertura()
        tipoCovertura.codigo = codigo
        tipoCovertura.nombre = nombre
        tipoCovertura.tipoUso = tipoUso
        tipoCovertura.requiereDescripcion = false

        tipoCovertura.version = 1
        tipoCovertura.fechaCreacion = new Date()
        tipoCovertura.fechaActualizacion = new Date()

        tipoCovertura.save(flush:true)
      }
    }
    temp = TipoCobertura.findByCodigo('Opf')
    if(!temp.requiereDescripcion) {
      temp.requiereDescripcion = true;
      temp.save(flush:true)
    }

    // - - - - - - Para conservación
    tipoUso = TipoUso.findByCodigo('cons') ;
    ingresar = ['Vab':'Vegetación arbórea','Vaa':'Vegetación arbustiva','Vha':'Vegetación herbácea','Mal':'Maleza']
    ingresar.each {item ->
      String codigo = item.key
      String nombre = item.value
      if(!TipoCobertura.findByCodigo(codigo)) {
        TipoCobertura tipoCovertura = new TipoCobertura()
        tipoCovertura.codigo = codigo
        tipoCovertura.nombre = nombre
        tipoCovertura.tipoUso = tipoUso
        tipoCovertura.requiereDescripcion = false

        tipoCovertura.version = 1
        tipoCovertura.fechaCreacion = new Date()
        tipoCovertura.fechaActualizacion = new Date()

        tipoCovertura.save(flush:true)
      }
    }

    // - - - - - - Para acuacultura
    tipoUso = TipoUso.findByCodigo('acua') ;
    ingresar = ['Qca':'Camaronea','Qpi':'Pisciculra']
    ingresar.each {item ->
      String codigo = item.key
      String nombre = item.value
      if(!TipoCobertura.findByCodigo(codigo)) {
        TipoCobertura tipoCovertura = new TipoCobertura()
        tipoCovertura.codigo = codigo
        tipoCovertura.nombre = nombre
        tipoCovertura.tipoUso = tipoUso
        tipoCovertura.requiereDescripcion = false

        tipoCovertura.version = 1
        tipoCovertura.fechaCreacion = new Date()
        tipoCovertura.fechaActualizacion = new Date()

        tipoCovertura.save(flush:true)
      }
    }

    // - - - - - - Para acuacultura
    tipoUso = TipoUso.findByCodigo('sin') ;
    ingresar = ['Afl':'Afloramiento rocoso','Are':'Arenal',
                'Abr':'Barranco ','Ala':'Cuerpo de agua natural','Agl':'Glaciar ','Apl':'Playa',
                'Abn':'Banco de arena','Asa':'Area salina']
    ingresar.each {item ->
      String codigo = item.key
      String nombre = item.value
      if(!TipoCobertura.findByCodigo(codigo)) {
        TipoCobertura tipoCovertura = new TipoCobertura()
        tipoCovertura.codigo = codigo
        tipoCovertura.nombre = nombre
        tipoCovertura.tipoUso = tipoUso
        tipoCovertura.requiereDescripcion = false

        tipoCovertura.version = 1
        tipoCovertura.fechaCreacion = new Date()
        tipoCovertura.fechaActualizacion = new Date()

        tipoCovertura.save(flush:true)
      }
    }

    TipoUso.list().each { tu ->
      def n = TipoCobertura.findAllByTipoUso(tu).size()
      if(n == 0 && tu.hasCoberturas) {
        tu.hasCoberturas = false
        tu.save(flush:true)
      } else
      if(n > 0 && !tu.hasCoberturas) {
        tu.hasCoberturas = true
        tu.save(flush:true)
      }
    }

  }

  String toString() { nombre }
  String toString2() { codigo + ' ' + nombre }
  static entityName = "Tipo de Cobertura"
  static entityNamePlural = "Tipos de Cobertura"

}
