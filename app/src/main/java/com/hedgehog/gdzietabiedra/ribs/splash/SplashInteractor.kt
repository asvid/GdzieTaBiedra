package com.hedgehog.gdzietabiedra.ribs.splash

import android.Manifest.*
import com.hedgehog.gdzietabiedra.utils.analytics.Analytics
import com.hedgehog.gdzietabiedra.utils.analytics.EventType
import com.hedgehog.gdzietabiedra.utils.async
import com.hedgehog.gdzietabiedra.utils.subscribeWithErrorLogging
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SplashScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SplashInteractor : BaseInteractor<SplashInteractor.SplashPresenter, SplashRouter>() {

  @Inject
  lateinit var presenter: SplashPresenter
  @Inject
  lateinit var dexter: DexterBuilder.Permission
  @Inject
  lateinit var splashListener: SplashListener
  @Inject
  lateinit var analytics: Analytics

  override fun getRibName(): String = "Splash Screen"

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    permissionCheck()

    analytics.log(EventType.Screen(getRibName()))
  }

  private fun permissionCheck() {
    dexter
        .withPermissions(
            permission.ACCESS_FINE_LOCATION,
            permission.WRITE_EXTERNAL_STORAGE,
            permission.READ_EXTERNAL_STORAGE)
        .withListener(object : MultiplePermissionsListener {
          override fun onPermissionsChecked(report: MultiplePermissionsReport) {
            Timber.d("permissions checked: ${report.areAllPermissionsGranted()}")
            if (report.areAllPermissionsGranted()) {
              splashListener.allPermissionsGranted()
            } else {
              presenter.showPermissionButton()
            }
          }

          override fun onPermissionRationaleShouldBeShown(
              permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
            Timber.d("onPermissionRationaleShouldBeShown: $permissions")
            token?.continuePermissionRequest()
          }
        })
        .check()

    presenter.buttonClick()
        .async()
        .subscribeWithErrorLogging {
          Timber.d("button clicked")
          permissionCheck()
        }
        .addToDisposables()
  }

  interface SplashPresenter {
    fun buttonClick(): Observable<Any>
    fun showPermissionButton()
  }
}
