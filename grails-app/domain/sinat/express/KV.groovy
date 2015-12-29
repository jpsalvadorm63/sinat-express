package sinat.express

class KV {
    String key
    String value

    static constraints = {
        key nullable: false, maxSize: 240
        value nullable: true, maxSize: 240
    }

    static mapping = {
        table name: 'kv'
        cache true


    }

    static String valueOf(String key) {
        KV.findByKey(key)?.value
    }

    static String valueFor(String key, String value) {
        def mykv = KV.findByKey(key)
        if(mykv == null) {
            new KV(key: key, value: value).save(flush:true)
        } else {
            mykv.value = value
        }
    }

    static boolean isLocUE() {
        def loc = KV.findByKey('loc')
        return (loc != null && loc.value == 'UE'.encodeAsSHA1())
    }

    static void setLocUE() {
        if(!sinat.express.KV.findByKey('loc')) {
            new sinat.express.KV(key:'loc', value:'UE'.encodeAsSHA1()).save(flush:true)
        }
    }

}
