package com.hedgehog.gdzietabiedra

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore
import com.hedgehog.gdzietabiedra.utils.CrashlyticsTree
import com.squareup.leakcanary.LeakCanary
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

  private fun initLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    LeakCanary.install(this)
  }
}