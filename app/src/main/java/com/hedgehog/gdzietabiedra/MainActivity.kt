package com.hedgehog.gdzietabiedra

import android.os.Bundle
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.di.components.AppComponent
import com.hedgehog.gdzietabiedra.ribs.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : RibActivity() {

    lateinit var shopsRepository: ShopsRepository
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
            RootBuilder(object : RootBuilder.ParentComponent {
                override fun shopsRepository(): ShopsRepository = shopsRepository
            })
                    .build(parentViewGroup)
}
