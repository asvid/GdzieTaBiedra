package com.hedgehog.gdzietabiedra.data.repository.shops

import com.github.asvid.biedra.domain.Shop
import com.github.asvid.biedra.domain.address
import com.github.asvid.biedra.domain.openHours
import com.github.asvid.biedra.domain.position

internal fun ShopRoomEntity.toDomainModel(): Shop {
    val entity = this
    return Shop(
            this.id,
            address {
                cityName = city.orEmpty()
                streetName = street.orEmpty()
                streetNumber = entity.streetNumber.orEmpty()
            },
            this.distance,
            position {
                lat = entity.latitude ?: 0.0
                lng = entity.longitude ?: 0.0
            },
            openHours {
                weekDay = hours
                saturday = hoursSaturday
                sunday = hoursSunday
            })
}