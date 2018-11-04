package com.hedgehog.gdzietabiedra.ribs.bottomnav.settings

import com.hedgehog.gdzietabiedra.BuildConfig
import com.hedgehog.gdzietabiedra.utils.analytics.Analytics
import com.hedgehog.gdzietabiedra.utils.analytics.FirebaseAnalytics
import com.hedgehog.gdzietabiedra.utils.analytics.EventType
import com.hedgehog.gdzietabiedra.utils.analytics.EventType.Event.EventName.GIVE_STARS
import com.hedgehog.gdzietabiedra.utils.analytics.EventType.Event.EventName.SENDING_EMAIL
import com.hedgehog.gdzietabiedra.utils.subscribeWithErrorLogging
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SettingsScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SettingsInteractor : BaseInteractor<SettingsInteractor.SettingsPresenter, SettingsRouter>() {

  @Inject
  lateinit var presenter: SettingsPresenter
  @Inject
  lateinit var analytics: Analytics

  override fun getRibName(): String = "Settings"

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    handleNotificationRange()
    handleEmailButton()
    handleStarsButton()
    handleVersionName()

    analytics.log(EventType.Screen(getRibName()))
  }

  private fun handleVersionName() {
    presenter.setVersionName("${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
  }

  private fun handleNotificationRange() {
    presenter.rangeChanges()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWithErrorLogging {
          Timber.d("range changed: $it")
          presenter.setRangeTest("${50 + it * 1950 / 100} m")
        }
        .addToDisposables()

    presenter.rangeSet()
        .subscribeWithErrorLogging {
          Timber.d("range set: $it")
        }
        .addToDisposables()
  }

  private fun handleEmailButton() {
    presenter.emailButtonSubject()
        .subscribeWithErrorLogging {
          analytics.log(EventType.Event(SENDING_EMAIL))
          presenter.openEmail()
        }
        .addToDisposables()
  }

  private fun handleStarsButton() {
    presenter.starsButtonSubject()
        .subscribeWithErrorLogging {
          analytics.log(EventType.Event(GIVE_STARS))
          presenter.openGooglePlay()
        }
        .addToDisposables()
  }

  interface SettingsPresenter {
    fun rangeChanges(): Observable<Int>
    fun rangeSet(): Observable<Int>
    fun setRangeTest(range: String)
    fun emailButtonSubject(): Observable<Any>
    fun starsButtonSubject(): Observable<Any>
    fun openGooglePlay()
    fun openEmail()
    fun setVersionName(versionName: String)
  }
}
