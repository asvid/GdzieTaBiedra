package com.hedgehog.gdzietabiedra.data.db.shops

import com.github.asvid.biedra.domain.*

internal fun ShopRoomEntity.toDomainModel(): Shop {
    val entity = this
    return Shop(
            this.id,
            address {
                cityName = city.orEmpty()
                streetName = street.orEmpty()
                streetNumber = entity.streetNumber.orEmpty()
            },
            this.name,
            this.distance,
            location {
                lat = entity.latitude ?: 0.0
                lng = entity.longitude ?: 0.0
            },
            openHours {
                weekDay = hours
                saturday = hoursSaturday
                sunday = hoursSunday
            },
            features {
                relax = entity.relax
                bakery = entity.bakery
                relax = entity.relax
                atm = entity.atm
                cardPayment = entity.cardPayment
                isTaxFree = entity.isTaxFree
                isEuro = entity.isEuro
                isNew = entity.isNew
            }
    )
}