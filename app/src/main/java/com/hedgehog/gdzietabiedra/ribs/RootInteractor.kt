package com.hedgehog.gdzietabiedra.ribs

import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

  @Inject
  lateinit var presenter: RootPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    router.attachBottomNav()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
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
