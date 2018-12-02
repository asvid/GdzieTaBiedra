package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener.ShopListEvent.ShopSelected

interface ShopListListener {
  fun onShopSelected(shopSelected: ShopSelected)

  sealed class ShopListEvent {
    class ShopSelected(val shop: Shop) : ShopListEvent()
  }

}
