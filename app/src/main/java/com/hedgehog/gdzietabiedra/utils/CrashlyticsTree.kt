package com.hedgehog.gdzietabiedra.utils

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

  override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
    if (priority == Log.ERROR || priority == Log.DEBUG) {
      FirebaseCrashlytics.getInstance().log("${if (priority == 6) "E" else "D"}/$tag: $message")
      if (throwable != null) {
        FirebaseCrashlytics.getInstance().recordException(throwable)
      }
    } else return
  }
}