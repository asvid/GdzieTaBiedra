package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.domain.Shop

data class ShopMarker(
    val position: Position,
    val shop: Shop
)