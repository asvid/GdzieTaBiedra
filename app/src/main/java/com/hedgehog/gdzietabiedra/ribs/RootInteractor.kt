package com.hedgehog.gdzietabiedra.ribs

import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavInteractor
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapEvent
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener.ShopListEvent
import com.hedgehog.gdzietabiedra.utils.async
import com.hedgehog.gdzietabiedra.utils.subscribeWithErrorLogging
import com.jakewharton.rxrelay2.PublishRelay
import com.karumi.dexter.MultiplePermissionsReport
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : BaseInteractor<RootInteractor.RootPresenter, RootRouter>() {

  @Inject
  lateinit var presenter: RootPresenter
  @Inject
  lateinit var shopListRelay: PublishRelay<ShopListEvent>
  @Inject
  lateinit var mapRelay: PublishRelay<MapEvent>
  @Inject
  lateinit var permissionSubject: BehaviorSubject<MultiplePermissionsReport>

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
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

    permissionSubject
        .async()
        .subscribeWithErrorLogging {
          if (it.deniedPermissionResponses.isEmpty()) {
            router.attachBottomNav()
            router.attachMapHidden()
          } else {
            router.
          }
        }
        .addToDisposables()
  }

  interface RootPresenter

  inner class NavigationListener : BottomNavInteractor.Listener {

    override fun shopsListSelected() {
      router.detachMap()
      router.detachSettings()

      router.attachShopslist()
    }

    override fun mapSelected() {
      router.detachShopslist()
      router.detachSettings()

      router.attachMap()
    }

    override fun settingsSelected() {
      router.detachMap()
      router.detachShopslist()

      router.attachSettings()
    }

  }
}
