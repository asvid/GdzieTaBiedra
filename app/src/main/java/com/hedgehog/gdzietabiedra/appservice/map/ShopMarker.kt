package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.domain.Shop

data class ShopMarker private constructor(
    val position: Position,
    val shop: Shop
) {
  companion object {
    fun create(shop: Shop) = ShopMarker(shop.location, shop)
  }
}