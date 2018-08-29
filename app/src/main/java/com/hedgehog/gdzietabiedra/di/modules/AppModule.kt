package com.hedgehog.gdzietabiedra.di.modules

import android.content.Context
import com.hedgehog.gdzietabiedra.App
import com.hedgehog.gdzietabiedra.di.components.MainActivityComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [MainActivityComponent::class])
class AppModule {

  @Provides
  internal fun context(application: App): Context {
    return application.applicationContext
  }
}