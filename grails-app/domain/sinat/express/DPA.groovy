package sinat.express

// YA NO DEBE USARSE - - - en su lugar externos.DPALP
class DPA {

  String codigo
  String nombre
  String nivel
  DPA superior

  // generador de códigos
  String preIndice
  Integer indice

  // CAJA
  Double minx
  Double miny
  Double maxx
  Double maxy

  // - campos de control
  Date fechaCreacion
  Date fechaActualizacion

  static constraints = {
    codigo(size:1..6,nullable:false,unique:true)
    nombre(size:1..80,nullable:false,unique:false)
    nivel(sise:1..16,nullable:false,inList:['PROVINCIA','CANTON','PARROQUIA'])
    superior(nullable:true)

    preIndice(nullable:true,size:0..8)
    indice(nullable:true)

    minx(nullable:true)
    miny(nullable:true)
    maxx(nullable:true)
    maxy(nullable:true)

    fechaCreacion(default:new Date())
    fechaActualizacion(default:new Date())
  }

  static mapping = {
    table 'dpa'
    version true
    cache false

    id                  column:"id"
    codigo 				      column:"codigo"
    nombre				      column:"nombre"
    fechaCreacion 		  column:"creacion"
    fechaActualizacion  column:"actualizacion"
    minx                column:"minx"
    miny                column:"miny"
    maxx                column:"maxx"
    maxy                column:"maxy"
    preIndice           column:"preindice"
    indice              column:"indice"
  }

  static void fillData() {

    Map dpaProvincias = [ '03':'CAÑAR',            '10':'IMBABURA',   '13':'MANABI',          '07':'EL ORO',
                          '01':'AZUAY',            '20':'GALAPAGOS',  '21':'SUCUMBIOS',       '02':'BOLIVAR',
                          '19':'ZAMORA CHINCHIPE', '12':'LOS RIOS',   '22':'ORELLANA',        '15':'NAPO',
                          '16':'PASTAZA',          '05':'COTOPAXI',   '06':'CHIMBORAZO',      '09':'GUAYAS',
                          '11':'LOJA',             '17':'PICHINCHA',  '14':'MORONA SANTIAGO', '18':'TUNGURAHUA',
                          '24':'SANTA ELENA',      '08':'ESMERALDAS', '04':'CARCHI',          '23':'SANTO DOMINGO DE LOS TSACHILAS',
                          '90':'ZONA NO DELIMITADA' ]

    dpaProvincias.each {dpap->
      String codigo = dpap.key
      String nombre = dpap.value
      if(!DPA.findByCodigo(codigo)) {
        def provincia = new DPA()
        provincia.nivel = 'PROVINCIA'
        provincia.codigo = codigo
        provincia.nombre = nombre
        provincia.superior = null
        provincia.version = 1
        provincia.fechaCreacion = new Date()
        provincia.fechaActualizacion = new Date()
        provincia.save(flush:true)
      }
    }

    Map dpaCantones = [
       '0916':'SAMBORONDON','1601':'PASTAZA','0501':'LATACUNGA','1407':'HUAMBOYA',
       '0918':'SANTA LUCIA','1602':'MERA','0502':'LA MANA','1408':'SAN JUAN BOSCO',
       '0919':'SALITRE','1603':'SANTA CLARA','0503':'PANGUA','1409':'TAISHA',
       '0920':'SAN JACINTO DE YAGUACHI','1604':'ARAJUNO','0504':'PUJILI','1410':'LOGROÃƒï¿½O',
       '0921':'PLAYAS','1701':'QUITO','0505':'SALCEDO','1411':'PABLO SEXTO',
       '0922':'SIMON BOLIVAR','1702':'CAYAMBE','0506':'SAQUISILI','0101':'CUENCA',
       '0923':'CRNEL. MARCELINO MARIDUEÑA','1703':'MEJIA','0507':'SIGCHOS','0102':'GIRON',
       '0924':'LOMAS DE SARGENTILLO','1704':'PEDRO MONCAYO','0601':'RIOBAMBA','0103':'GUALACEO',
       '0925':'NOBOL','1705':'RUMIÑAHUI','0602':'ALAUSI','0104':'NABON',
       '0927':'GNRAL. ANTONIO ELIZALDE','1707':'SAN MIGUEL DE LOS BANCOS','0603':'COLTA','0105':'PAUTE',
       '1115':'QUILANGA','1708':'PEDRO VICENTE MALDONADO','0604':'CHAMBO','0106':'PUCARA',
       '1116':'OLMEDO','1709':'PUERTO QUITO','0605':'CHUNCHI','0107':'SAN FERNANDO',
       '1201':'BABAHOYO','1801':'AMBATO','0606':'GUAMOTE','0108':'SANTA ISABEL',
       '1202':'BABA','1802':'BAÑOS DE AGUA SANTA','0607':'GUANO','0109':'SIGSIG',
       '1203':'MONTALVO','2103':'PUTUMAYO','0608':'PALLATANGA','0928':'ISIDRO AYORA',
       '1204':'PUEBLOVIEJO','2104':'SHUSHUFINDI','0609':'PENIPE','1001':'IBARRA',
       '1205':'QUEVEDO','2105':'SUCUMBIOS','0610':'CUMANDA','1002':'ANTONIO ANTE',
       '1206':'URDANETA','2106':'CASCALES','0701':'MACHALA','1003':'COTACACHI',
       '1207':'VENTANAS','2107':'CUYABENO','0702':'ARENILLAS','1004':'OTAVALO',
       '1208':'VINCES','2201':'ORELLANA','0703':'ATAHUALPA','1005':'PIMAMPIRO',
       '1209':'PALENQUE','2202':'AGUARICO','0704':'BALSAS','1006':'SAN MIGUEL DE URCUQUI',
       '1210':'BUENA FE','2203':'LA JOYA DE LOS SACHAS','0705':'CHILLA','1101':'LOJA',
       '1211':'VALENCIA','2204':'LORETO','0706':'EL GUABO','1102':'CALVAS',
       '1212':'MOCACHE','2301':'SANTO DOMINGO','0707':'HUAQUILLAS','1103':'CATAMAYO',
       '1213':'QUINSALOMA','2401':'SANTA ELENA','0708':'MARCABELI','1104':'CELICA',
       '1301':'PORTOVIEJO','2402':'LA LIBERTAD','0709':'PASAJE','1105':'CHAGUARPAMBA',
       '1302':'BOLIVAR','2403':'SALINAS','0710':'PIÑAS','1106':'ESPINDOLA',
       '1303':'CHONE','9001':'LAS GOLONDRINAS','0711':'PORTOVELO','1107':'GONZANAMA',
       '1304':'EL CARMEN','9003':'MANGA DEL CURA','0712':'SANTA ROSA','1108':'MACARA',
       '1305':'FLAVIO ALFARO','9004':'EL PIEDRERO','0713':'ZARUMA','1109':'PALTAS',
       '1306':'JIPIJAPA','0110':'OÑA','0714':'LAS LAJAS','1110':'PUYANGO',
       '1307':'JUNIN','0111':'CHORDELEG','0801':'ESMERALDAS','1111':'SARAGURO',
       '1308':'MANTA','0112':'EL PAN','0802':'ELOY ALFARO','1112':'SOZORANGA',
       '1309':'MONTECRISTI','0113':'SEVILLA DE ORO','0803':'MUISNE','1113':'ZAPOTILLO',
       '1310':'PAJAN','0114':'GUACHAPALA','0804':'QUININDE','1114':'PINDAL',
       '1311':'PICHINCHA','0115':'CAMILO PONCE ENRIQUEZ','0805':'SAN LORENZO','2101':'LAGO AGRIO',
       '1312':'ROCAFUERTE','0201':'GUARANDA','0806':'ATACAMES','2102':'GONZALO PIZARRO',
       '1313':'SANTA ANA','0202':'CHILLANES','0807':'RIOVERDE','2003':'SANTA CRUZ',
       '1314':'SUCRE','0203':'CHIMBO','0808':'LA CONCORDIA','2001':'SAN CRISTOBAL',
       '1315':'TOSAGUA','0204':'ECHEANDIA','0901':'GUAYAQUIL','2002':'ISABELA',
       '1316':'24 DE MAYO','0205':'SAN MIGUEL','0902':'ALFREDO BAQUERIZO MORENO','1803':'CEVALLOS',
       '1317':'PEDERNALES','0206':'CALUMA','0903':'BALAO','1804':'MOCHA',
       '1318':'OLMEDO','0207':'LAS NAVES','0904':'BALZAR','1805':'PATATE',
       '1319':'PUERTO LOPEZ','0301':'AZOGUES','0905':'COLIMES','1806':'QUERO',
       '1320':'JAMA','0302':'BIBLIAN','0906':'DAULE','1807':'SAN PEDRO DE PELILEO',
       '1321':'JARAMIJO','0303':'CAÑAR','0907':'DURAN','1808':'SANTIAGO DE PILLARO',
       '1322':'SAN VICENTE','0304':'LA TRONCAL','0908':'EMPALME','1809':'TISALEO',
       '1401':'MORONA','0305':'EL TAMBO','0909':'EL TRIUNFO','1901':'ZAMORA',
       '1402':'GUALAQUIZA','0306':'DELEG','0910':'MILAGRO','1902':'CHINCHIPE',
       '1403':'LIMON INDANZA','0307':'SUSCAL','0911':'NARANJAL','1903':'NANGARITZA',
       '1412':'TIWINTZA','0401':'TULCAN','0912':'NARANJITO','1904':'YACUAMBI',
       '1501':'TENA','0402':'BOLIVAR','0913':'PALESTINA','1905':'YANTZAZA',
       '1503':'ARCHIDONA','0403':'ESPEJO','0914':'PEDRO CARBO','1906':'EL PANGUI',
       '1504':'EL CHACO','0404':'MIRA','1404':'PALORA','1907':'CENTINELA DEL CONDOR',
       '1507':'QUIJOS','0405':'MONTUFAR','1405':'SANTIAGO','1908':'PALANDA',
       '1509':'CARLOS JULIO AROSEMENA TOLA','0406':'SAN PEDRO DE HUACA','1406':'SUCUA','1909':'PAQUISHA']

    dpaCantones.each {dpac ->
      String codigo = dpac.key
      String nombre = dpac.value
      String codigoProvincia = codigo.substring(0,2)
      def provincia = DPA.findByCodigoAndNivel(codigoProvincia,'PROVINCIA')
      def elcanton = DPA.findByCodigoAndNivel(codigo,'CANTON')
      if(provincia && !elcanton) {
        def canton = new DPA()
        canton.nivel = 'CANTON'
        canton.codigo = codigo
        canton.nombre = nombre
        canton.superior = provincia
        canton.version = 1
        canton.fechaCreacion = new Date()
        canton.fechaActualizacion = new Date()
        canton.save(flush:true)
      }
    }

    Map dpaParroquias
    dpaParroquias = [
       '080352':'DAULE','030160':'TADAY','030361':'ZHUD','080252':'ATAHUALPA (CAB. EN CAMARONES)','080751':'CHONTADURO','092350':'CORONEL MARCELINO MARIDUEÑA (SAN CARLOS)','100455':'SAN JOSE DE QUICHINCHE','111158':'SELVA ALEGRE','140351':'INDANZA','180652':'YANAYACU - MOCHAPATA (CAB. EN YANAYACU)','210153':'GENERAL FARFAN',
       '090350':'BALAO','030250':'BIBLIAN','030362':'VENTURA','080452':'CHURA (CHANCAMA) (CAB. EN EL YERBERO)','080752':'CHUMUNDE','092450':'LOMAS DE SARGENTILLO','100456':'SAN JUAN DE ILUMAN','111159':'URDANETA (PAQUISHAPA)','140353':'SAN ANTONIO (CAB EN SAN ANTONIO CENTRO)','180750':'PELILEO','210155':'EL ENO',
       '010955':'SAN BARTOLOME','030251':'NAZON (CAB. EN PAMPA DE DOMINGUEZ)','030363':'DUCUR','070354':'SAN JOSE','080753':'LAGARTO','092550':'NARCISA DE JESUS','100457':'SAN PABLO','111160':'SUMAYPAMBA','140356':'SAN MIGUEL DE CONCHAY','220254':'TIPUTINI','210156':'PACAYACU',
       '010956':'SAN JOSE DE RARANGA','040450':'MIRA (CHONTAHUASI)','030450':'LA TRONCAL','070355':'SAN JUAN DE CERRO AZUL','080754':'MONTALVO (CAB EN HORQUETA)','092750':'GRAL. ANTONIO ELIZALDE (BUCAY)','100458':'SAN RAFAEL','111250':'SOZORANGA','140357':'STA SUSANA DE CHIVIAZA (CAB EN CHIVIAZA)','220255':'YASUNI','210157':'JAMBELI',
       '011050':'SAN FELIPE DE OÑA','040451':'CONCEPCION','030451':'MANUEL J. CALLE','070450':'BALSAS','060753':'LA PROVIDENCIA','092850':'ISIDRO AYORA','100459':'SELVA ALEGRE (CAB. EN SAN MIGUEL DE PAMPLONA)','111251':'NUEVA FATIMA','140358':'YUNGANZA (CAB EN EL ROSARIO)','220350':'LA JOYA DE LOS SACHAS','210158':'SANTA CECILIA',
       '011051':'SUSUDEL','040452':'JIJON Y CAAMAÑO (CAB. EN RIO BLANCO)','030452':'PANCHO NEGRO','130852':'SANTA MARIANITA (BOCA DE PACOCHE)','090158':'TENGUEL','100150':'SAN MIGUEL DE IBARRA','100550':'PIMAMPIRO','111252':'TACAMOROS','140450':'PALORA (METZERA)','220351':'ENOKANQUI','210250':'LUMBAQUI',
       '011150':'CHORDELEG','040453':'JUAN MONTALVO (SAN IGNACIO DE QUIL)','030550':'EL TAMBO','060256':'PISTISHI (NARIZ DEL DIABLO)','091150':'NARANJAL','100151':'AMBUQUI','100551':'CHUGA','111350':'ZAPOTILLO','140451':'ARAPICOS','220352':'POMPEYA','210251':'EL REVENTADOR',
       '011151':'PRINCIPAL','040550':'SAN GABRIEL','030650':'DELEG','090652':'JUAN BAUTISTA AGUIRRE (LOS TINTOS)','170175':'PIFO','100152':'ANGOCHAGUA','100552':'MARIANO ACOSTA','111351':'MANGAHURCO (CAZADEROS)','140452':'CUMANDA (CAB EN COLONIA AGRICOLA SEVILLA DEL ORO)','220353':'SAN CARLOS','210252':'GONZALO PIZARRO',
       '011152':'LA UNION','040551':'CRISTOBAL COLON','030651':'SOLANO','100154':'LA ESPERANZA','170176':'PINTAG','060757':'SAN JOSE DEL CHAZO','100553':'SAN  FCO. DE SIGSIPAMBA','111352':'GARZAREAL','140454':'SANGAY (CAB EN NAYAMANACA)','220354':'SAN SEBASTIAN DEL COCA','210254':'PUERTO LIBRE',
       '011153':'LUIS GALARZA ORELLANA (CAB. EN DELEGSOL)','040552':'CHITAN DE NAVARRETE','050453':'GUANGAJE','100157':'SAN ANTONIO','170177':'POMASQUI','060758':'SANTA FE DE GALAN','100650':'URCUQUI','111353':'LIMONES','170454':'TUPIGACHI','220355':'LAGO SAN PEDRO','210350':'PUERTO EL CARMEN DEL PUTUMAYO',
       '011154':'SAN MARTIN DE PUZHIO','040553':'FERNANDEZ SALVADOR','130356':'RICAURTE','131550':'TOSAGUA','100356':'QUIROGA','100253':'SAN JOSE DE CHALTURA','100651':'CAHUASQUI','111354':'PALETILLAS','170550':'SANGOLQUI','220356':'RUMIPAMBA','210351':'PALMA ROJA',
       '010150':'CUENCA','040554':'LA PAZ','130357':'SAN ANTONIO','140160':'ZUÑA (ZUÑAC)','110156':'MALACATOS (VALLADOLID)','110655':'EL INGENIO','100652':'LA MERCED DE BUENOS AIRES','071356':'MALVAS','170551':'COTOGCHOA','180350':'CEVALLOS','210352':'PUERTO BOLIVAR (PUERTO MONTUFAR)',
       '010151':'BAÑOS','040555':'PIARTAL','130450':'EL CARMEN','050551':'ANTONIO JOSE HOLGUIN (SANTA LUCIA)','110653':'SANTA TERESITA','110656':'EL AIRO','100653':'PABLO ARENAS','111550':'QUILANGA','170552':'RUMIPAMBA','180450':'MOCHA','210353':'PUERTO RODRIGUEZ',
       '010152':'CUMBE','040650':'HUACA','130451':'WILFRIDO LOOR MOREIRA (MAICITO)','050552':'CUSUBAMBA','110654':'27 DE ABRIL (CAB. EN LA NARANJA)','110750':'GONZANAMA','100654':'SAN BLAS','111551':'FUNDOCHAMBA','170750':'SAN MIGUEL DE LOS BANCOS','190350':'GUAYZIMI','210354':'SANTA ELENA',
       '010153':'CHAUCHA','040651':'MARISCAL SUCRE','130452':'SAN PEDRO DE SUMA','050553':'MULALILLO','111450':'PINDAL','140552':'CHUPIANZA','070451':'BELLAMARIA','111552':'SAN ANTONIO DE LAS ARADAS (CAB. EN LAS ARADAS)','170751':'MINDO','190351':'ZURMI','210450':'SHUSHUFINDI',
       '010154':'CHECA (JIDCAY)','050150':'LATACUNGA','130550':'FLAVIO ALFARO','050554':'MULLIQUINDIL (SANTA ANA)','111451':'CHAQUINAL','140553':'PATUCA','070550':'CHILLA','111650':'OLMEDO','170850':'PEDRO VICENTE MALDONADO','190352':'NUEVO PARAISO','210451':'LIMONCOCHA',
       '010155':'CHIQUINTAD','050151':'ALAQUES (ALAQUEZ)','130551':'SAN FRANCISCO DE NOVILLO (CAB. EN NOVILLO)','050555':'PANSALEO','140254':'CHIGUINDA','140554':'SAN LUIS DE EL ACHO (CAB EN EL ACHO)','070650':'EL GUABO','111651':'LA TINGUE','170950':'PUERTO QUITO','190450':'28 DE MAYO (SAN JOSE DE YACUAMBI)','210452':'PAÑACOCHA',
       '010156':'LLACAO','050152':'BELISARIO QUEVEDO (GUANAILIN)','130651':'AMERICA','050650':'SAQUISILI','140255':'EL ROSARIO','140556':'TAYUZA','070651':'BARBONES (SUCRE)','120150':'BABAHOYO','180150':'AMBATO','190451':'LA PAZ','210453':'SAN ROQUE (CAB. EN SAN VICENTE)',
       '010157':'MOLLETURO','050153':'GUAITACAMA (GUAYTACAMA)','130950':'MONTECRISTI','050651':'CANCHAGUA','080453':'MALIMPIA','130352':'CANUTO','100358':'VACAS GALINDO (EL CHURO) (CAB. EN SAN MIGUEL ALTO)','120152':'CARACOL','180158':'MONTALVO','190452':'TUTUPALI','210454':'SAN PEDRO DE LOS COFANES',
       '010158':'NULTI','050154':'JOSEGUANGO BAJO','140151':'ALSHI (CAB EN 9 DE OCTUBRE)','130652':'EL ANEGADO (CAB. EN ELOY ALFARO)','130355':'ELOY ALFARO','160156':'MONTALVO (ANDOAS)','100655':'TUMBABIRO','120153':'FEBRES CORDERO (LAS JUNTAS)','180159':'PASA','190550':'YANTZAZA (YANZATZA)','210455':'SIETE DE JULIO',
       '011250':'EL PAN','050156':'MULALO','140153':'GENERAL PROAÑO','130653':'JULCUY','170184':'TUMBACO','160157':'POMONA','110150':'LOJA','120154':'PIMOCHA','180160':'PICAIGUA','190551':'CHICAÑA','210550':'LA BONITA',
       '011253':'SAN VICENTE','130850':'MANTA','140156':'SAN ISIDRO','130654':'LA UNION','170185':'YARUQUI','160158':'RIO CORRIENTES','110151':'CHANTACO','120155':'LA UNION','180161':'PILAGUIN (PILAHUIN)','190553':'LOS ENCUENTROS','210551':'EL PLAYON DE SAN FRANCISCO',
       '011350':'SEVILLA DE ORO','130851':'SAN LORENZO','080851':'MONTERREY','130656':'MEMBRILLAL','170186':'ZAMBIZA','160159':'RIO TIGRE','110152':'CHUQUIRIBAMBA','120250':'BABA','150453':'OYACACHI','190650':'EL PANGUI','210552':'LA SOFIA',
       '011351':'AMALUZA','131650':'SUCRE','060952':'MATUS','130657':'PEDRO PABLO GOMEZ','170250':'CAYAMBE','160161':'SARAYACU','110153':'EL CISNE','071357':'MULUNCAY GRANDE','170158':'CHAVEZPAMBA','190651':'EL GUISME','210651':'SANTA ROSA DE SUCUMBIOS',
       '010168':'TARQUI','131651':'BELLAVISTA','060953':'PUELA','130351':'BOYACA','170251':'ASCAZUBI','160162':'SIMON BOLIVAR (CAB. EN MUSHULLACTA)','070953':'LA PEAÑA','071358':'SINSAO','150452':'LINARES','190652':'PACHICUTZA','210652':'SEVILLA',
       '010169':'TURI','131652':'NOBOA','091151':'JESUS MARIA','140455':'16 DE AGOSTO','170252':'CANGAHUA','160163':'TARQUI','070954':'PROGRESO','071359':'SALVIAS','170157':'CUMBAYA','190653':'TUNDAYME','210750':'TARAPOA',
       '010170':'VALLE','140164':'RIO BLANCO','091152':'SAN CARLOS','170357':'UYUMBICHO','170253':'OLMEDO (PESILLO)','160164':'TENIENTE HUGO ORTIZ','070955':'UZHCURRUMI','071450':'LA VICTORIA','220251':'CAPITAN AUGUSTO RIVADENEYRA','190750':'ZUMBI','210751':'CUYABENO',
       '010171':'VICTORIA DEL PORTETE (IRQUIS)','090250':'ALFREDO BAQUERIZO MORENO (JUJAN)','092251':'CRNEL. LORENZO DE GARAICOA (PEDREGAL)','170450':'TABACUNDO','170254':'OTON','160165':'VERACRUZ (INDILLAMA) (CAB. EN INDILLAMA)','110160':'TAQUIL (MIGUEL RIOFRIO)','071451':'LA LIBERTAD','220252':'CONONACO','180551':'EL TRIUNFO','210752':'AGUAS NEGRAS',
       '010250':'GIRON','090653':'LAUREL','100250':'ATUNTAQUI','170451':'LA ESPERANZA','170255':'SANTA ROSA DE CUZUBAMBA','160166':'EL TRIUNFO','110161':'VILCABAMBA (VICTORIA)','071452':'EL PARAISO','150150':'TENA','190852':'SAN FRANCISCO DEL VERGEL','220150':'PUERTO FRANCISCO DE ORELLANA (EL COCA)',
       '010251':'ASUNCION','091950':'EL SALITRE (LAS RAMAS)','110551':'BUENAVISTA','170452':'MALCHINGUI','170350':'MACHACHI','160250':'MERA','110162':'YANGANA (ARSENIO CASTILLO)','120450':'PUEBLOVIEJO','150151':'AHUANO','190853':'VALLADOLID','220151':'DAYUMA',
       '010252':'SAN GERARDO','100155':'LITA','110552':'EL ROSARIO','170453':'TOCACHI','170351':'ALOAG','160251':'MADRE TIERRA','110163':'QUINARA','120451':'PUERTO PECHICHE','150153':'CHONTAPUNTA','190854':'LA CANELA','220152':'TARACOA (NUEVA ESPERANZA: YUCA)',
       '010350':'GUALACEO','110157':'SAN LUCAS','120252':'ISLA DE BEJUCAL','060151':'CACHA (CAB. EN MACHANGARA)','150454':'SANTA ROSA','170161':'GUALEA','110250':'CARIAMANGA','120452':'SAN JUAN','150154':'PANO','190950':'PAQUISHA','220153':'ALEJANDRO LABAKA',
       '010451':'COCHAPATA','110158':'SAN PEDRO DE VILCABAMBA','120350':'MONTALVO','060152':'CALPI','150455':'SARDINAS','170162':'GUANGOPOLO','110251':'COLAISACA','120550':'QUEVEDO','150155':'PUERTO MISAHUALLI','190951':'BELLAVISTA','220154':'EL DORADO',
       '010452':'EL PROGRESO (CAB. EN ZHOTA)','110159':'SANTIAGO','150750':'BAEZA','070950':'PASAJE','170159':'CHECA (CHILPA)','170163':'GUAYLLABAMBA','110252':'EL LUCERO','120553':'SAN CARLOS','150156':'PUERTO NAPO','190952':'NUEVO QUITO','240350':'SALINAS',
       '010453':'LAS NIEVES (CHAYA)','111452':'12 DE DICIEMBRE (CAB. EN ACHIOTES)','160152':'CANELOS','070951':'BUENAVISTA','170160':'EL QUINCHE','170164':'LA MERCED','110253':'UTUANA','120555':'LA ESPERANZA','150157':'TALAG','220450':'LORETO','240351':'ANCONCITO',
       '010550':'PAUTE','111453':'MILAGROS','160154':'DIEZ DE AGOSTO','070952':'CASACAY','060257':'PUMALLACTA','170165':'LLANO CHICO','110254':'SANGUILLIN','120650':'CATARAMA','150158':'SAN JUAN DE MUYUNA','220451':'AVILA (CAB. EN HUIRUNO)',
       '010552':'BULAN (JOSE VICTOR IZQUIERDO)','080454':'VICHE','160155':'FATIMA','130952':'LA PILA','060754':'SAN ANDRES','170166':'LLOA','110350':'CATAMAYO (LA TOMA)','120651':'RICAURTE','150350':'ARCHIDONA','220452':'PUERTO MURIALDO',
       '010553':'CHICAN (GUILLERMO ORTEGA)','080455':'LA UNION','070353':'MILAGRO','131050':'PAJAN','080852':'LA VILLEGAS','170168':'NANEGAL','110351':'EL TAMBO','120750':'VENTANAS','150352':'COTUNDO','220453':'SAN JOSE DE PAYAMINO',
       '010554':'EL CABO','150756':'SUMACO','130552':'ZAPALLO','131051':'CAMPOZANO (LA PALMA DE PAJAN)','080853':'PLAN PILOTO','170169':'NANEGALITO','110352':'GUAYQUICHUMA','120752':'ZAPOTAL','150354':'SAN PABLO DE USHPAYACU','220454':'SAN JOSE DE DAHUANO',
       '010556':'GUARAINAG','150950':'CARLOS JULIO AROSEMENA TOLA','130650':'JIPIJAPA','131052':'CASCOL','090150':'GUAYAQUIL','170170':'NAYON','070851':'EL INGENIO','120753':'CHACARITA','150450':'EL CHACO','220455':'SAN VICENTE DE HUATICOCHA',
       '010559':'SAN CRISTOBAL (CARLOS ORDOÑEZ LAZO)','160150':'PUYO','130658':'PUERTO DE CAYO','131053':'GUALE','090152':'JUAN GOMEZ RENDON (PROGRESO)','170171':'NONO','110353':'SAN PEDRO DE LA BENDITA','120754':'LOS ANGELES','150451':'GONZALO DIAZ DE PINEDA (EL BOMBON)','230150':'SANTO DOMINGO DE LOS COLORADOS',
       '010561':'TOMEBAMBA','010166':'SIDCAY','050455':'LA VICTORIA','131054':'LASCANO','090153':'MORRO','060759':'VALPARAISO','110354':'ZAMBI','120850':'VINCES','160252':'SHELL','230151':'ALLURIQUIN',
       '010562':'DUG DUG','040352':'LA LIBERTAD (ALIZO)','050456':'PILALO','131150':'PICHINCHA','090156':'POSORJA','100357':'6 DE JULIO DE CUELLAJE (CAB. EN CUELLAJE)','110450':'CELICA','120851':'ANTONIO SOTOMAYOR (CAB. EN PLAYAS DE VINCES)','160350':'SANTA CLARA','230152':'PUERTO LIMON',
       '010650':'PUCARA','040353':'SAN ISIDRO','050457':'TINGO','131151':'BARRAGANETE','090157':'PUNA','111152':'EL TABLON','110451':'CRUZPAMBA (CAB EN CARLOS BUSTAMANTE)','120950':'PALENQUE','160351':'SAN JOSE','230153':'LUZ DE AMERICA',
       '010652':'SAN RAFAEL DE SHARUG','030155':'LUIS CORDERO','050458':'ZUMBAHUA','131152':'SAN SEBASTIAN','060258':'SEVILLA','111153':'LLUZHAPA','110455':'POZUL (SAN JUAN DE POZUL)','121050':'SAN JACINTO DE BUENA FE','160450':'ARAJUNO','230154':'SAN JACINTO DEL BUA',
       '010750':'SAN FERNANDO','040351':'EL GOALTAL','050550':'SAN MIGUEL','131250':'ROCAFUERTE','060259':'SIBAMBE','080254':'LA TOLA','070956':'CAÑAQUEMADA','121051':'PATRICIA PILAR','160451':'CURARAY','180552':'LOS ANDES (CAB. EN POATUG)',
       '010751':'CHUMBLIN','030750':'SUSCAL','130750':'JUNIN','131350':'SANTA ANA DE VUELTA LARGA','060260':'TIXAN','140550':'SANTIAGO DE MENDEZ','071050':'PIÑAS','121150':'VALENCIA','170150':'QUITO','180553':'SUCRE (CAB. EN SUCRE-PATATE URCU)',
       '010850':'SANTA ISABEL (CHAGUARURCO)','040150':'TULCAN','060150':'RIOBAMBA','131351':'AYACUCHO','060350':'VILLA LA UNION (CAJABAMBA)','140551':'COPAL','071051':'CAPIRO (CAB. EN LA CAPILLA DE CAPIRO)','121250':'MOCACHE','170151':'ALANGASI','180650':'QUERO',
       '010851':'ABDON CALDERON (LA UNION)','040151':'EL CARMELO (EL PUN)','132251':'CANOA','131352':'HONORATO VASQUEZ (CAB EN VASQUEZ)','060351':'CAÑI','140557':'SAN FRANCISCO DE CHINIMBIMI','071052':'LA BOCANA','121350':'QUINSALOMA','170152':'AMAGUAÑA','180651':'RUMIPAMBA',
       '010853':'ZHAGLLI (SHAGLLI )','050157':'11 DE NOVIEMBRE (ILINCHISI)','140150':'MACAS','131353':'LA UNION','060352':'COLUMBE','140650':'SUCUA','071053':'MOROMORO (CAB. EN EL VADO)','130150':'PORTOVIEJO','170153':'ATAHUALPA (HABASPAMBA)','180752':'BOLIVAR',
       '010854':'SAN SALVADOR DE CAÑ˜ARIBAMBA','050158':'POALO','080850':'LA CONCORDIA','131355':'SAN PABLO (CAB EN PUEBLO NUEVO)','060353':'JUAN DE VELASCO (PANGOR)','140651':'ASUNCION','071054':'PIEDRAS','130151':'ABDON CALDERON (SAN FRANCISCO)','170154':'CALACALI','180753':'COTALO',
       '010950':'SIGSIG','050159':'SAN JUAN DE PASTOCALLE','060951':'EL ALTAR','131450':'BAHIA DE CARAQUEZ','060354':'SANTIAGO DE QUITO (CAB. EN SAN ANTONIO DE QUITO)','140652':'HUAMBI','071055':'SAN ROQUE (AMBROSIO MALDONADO)','140250':'GUALAQUIZA','170155':'CALDERON (CARAPUNGO)','240152':'COLONCHE',
       '010951':'CUCHIL (CUTCHIL)','050161':'TANICUCHI','091850':'SANTA LUCIA','131453':'CHARAPOTO','060450':'CHAMBO','140655':'SANTA MARIANITA DE JESUS','071056':'SARACAY','140251':'AMAZONAS (ROSARIO DE CUYES)','170156':'CONOCOTO','240153':'CHANDUY',
       '010952':'GIMA','050162':'TOACASO','150753':'PAPALLACTA','131457':'SAN ISIDRO','060550':'CHUNCHI','140750':'HUAMBOYA','071150':'PORTOVELO','071453':'SAN ISIDRO','170352':'ALOASI','240154':'MANGLARALTO',
       '011352':'PALMAS','050250':'LA MANA','150754':'SAN FRANCISCO DE BORJA (VIRGILIO DAVILA)','060153':'CUBIJIES','060551':'CAPZOL','140751':'CHIGUAZA','071151':'CURTINCAPA','080150':'ESMERALDAS','170353':'CUTUGLAHUA','240155':'SIMON BOLIVAR (JULIO MORENO)',
       '011450':'GUACHAPALA','050251':'GUASAGANDA (CAB. EN GUASAGANDA CENTRO)','100450':'OTAVALO','060154':'FLORES','060850':'PALLATANGA','140850':'SAN JUAN BOSCO','071152':'MORALES','080152':'CAMARONES (CAB. EN SAN VICENTE)','170354':'EL CHAUPI','240156':'SAN JOSE DE ANCON',
       '011550':'CAMILO PONCE ENRIQUEZ','050252':'PUCAYACU','110457':'TNTE. MAXIMILIANO RODRIGUEZ LOAIZA','060155':'LICAN','060553':'GONZOL','140851':'PAN DE AZUCAR','071153':'SALATI','080153':'CRNEL. CARLOS CONCHA TORRES (CAB. EN HUELE)','180151':'AMBATILLO','240250':'LA LIBERTAD',
       '011551':'EL CARMEN DE PIJILI','050350':'EL CORAZON','110550':'CHAGUARPAMBA','060156':'LICTO','060554':'LLAGOS','140852':'SAN CARLOS DE LIMON','071250':'SANTA ROSA','080154':'CHINCA','180162':'QUISAPINCHA (QUIZAPINCHA)','210554':'SANTA BARBARA',
       '020150':'GUARANDA','050351':'MORASPUNGO','111150':'SARAGURO','060157':'PUNGALA','060650':'GUAMOTE','140853':'SAN JACINTO DE WAKAMBEIS','071251':'BELLAVISTA','080159':'MAJUA','180163':'SAN BARTOLOME DE PINLLOG','210650':'EL DORADO DE CASCALES',
       '020151':'FACUNDO VELA','050352':'PINLLOPATA','111151':'EL PARAISO DE CELEN','080255':'LUIS VARGAS TORRES (CAB. EN PLAYA DE ORO)','060651':'CEBADAS','140854':'SANTIAGO DE PANANZA','071252':'JAMBELI','080163':'SAN MATEO','180164':'SAN FERNANDO (PASA SAN FERNANDO)','210553':'ROSA FLORIDA',
       '020153':'JULIO E. MORENO (CATANAHUAN GRANDE)','050353':'RAMON CAMPAÑA','120251':'GUARE','080256':'MALDONADO','060652':'PALMIRA','140950':'TAISHA','071253':'LA AVANZADA','080165':'TABIAZO','180165':'SANTA ROSA','180754':'CHIQUICHA (CAB. EN CHIQUICHA GRANDE)',
       '020155':'SALINAS','050450':'PUJILI','080253':'BORBON','080257':'PAMPANAL DE BOLIVAR','060750':'GUANO','140951':'HUASAGA (CAB EN WAMPUIK)','071254':'SAN ANTONIO','080166':'TACHINA','240150':'SANTA ELENA','240352':'JOSE LUIS TAMAYO (MUEY)',
       '020156':'SAN LORENZO','050451':'ANGAMARCA','080560':'TAMBILLO','080258':'SAN FRANCISCO DE ONZOLE','090654':'LIMONAL','140952':'MACUMA','071255':'TORATA','080168':'VUELTA LARGA','240151':'ATAHUALPA','900151':'LAS GOLONDRINAS',
       '010954':'LUDO','030252':'SAN FRANCISCO DE SAGEO','170178':'PUELLARO','080259':'SANTO DOMINGO DE ONZOLE','090656':'LOS LOJAS (ENRIQUE BAQUERIZO MORENO)','140953':'TUUTINENTZA','071256':'VICTORIA','080250':'VALDEZ (LIMONES)','220253':'SANTA MARIA DE HUIRIRIMA','900351':'MANGA DEL CURA',
       '040155':'PIOTER','040156':'TOBAR DONOSO (LA BOCANA DE CAMUMBI)','170179':'PUEMBO','080260':'SELVA ALEGRE','090750':'ELOY ALFARO (DURAN)','140954':'PUMPUENTSA','071257':'BELLAMARIA','080251':'ANCHAYACU','220357':'TRES DE NOVIEMBRE','900451':'EL PIEDRERO',
       '010352':'DANIEL CORDOVA TORAL (EL ORIENTE)','040157':'TUFIÑO','170180':'SAN ANTONIO','080261':'TELEMBI','090850':'VELASCO IBARRA (CAB. EL EMPALME)','141050':'LOGROÑO','110553':'SANTA RUFINA','080264':'TIMBIRE','180152':'ATAHUALPA (CHISALATA)','220155':'EL EDEN',
       '010353':'JADAN','040158':'URBINA (TAYA)','170181':'SAN JOSE DE MINAS','080262':'COLON ELOY DEL MARIA','060552':'COMPUD','141051':'YAUPI','110554':'AMARILLOS','080265':'SANTA LUCIA DE LAS PEÑAS','180153':'AUGUSTO N. MARTINEZ (MUNDUGLEO)','180755':'EL ROSARIO (RUMICHACA)',
       '010354':'MARIANO MORENO','040159':'EL CHICAL','170183':'TABABELA','080263':'SAN JOSE DE CAYAPAS','090450':'BALZAR','141052':'SHIMPIS','110650':'AMALUZA','080350':'MUISNE','180154':'CONSTANTINO FERNANDEZ (CAB. EN CULLITAHUA)','180756':'GARCIA MORENO (CHUMAQUI)',
       '010356':'REMIGIO CRESPO TORAL (GULAG)','040161':'SANTA MARTHA DE CUBA','131551':'BACHILLERO','131653':'ARQ. SIXTO DURAN BALLEN','090550':'COLIMES','141150':'PABLO SEXTO','110651':'BELLAVISTA','080351':'BOLIVAR','180250':'BAÑOS DE AGUA SANTA','180757':'GUAMBALO (HUAMBALO)',
       '010357':'SAN JUAN','040250':'BOLIVAR','131552':'ANGEL PEDRO GILER (LA ESTANCILLA)','131750':'PEDERNALES','090551':'SAN JACINTO','141250':'SANTIAGO','071350':'ZARUMA','080353':'GALERA','190251':'CHITO','180758':'SALASACA',
       '010358':'ZHIDMAD','040251':'GARCIA MORENO','140162':'CUCHAENTZA','131751':'COJIMIES','090650':'DAULE','141251':'SAN JOSE DE MORONA','071351':'ABAÑIN','080354':'QUINGUE (OLMEDO PERDOMO FRANCO)','220358':'UNION MILAGREÑA','180850':'PILLARO',
       '010359':'LUIS CORDERO VEGA','010159':'OCTAVIO CORDERO PALACIOS (STA. ROSA)','132150':'JARAMIJO','131752':'10 DE AGOSTO','091651':'TARIFA','060956':'BILBAO (CAB. EN QUILLUYACU)','110751':'CHANGAIMINA (LA LIBERTAD)','080355':'SALIMA','190752':'TRIUNFO-DORADO','180851':'BAQUERIZO MORENO',
       '010360':'SIMON BOLIVAR (CAB. EN GAÑANZOL)','010160':'PACCHA','132250':'SAN VICENTE','131753':'ATAHUALPA','090851':'GUAYAS (PUEBLO NUEVO)','061050':'CUMANDA','110753':'NAMBACOLA','080356':'SAN FRANCISCO','180155':'HUACHI GRANDE','180852':'EMILIO MARIA TERAN (RUMIPAMBA)',
       '010450':'NABON','010161':'QUINGEO','080755':'ROCAFUERTE','131850':'OLMEDO','090852':'EL ROSARIO','070150':'MACHALA','110754':'PURUNUMA (EGUIGUREN)','080357':'SAN GREGORIO','180156':'IZAMBA','180853':'MARCOS ESPINEL (CHACATA)',
       '020157':'SAN SIMON (YACOTO)','010162':'RICAURTE','060950':'PENIPE','131950':'PUERTO LOPEZ','090950':'EL TRIUNFO','070152':'EL RETIRO','110756':'SACAPALCA','080358':'SAN JOSE DE CHAMANGA','190850':'PALANDA','180854':'PRESIDENTE URBINA (CHAGRAPAMBA -PATZUCUL)',
       '020158':'SANTA FE (SANTA FE)','010163':'SAN JOAQUIN','092250':'SIMON BOLIVAR','131951':'MACHALILLA','091050':'MILAGRO','070250':'ARENILLAS','110850':'MACARA','080450':'ROSA ZARATE (QUININDE)','190851':'EL PORVENIR DEL CARMEN','180855':'SAN ANDRES',
       '020159':'SIMIATUG','010164':'SANTA ANA','170172':'PACTO','131952':'SALANGO','091051':'CHOBO','070251':'CHACRAS','110851':'LARAMA','080451':'CUBE','190259':'SAN ANDRES','180856':'SAN JOSE DE POALO',
       '020160':'SAN LUIS DE PAMBIL','010165':'SAYAUSI','050652':'CHANTILIN','132050':'JAMA','091053':'MARISCAL SUCRE (HUAQUES)','070254':'PALMALES','110852':'LA VICTORIA','080553':'CALDERON','190753':'PANGUINTZA','180857':'SAN MIGUELITO',
       '020250':'CHILLANES','010953':'GUEL','050653':'COCHAPAMBA','060158':'PUNIN','060751':'GUANANDO','070255':'CARCABON','110853':'SABIANGO (LA CAPILLA)','080554':'CARONDELET','230155':'VALLE HERMOSO','180950':'TISALEO',
       '020251':'SAN JOSE DEL TAMBO (TAMBOPAMBA)','030151':'COJITAMBO','050750':'SIGCHOS','060159':'QUIMIAG','091153':'SANTA ROSA DE FLANDES','070352':'CORDONCILLO','110950':'CATACOCHA','080555':'5 DE JUNIO (CAB. EN UIMBI)','230156':'EL ESFUERZO','180951':'QUINCHICOTO',
       '020350':'SAN JOSE DE CHIMBO','030153':'GUAPAN','050751':'CHUGCHILLAN','060160':'SAN JUAN','091154':'TAURA','100254':'SAN ROQUE','110951':'CANGONAMA','080556':'CONCEPCION','180157':'JUAN BENIGNO VELA','190150':'ZAMORA',
       '020351':'ASUNCION (ASANCOTO)','030154':'JAVIER LOYOLA (CHUQUIPATA)','050752':'ISINLIVI','060161':'SAN LUIS','091250':'NARANJITO','100350':'COTACACHI','110952':'GUACHANAMA','080557':'MATAJE (CAB. EN SANTANDER)','220156':'GARCIA MORENO','190151':'CUMBARATZA',
       '020353':'MAGDALENA (CHAPACOTO)','040252':'LOS ANDES','050753':'LAS PAMPAS','060250':'ALAUSI','091350':'PALESTINA','070350':'PACCHA','110954':'LAURO GUERRERO','080558':'SAN JAVIER DE CACHAVI (CAB. EN SAN JAVIER)','230157':'SANTA MARIA DEL TOACHI','190152':'GUADALUPE',
       '020354':'SAN SEBASTIAN','040253':'MONTE OLIVO','050754':'PALO QUEMADO','060251':'ACHUPALLAS','091450':'PEDRO CARBO','070351':'AYAPAMBA','110956':'ORIANGA','080559':'SANTA RITA','190250':'ZUMBA','190153':'IMBANA (LA VICTORIA DE IMBANA)',
       '020355':'TELIMBELA','040254':'SAN VICENTE DE PUSIR','170355':'MANUEL CORNEJO ASTORGA (TANDAPI)','080550':'SAN LORENZO','091451':'VALLE DE LA VIRGEN','100251':'IMBAYA (SAN LUIS DE COBUENDO)','110957':'SAN ANTONIO','130154':'PUEBLO NUEVO','180166':'TOTORAS','190155':'SABANILLA',
       '020450':'ECHEANDIA','040153':'JULIO ANDRADE (OREJUELA)','170356':'TAMBILLO','080551':'ALTO TAMBO (CAB EN GUADUAL)','091452':'SABANILLA','100252':'SAN FCO. DE NATABUELA','110958':'CASANGA','130155':'RIOCHICO (RIO CHICO)','180167':'CUNCHIBAMBA','190156':'TIMBARA',
       '020550':'SAN MIGUEL','040154':'MALDONADO','091054':'ROBERTO ASTUDILLO (CAB. EN CRUCE DE VENECIA)','080552':'ANCON (PICHANGAL) (CAB. EN PALMA REAL)','091650':'SAMBORONDON','100351':'APUELA','110959':'YAMANA','130156':'SAN PLACIDO','180751':'BENITEZ (PACHANLICA)','190158':'SAN CARLOS DE LAS MINAS',
       '020551':'BALSAPAMBA','040255':'SAN RAFAEL','100153':'CAROLINA','140157':'SEVILLA DON BOSCO','060752':'ILAPO','100352':'GARCIA MORENO (LLURIMAGUA)','111050':'ALAMOR','130157':'CHIRIJOS','180168':'UNAMUNCHO','190252':'EL CHORRO',
       '020552':'BILOVAN','040350':'EL ANGEL','170174':'PERUCHO','140158':'SINAI','091951':'GRAL. VERNAZA (DOS ESTEROS)','100353':'IMANTAG','111051':'CIANO','130152':'ALHAJUELA (BAJO GRANDE)','180451':'PINGUILI','190254':'LA CHONTA',
       '020553':'REGULO DE MORA','030253':'TURUPAMBA','150751':'COSANGA','060253':'GUASUNTOS','091952':'LA VICTORIA (ÑAUZA)','100354':'PEÑAHERRERA','111052':'EL ARENAL','130153':'CRUCITA','180550':'PATATE','190256':'PUCAPAMBA',
       '020554':'SAN PABLO (SAN PABLO DE ATENAS)','030254':'JERUSALEN','150752':'CUYUJA','060254':'HUIGRA','091953':'JUNQUILLAL','100355':'PLAZA GUTIERREZ (CALVARIO)','111053':'EL LIMO (MARIANA DE JESUS)','130353':'CONVENTO','220157':'INES ARANGO (CAB. EN WESTERN)','200150':'PUERTO BAQUERIZO MORENO',
       '020555':'SANTIAGO','030350':'CAÑAR','100156':'SALINAS','060255':'MULTITUD','092050':'SAN JACINTO DE YAGUACHI','070652':'LA IBERIA','111054':'MERCADILLO','130354':'CHIBUNGA','220158':'LA BELLEZA','200151':'EL PROGRESO',
       '020556':'SAN VICENTE','030351':'CHONTAMARCA','110154':'GUALEL','080561':'TULULBI (CAB. EN RICAURTE)','092053':'GRAL. PEDRO J. MONTERO (BOLICHE)','070653':'TENDALES (CAB. EN PUERTO TENDALES)','071352':'ARCAPAMBA','130250':'CALCETA','220159':'NUEVO PARAISO (CAB. EN UNION )','200152':'ISLA SANTA MARIA (FLOREANA) (CAB. EN  PTO. VELASCO IBARRA)',
       '020650':'CALUMA','030352':'CHOROCOPTE','110155':'JIMBILLA','080562':'URBINA','092055':'YAGUACHI VIEJO (CONE)','070654':'RIO BONITO','071353':'GUANAZAN','130251':'MEMBRILLO','220160':'SAN JOSE DE GUAYUSA','200250':'PUERTO VILLAMIL',
       '010167':'SININCAY','030353':'GENERAL MORALES (SOCARTE)','110456':'SABANILLA','080650':'ATACAMES','092056':'VIRGEN DE FATIMA','070750':'HUAQUILLAS','071354':'GUIZHAGUIÑA','130252':'QUIROGA','220161':'SAN LUIS DE ARMENIA','200251':'TOMAS DE BERLANGA (SANTO TOMAS)',
       '020750':'LAS NAVES','030354':'GUALLETURO','110652':'JIMBURA','080651':'LA UNION','092150':'GENERAL VILLAMIL (PLAYAS)','070850':'MARCABELI','071355':'HUERTAS','130350':'CHONE','220250':'NUEVO ROCAFUERTE','200350':'PUERTO AYORA',
       '030150':'AZOGUES','030355':'HONORATO VASQUEZ (TAMBO VIEJO)','111055':'VICENTINO','080652':'SUA (CAB. EN LA BOCANA)','060755':'SAN GERARDO DE PACAICAGUAN','100451':'DOCTOR MIGUEL EGAS CABEZAS (PEGUCHE)','111154':'MANU','140256':'NUEVA TARQUI','180251':'LLIGUA','200351':'BELLAVISTA',
       '030156':'PINDILIG','030356':'INGAPIRCA','111355':'BOLASPAMBA','080653':'TONCHIGUE','060756':'SAN ISIDRO DE PATULU','100452':'EUGENIO ESPEJO (CALPAQUI)','111155':'SAN ANTONIO DE QUMBE (CUMBE)','140257':'SAN MIGUEL DE CUYES','180252':'RIO NEGRO','200352':'SANTA ROSA',
       '030157':'RIVERA','030357':'JUNCAL','140252':'BERMEJOS','080654':'TONSUPA','060954':'SAN ANTONIO DE BAYUSHIG','100453':'GONZALEZ SUAREZ','111156':'SAN PABLO DE TENTA','140258':'EL IDEAL','180253':'RIO VERDE','210150':'NUEVA LOJA',
       '030158':'SAN MIGUEL','030358':'SAN ANTONIO','140253':'BOMBOIZA','080750':'RIOVERDE','060955':'LA CANDELARIA','100454':'PATAQUI','111157':'SAN SEBASTIAN DE YULUC','140350':'GRAL. LEONIDAS PLAZA GUTIERREZ','180254':'ULBA','210152':'DURENO']

    dpaParroquias.each {dpapr ->
      String codigo = dpapr.key
      String nombre = dpapr.value
      String codigoCanton = codigo.substring(0,4)
      def canton = DPA.findByCodigoAndNivel(codigoCanton,'CANTON')
      def laparroquia = DPA.findByCodigo(codigo)
      if(canton && !laparroquia) {
        def parroquia = new DPA()
        parroquia.nivel = 'PARROQUIA'
        parroquia.codigo = codigo
        parroquia.nombre = nombre
        parroquia.superior = canton
        parroquia.version = 1
        parroquia.fechaCreacion = new Date()
        parroquia.fechaActualizacion = new Date()
        parroquia.save(flush:true)
      }
    }

  }

  def updateIndice() {
    indice++
    save(flush:true)
  }

  String indice() {
    preIndice + ('00000000' + indice)[-4..-1]
  }

  static def provincias() {
    return findAllByNivel('PROVINCIA',[sort:'nombre'])
  }

  static def cantones(DPA provincia) {
    return findAllByNivelAndSuperior('CANTON',get(provincia.id),[sort:'nombre'])
  }

  static def parroquias(DPA canton) {
    return findAllByNivelAndSuperior('PARROQUIA',get(canton.id),[sort:'nombre'])
  }

  String toString() { nombre }

  String toString2() { codigo + ', ' + nombre }

  static entityName = "DPA"

  static entityNamePlural = "DPA"

}
