package com.hedgehog.gdzietabiedra.ribs

import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavInteractor
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapEvent
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener.ShopListEvent
import com.hedgehog.gdzietabiedra.ribs.splash.SplashEvent
import com.hedgehog.gdzietabiedra.utils.async
import com.hedgehog.gdzietabiedra.utils.subscribeWithErrorLogging
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import timber.log.Timber
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootBuilder.RootScope].
 *
 * Rooting main views
 * [com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapView] is initialised always
 * but it's shown only after user selects it at [com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavView]
 */
@RibInteractor
class RootInteractor : BaseInteractor<RootInteractor.RootPresenter, RootRouter>() {
  override fun getRibName() = "Root"

  @Inject
  lateinit var presenter: RootPresenter
  @Inject
  lateinit var shopListRelay: PublishRelay<ShopListEvent>
  @Inject
  lateinit var mapRelay: PublishRelay<MapEvent>
  @Inject
  lateinit var splashRelay: BehaviorRelay<SplashEvent>

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    router.attachSplashScreen()
    shopListRelay
        .async()
        .subscribeWithErrorLogging {
          when (it) {
            is ShopListEvent.ShopSelected -> {
              NavigationListener().mapSelected()
              mapRelay.accept(MapEvent.ShopSelected(it.shop))
            }
          }
        }
        .addToDisposables()

    splashRelay
        .async()
        .subscribeWithErrorLogging {
          Timber.d("splash relay: $it")
          router.detachSplashScreen()
          router.attachBottomNav()
          router.attachMapHidden()
        }
        .addToDisposables()
  }

  interface RootPresenter

  inner class NavigationListener : BottomNavInteractor.Listener {
    override fun sundaysSelected() {
      router.detachMap()
      router.detachSettings()
      router.detachShopslist()

      router.attachSundays()
    }

    override fun shopsListSelected() {
      router.detachMap()
      router.detachSettings()
      router.detachSundays()

      router.attachShopslist()
    }

    override fun mapSelected() {
      router.detachShopslist()
      router.detachSettings()
      router.detachSundays()

      router.attachMap()
    }

    override fun settingsSelected() {
      router.detachMap()
      router.detachShopslist()
      router.detachSundays()

      router.attachSettings()
    }
  }
}
