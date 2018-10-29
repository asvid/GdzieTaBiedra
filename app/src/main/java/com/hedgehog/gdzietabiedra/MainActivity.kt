package com.hedgehog.gdzietabiedra

import android.os.Bundle
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.appservice.LocationService
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.ribs.RootBuilder
import com.karumi.dexter.Dexter
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : RibActivity() {

  @Inject
  lateinit var locationService: LocationService
  @Inject
  lateinit var shopService: ShopService

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
  }


  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
      RootBuilder(object : RootBuilder.ParentComponent {
        override fun locationService() = locationService
        override fun shopServices() = shopService
        override fun dexter() = Dexter.withActivity(this@MainActivity)
      })
          .build(parentViewGroup)
}
