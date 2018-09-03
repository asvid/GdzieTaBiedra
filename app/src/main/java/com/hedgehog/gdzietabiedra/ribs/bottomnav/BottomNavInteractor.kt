package com.hedgehog.gdzietabiedra.ribs.bottomnav

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import timber.log.Timber
import javax.inject.Inject

/**
 * Coordinates Business Logic for [BottomNavScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class BottomNavInteractor : Interactor<BottomNavInteractor.BottomNavPresenter, BottomNavRouter>() {

  @Inject
  lateinit var presenter: BottomNavPresenter
  @Inject
  lateinit var listener: Listener

  private val disposables = CompositeDisposable()

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    disposables.add(presenter.menuEvents()
        .subscribe({ event ->
          Timber.d("nav event: $event")
          when (event) {
            MenuEvent.LIST -> listener.shopsListSelected()
            MenuEvent.MAP -> listener.mapSelected()
            MenuEvent.SETTINGS -> listener.settingsSelected()

          }
        }, { OnErrorNotImplementedException(it) }))
  }

  override fun willResignActive() {
    super.willResignActive()
    disposables.clear()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface BottomNavPresenter {
    fun menuEvents(): Observable<MenuEvent>
  }

  interface Listener {

    fun shopsListSelected()
    fun mapSelected()
    fun settingsSelected()
  }
}
