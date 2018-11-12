package com.hedgehog.gdzietabiedra.ribs.bottomnav

import com.hedgehog.gdzietabiedra.utils.subscribeWithErrorLogging
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

/**
 * Coordinates Business Logic for [BottomNavBuilder.BottomNavScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class BottomNavInteractor : BaseInteractor<BottomNavInteractor.BottomNavPresenter, BottomNavRouter>() {

  @Inject
  lateinit var presenter: BottomNavPresenter
  @Inject
  lateinit var listener: Listener

  override fun getRibName(): String = "Bottom Navigation"

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.menuEvents()
        .subscribeWithErrorLogging { event ->
          Timber.d("nav event: $event")
          when (event) {
            MenuItem.LIST -> listener.shopsListSelected()
            MenuItem.MAP -> listener.mapSelected()
            MenuItem.SETTINGS -> listener.settingsSelected()

          }
        }.addToDisposables()

    presenter.setActiveMenuItem(MenuItem.LIST)
  }

  interface BottomNavPresenter {
    fun menuEvents(): Observable<MenuItem>
    fun setActiveMenuItem(menuItem: MenuItem)
  }

  interface Listener {
    fun shopsListSelected()
    fun mapSelected()
    fun settingsSelected()
  }
}
