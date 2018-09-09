package com.hedgehog.gdzietabiedra.api

import com.squareup.moshi.Json

data class ShopsItem(

    @Json(name = "import_query")
    val importQuery: String? = null,

    @Json(name = "distance")
    val distance: String? = null,

    @Json(name = "city")
    val city: String? = null,

    @Json(name = "latitude")
    val latitude: String? = null,

    @Json(name = "is_tax_free")
    val isTaxFree: String? = null,

    @Json(name = "street")
    val street: String? = null,

    @Json(name = "hours_sunday")
    val hoursSunday: String? = null,

    @Json(name = "import_update")
    val importUpdate: String? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "atm")
    val atm: String? = null,

    @Json(name = "longitude")
    val longitude: String? = null,

    @Json(name = "shop_dc_id")
    val shopDcId: String? = null,

    @Json(name = "card_payment")
    val cardPayment: String? = null,

    @Json(name = "new")
    val jsonMemberNew: String? = null,

    @Json(name = "hours")
    val hours: String? = null,

    @Json(name = "bakery")
    val bakery: String? = null,

    @Json(name = "relax")
    val relax: String? = null,

    @Json(name = "sublease")
    val sublease: String? = null,

    @Json(name = "created")
    val created: String? = null,

    @Json(name = "city_slug")
    val citySlug: String? = null,

    @Json(name = "special")
    val special: String? = null,

    @Json(name = "shop_province_teryt")
    val shopProvinceTeryt: String? = null,

    @Json(name = "hours_friday")
    val hoursFriday: String? = null,

    @Json(name = "is_euro")
    val isEuro: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "street_number")
    val streetNumber: Any? = null,

    @Json(name = "shop_number")
    val shopNumber: String? = null,

    @Json(name = "shop_city_id")
    val shopCityId: String? = null,

    @Json(name = "hours_saturday")
    val hoursSaturday: String? = null
)