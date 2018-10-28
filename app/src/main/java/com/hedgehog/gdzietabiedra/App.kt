package com.hedgehog.gdzietabiedra

import android.app.Activity
import android.app.Application
import android.app.Service
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.hedgehog.gdzietabiedra.di.components.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.fabric.sdk.android.Fabric
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

  init {
    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this)

    Timber.plant(DebugTree())
  }

  override fun onCreate() {
    super.onCreate()

    initFabric()
    initLeakCanary()
  }

  private fun initFabric() {
    val crashlyticsKit = Crashlytics.Builder()
        .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
        .build()

    Fabric.with(this, crashlyticsKit)
  }

  private fun initLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    LeakCanary.install(this)
  }
}