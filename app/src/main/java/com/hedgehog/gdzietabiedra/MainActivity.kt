package com.hedgehog.gdzietabiedra

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.api.BiedraService
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.ribs.RootBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import dagger.android.AndroidInjection
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : RibActivity() {

  lateinit var shopsRepository: ShopsRepository
    @Inject set
  lateinit var biedraApi: BiedraService
    @Inject set

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    permissionCheck()
  }

  fun permissionCheck() {
//    TODO this should be available from Dagger, but no time for it now :)
    Dexter.withActivity(this)
        .withPermission(ACCESS_FINE_LOCATION)
        .withListener(object : PermissionListener {
          override fun onPermissionGranted(response: PermissionGrantedResponse?) {

          }

          override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?,
              token: PermissionToken?) {
          }

          override fun onPermissionDenied(response: PermissionDeniedResponse?) {
          }

        })
        .check()
  }

  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
      RootBuilder(object : RootBuilder.ParentComponent {
        override fun shopsRepository(): ShopsRepository = shopsRepository
      })
          .build(parentViewGroup)

  override fun onResume() {
    super.onResume()


    biedraApi.listRepos(51.802742F, 19.514333F)
        .subscribeOn(Schedulers.newThread())
        .subscribeBy(
            onSuccess = {
              Timber.d("biedras: $it")
            },
            onError = {
              Timber.e("biedras error: $it")
            })
  }
}
