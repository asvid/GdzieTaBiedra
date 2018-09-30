package com.hedgehog.gdzietabiedra

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.os.Bundle
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.api.BiedraService
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.ribs.RootBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import dagger.android.AndroidInjection
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
        .withPermissions(ACCESS_FINE_LOCATION, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
        .withListener(object : MultiplePermissionsListener {
          override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

          }

          override fun onPermissionRationaleShouldBeShown(
              permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
          }
        })
        .check()
  }

  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
      RootBuilder(object : RootBuilder.ParentComponent {
        override fun shopsRepository(): ShopsRepository = shopsRepository
      })
          .build(parentViewGroup)
}
