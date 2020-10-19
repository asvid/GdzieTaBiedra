package com.hedgehog.gdzietabiedra.data.repository.shops

import com.github.asvid.biedra.domain.Position
import com.github.asvid.biedra.domain.Shop
import com.github.asvid.biedra.domain.address
import com.github.asvid.biedra.domain.openHours
import com.hedgehog.gdzietabiedra.api.response.shopKtor.ShopsResponseItem
import com.hedgehog.gdzietabiedra.data.room.ShopRoomEntity

internal fun ShopEntity.toDomainModel(): Shop {
  val entity = this
  return Shop(
      this.id,
      address {
        cityName = city
        streetName = street
        streetNumber = entity.streetNumber ?: ""
      },
      this.distance,
      Position(this.latitude, this.longitude),
      openHours {
        weekDay = hours
        saturday = hoursSaturday
        sunday = hoursSunday
      })
}

fun ShopsResponseItem.toRoomEntity(): ShopRoomEntity {
  return ShopRoomEntity(
      id = this.id?:"",
      shopNumber = this.shop_number?.toInt(),
      city = this.city,
      street = this.street,
      streetNumber = this.street_number,
      latitude = this.latitude?.toDouble(),
      longitude = this.longitude?.toDouble(),
      hours = this.hours,
      hoursFriday = this.hours_friday,
      hoursSaturday = this.hours_saturday,
      hoursSunday = this.hours_sunday,
      distance = this.distance?.toDouble(),
      bakery = this.bakery == "1",
      relax = this.relax == "1",
      atm = this.atm == "1",
      cardPayment = this.card_payment == "1",
      isTaxFree = this.is_tax_free == "1",
      isEuro = this.is_euro == "1",
      isNew = this.new == "1",
      special = this.special?.toInt(),
      sublease = this.sublease,
      name = this.name,
      citySlug = this.city_slug,
      provinceId = this.shop_province_teryt,
  )
}