package com.hedgehog.gdzietabiedra.ribs.bottomnav.settings

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SettingsScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SettingsInteractor : Interactor<SettingsInteractor.SettingsPresenter, SettingsRouter>() {

  @Inject
  lateinit var presenter: SettingsPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    presenter.rangeChanges()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(
            onNext = {
              Timber.d("range changed: $it")
              presenter.setRangeTest("${50 + it * 1950 / 100} m")
            })
    presenter.rangeSet().subscribeBy(
        onNext = {
          Timber.d("range set: $it")
        })
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface SettingsPresenter {
    fun rangeChanges(): Observable<Int>
    fun rangeSet(): Observable<Int>
    fun setRangeTest(range: String)
  }
}
