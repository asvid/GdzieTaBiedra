package com.hedgehog.gdzietabiedra

import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.ribs.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import javax.inject.Inject

class MainActivity : RibActivity() {

  lateinit var shopsRepository: ShopsRepository
    @Inject set

  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
      RootBuilder((application as App).appComponent)
          .build(parentViewGroup)
}
