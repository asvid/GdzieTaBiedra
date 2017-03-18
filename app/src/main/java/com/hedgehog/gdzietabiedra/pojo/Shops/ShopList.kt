package com.hedgehog.gdzietabiedra.pojo.Shops

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class ShopList {

    @Expose
    var timestamp: Int? = null
    @Expose
    var result: Int? = null
    @Expose
    var error: String? = null
    @SerializedName("cdn_base")
    @Expose
    var cdnBase: String? = null
    @Expose
    var shops: List<Shop> = ArrayList()

}
