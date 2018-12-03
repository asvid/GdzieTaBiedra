package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import com.github.asvid.biedra.domain.Shop

sealed class MapEvent {
  class ShopSelected(val shop: Shop) : MapEvent()
}
