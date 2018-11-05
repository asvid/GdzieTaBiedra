package com.hedgehog.gdzietabiedra.di.modules

import android.content.Context
import com.hedgehog.gdzietabiedra.App
import com.hedgehog.gdzietabiedra.di.components.MainActivityComponent
import com.hedgehog.gdzietabiedra.utils.analytics.Analytics
import com.hedgehog.gdzietabiedra.utils.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
  MainActivityComponent::class
])
class AppModule {

  @Provides
  internal fun context(application: App): Context {
    return application.applicationContext
  }

  @Provides
  @Singleton
  internal fun analytics(context: Context): Analytics {
    return FirebaseAnalytics(context)
  }
}