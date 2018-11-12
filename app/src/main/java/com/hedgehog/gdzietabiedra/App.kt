package com.hedgehog.gdzietabiedra

import android.app.Activity
import android.app.Application
import android.app.Service
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.hedgehog.gdzietabiedra.di.components.DaggerAppComponent
import com.hedgehog.gdzietabiedra.utils.CrashlyticsTree
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 * Application class, no rocket science here
 * */
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

  /**
   *  Dagger 2 initialisation
   * */
  init {
    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this)
  }

  /**
   * [Fabric] initialisation
   * [LeakCanary] initialisation
   * [Timber] initialisation
   * */
  override fun onCreate() {
    super.onCreate()

    initFabric()
    initLeakCanary()
    initTimber()
  }

  private fun initTimber() {
    Timber.plant(DebugTree(), CrashlyticsTree())
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