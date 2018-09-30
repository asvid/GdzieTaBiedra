package com.hedgehog.gdzietabiedra.api.response.shop

import com.squareup.moshi.Json

data class ShopsItem(

    @field:Json(name = "import_query")
    val importQuery: String? = null,

    @field:Json(name = "distance")
    val distance: String? = null,

    @field:Json(name = "city")
    val city: String? = null,

    @field:Json(name = "latitude")
    val latitude: String? = null,

    @field:Json(name = "is_tax_free")
    val isTaxFree: String? = null,

    @field:Json(name = "street")
    val street: String? = null,

    @field:Json(name = "hours_sunday")
    val hoursSunday: String? = null,

    @field:Json(name = "import_update")
    val importUpdate: String? = null,

    @field:Json(name = "id")
    val id: String? = null,

    @field:Json(name = "atm")
    val atm: String? = null,

    @field:Json(name = "longitude")
    val longitude: String? = null,

    @field:Json(name = "shop_dc_id")
    val shopDcId: String? = null,

    @field:Json(name = "card_payment")
    val cardPayment: String? = null,

    @field:Json(name = "new")
    val jsonMemberNew: String? = null,

    @field:Json(name = "hours")
    val hours: String? = null,

    @field:Json(name = "bakery")
    val bakery: String? = null,

    @field:Json(name = "relax")
    val relax: String? = null,

    @field:Json(name = "sublease")
    val sublease: String? = null,

    @field:Json(name = "created")
    val created: String? = null,

    @field:Json(name = "city_slug")
    val citySlug: String? = null,

    @field:Json(name = "special")
    val special: String? = null,

    @field:Json(name = "shop_province_teryt")
    val shopProvinceTeryt: String? = null,

    @field:Json(name = "hours_friday")
    val hoursFriday: String? = null,

    @field:Json(name = "is_euro")
    val isEuro: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "street_number")
    val streetNumber: Any? = null,

    @field:Json(name = "shop_number")
    val shopNumber: String? = null,

    @field:Json(name = "shop_city_id")
    val shopCityId: String? = null,

    @field:Json(name = "hours_saturday")
    val hoursSaturday: String? = null
)