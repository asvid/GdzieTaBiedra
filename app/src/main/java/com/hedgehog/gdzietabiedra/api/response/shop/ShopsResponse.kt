package com.hedgehog.gdzietabiedra.api.response.shop

import com.squareup.moshi.Json

data class ShopsResponse(

    @Json(name = "result")
    val result: Int? = null,

    @Json(name = "cdn_base")
    val cdnBase: String? = null,

    @Json(name = "shops")
    val shops: List<ShopsItem>? = null,

    @Json(name = "error")
    val error: String? = null,

    @Json(name = "timestamp")
    val timestamp: Int? = null
)