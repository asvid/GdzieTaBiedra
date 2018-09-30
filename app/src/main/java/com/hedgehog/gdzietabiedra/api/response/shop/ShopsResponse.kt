package com.hedgehog.gdzietabiedra.api.response.shop

import com.squareup.moshi.Json

data class ShopsResponse(

    @field:Json(name = "result")
    val result: Int? = null,

    @field:Json(name = "cdn_base")
    val cdnBase: String? = null,

    @field:Json(name = "shops")
    val shops: List<ShopsItem>? = null,

    @field:Json(name = "error")
    val error: String? = null,

    @field:Json(name = "timestamp")
    val timestamp: Int? = null
)