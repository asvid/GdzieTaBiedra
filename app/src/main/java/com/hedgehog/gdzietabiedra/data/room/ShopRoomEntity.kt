package com.hedgehog.gdzietabiedra.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopRoomEntity(
    @PrimaryKey
    var id: String = "",
    var shopNumber: Int? = null,
    var city: String,
    var street: String = "",
    var streetNumber: String? = null,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var hours: String,
    var hoursFriday: String? = null,
    var hoursSaturday: String? = null,
    var hoursSunday: String? = null,
    var distance: Double? = null,
    var bakery: Boolean = false,
    var relax: Boolean = false,
    var atm: Boolean = false,
    var cardPayment: Boolean = false,
    var isTaxFree: Boolean = false,
    var isEuro: Boolean = false,
    var isNew: Boolean = false,
    var special: Int? = null,
    var sublease: String? = null
)