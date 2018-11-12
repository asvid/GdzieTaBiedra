package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.domain.Shop

/**
 * Map marker for [Shop]
 *
 * @property position - position of shop
 * @property shop - shop connected with map marker
 * */
data class ShopMarker private constructor(
    val position: Position,
    val shop: Shop
) {
  companion object {
    /**
     * Factory for [ShopMarker]
     *
     * @param shop - shop for which map marker should be created
     * */
    fun create(shop: Shop) = ShopMarker(shop.location, shop)
  }
}