package com.hedgehog.gdzietabiedra

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

  init {
    Timber.plant(DebugTree())
  }
}