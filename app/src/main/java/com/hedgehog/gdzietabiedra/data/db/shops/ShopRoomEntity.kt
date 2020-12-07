package com.hedgehog.gdzietabiedra.data.db.shops

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val TABLE_NAME = "shoproomentity"
internal const val ID = "id"

@Entity(tableName = TABLE_NAME)
data class ShopRoomEntity(
        @PrimaryKey
        @ColumnInfo(name = ID)
        var id: String = "",
        var shopNumber: Int? = null,
        var city: String? = null,
        var street: String? = null,
        var streetNumber: String? = null,
        var latitude: Double? = null,
        var longitude: Double? = null,
        var hours: String? = null,
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
        var sublease: String? = null,
        val name: String? = null,
        val citySlug: String? = null,
        val provinceId: String? = null
)