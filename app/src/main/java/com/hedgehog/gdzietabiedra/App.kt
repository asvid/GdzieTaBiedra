package com.hedgehog.gdzietabiedra

import android.app.Activity
import android.app.Application
import android.app.Service
import com.hedgehog.gdzietabiedra.di.components.AppComponent
import com.hedgehog.gdzietabiedra.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasServiceInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
  @Inject
  lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

  override fun activityInjector(): AndroidInjector<Activity> {
    return dispatchingAndroidInjector
  }

  override fun serviceInjector(): AndroidInjector<Service> {
    return dispatchingServiceInjector
  }

  val appComponent: AppComponent

  init {
    appComponent = DaggerAppComponent
        .builder()
        .application(this)
        .build()
    appComponent.inject(this)

    Timber.plant(DebugTree())
  }
}