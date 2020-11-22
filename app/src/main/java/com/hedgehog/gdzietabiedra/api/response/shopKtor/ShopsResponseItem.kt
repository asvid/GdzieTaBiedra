package com.hedgehog.gdzietabiedra.api.response.shopKtor

import kotlinx.serialization.Serializable

@Serializable
data class ShopsResponseItem(

        val import_query: String? = null,

        val distance: String? = null,

        val city: String? = null,

        val latitude: String? = null,

        val is_tax_free: String? = null,

        val street: String? = null,

        val hours_sunday: String? = null,

        val import_update: String? = null,

        val id: String? = null,

        val atm: String? = null,

        val longitude: String? = null,

        val shop_dc_id: String? = null,

        val card_payment: String? = null,

        val new: String? = null,

        val hours: String? = null,

        val bakery: String? = null,

        val relax: String? = null,

        val sublease: String? = null,

        val created: String? = null,

        val city_slug: String? = null,

        val special: String? = null,

        val shop_province_teryt: String? = null,

        val hours_friday: String? = null,

        val is_euro: String? = null,

        val name: String? = null,

        val street_number: String? = null,

        val shop_number: String? = null,

        val shop_city_id: String? = null,

        val hours_saturday: String? = null
)