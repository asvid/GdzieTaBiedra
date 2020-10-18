package com.hedgehog.gdzietabiedra

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.hedgehog.gdzietabiedra.utils.CrashlyticsTree
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Application class, no rocket science here
 * */
class App : Application() {

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
    initJoda()
  }

  private fun initJoda() {
    JodaTimeAndroid.init(this)
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