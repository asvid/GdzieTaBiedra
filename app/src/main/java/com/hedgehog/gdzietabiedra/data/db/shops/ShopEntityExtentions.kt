package com.hedgehog.gdzietabiedra.data.db.shops

import com.github.asvid.biedra.domain.shops.Shop

internal fun ShopRoomEntity.toDomainModel(): Shop {
    val entity = this
    return Shop(
            this.id,
            com.github.asvid.biedra.domain.shops.address {
                cityName = city.orEmpty()
                streetName = street.orEmpty()
                streetNumber = entity.streetNumber.orEmpty()
            },
            this.name,
            this.distance,
            com.github.asvid.biedra.domain.shops.location {
                lat = entity.latitude ?: 0.0
                lng = entity.longitude ?: 0.0
            },
            com.github.asvid.biedra.domain.shops.openHours {
                weekDay = hours
                saturday = hoursSaturday
                sunday = hoursSunday
            },
            com.github.asvid.biedra.domain.shops.features {
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