package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import com.hedgehog.gdzietabiedra.domain.Shop

sealed class MapEvent {
  class ShopSelected(val shop: Shop) : MapEvent()
}
