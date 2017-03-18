package com.hedgehog.gdzietabiedra.utils

import com.hedgehog.gdzietabiedra.pojo.Shops.Shop
import kotlin.properties.Delegates

/**
 * Created by Adam on 2015-06-29.
 */
class MessageEvent {
    var message: String
    var shop: Shop by Delegates.notNull()

    enum class types {
        DATABASE_UPDATE, ITEM_CLICK
    }

    var type: types

    constructor(message: String, shop: Shop, type: types) {
        this.message = message
        this.shop = shop
        this.type = type
    }

    constructor(message: String, type: types) {
        this.message = message
        this.type = type
    }
}
