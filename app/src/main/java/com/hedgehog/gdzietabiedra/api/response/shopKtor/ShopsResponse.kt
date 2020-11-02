package com.hedgehog.gdzietabiedra.api.response.shopKtor

import kotlinx.serialization.Serializable

@Serializable
data class ShopsResponse(

        val result: Int? = null,

        val cdn_base: String? = null,

        val shops: List<ShopsResponseItem>? = null,

        val error: String? = null,

        val timestamp: Int? = null
)