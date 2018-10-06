package com.hedgehog.gdzietabiedra.data.repository.shops

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.api.response.shop.ShopsItem
import com.hedgehog.gdzietabiedra.domain.Shop

internal fun ShopEntity.toDomainModel(): Shop {
  return Shop(this.id, generateAddress(this), this.distance,
      Position(this.latitude, this.longitude),
      this.hours)
}

internal fun generateAddress(shopEntity: ShopEntity): String {
  return "${shopEntity.city}, ${shopEntity.street} ${shopEntity.streetNumber}"
}

internal fun Collection<ShopsItem>.toRealmEntity(): Collection<ShopEntity> {
  return this.map { it.toRealmEntity() }
}

internal fun ShopsItem.toRealmEntity(): ShopEntity {
  val output = ShopEntity()
  output.id = this.id!!
  output.city = this.city!!
  output.street = this.street ?: ""
  output.streetNumber = this.streetNumber.toString()
  output.shopNumber = this.shopNumber?.toInt()
  output.latitude = this.latitude?.toDouble() ?: 0.0
  output.longitude = this.longitude?.toDouble() ?: 0.0
  output.hours = this.hours ?: ""
  output.hoursFriday = this.hoursFriday
  output.hoursSaturday = this.hoursSaturday
  output.hoursSunday = this.hoursSunday
  output.distance = this.distance?.toDouble()

  output.atm = this.atm == "1"
  output.bakery = this.bakery == "1"
  output.relax = this.relax == "1"
  output.cardPayment = this.cardPayment == "1"
  output.isTaxFree = this.isTaxFree == "1"
  output.isEuro = this.isEuro == "1"
  output.isNew = this.jsonMemberNew == "1"
  output.special = this.special?.toInt()
  output.sublease = this.sublease
  return output
}
