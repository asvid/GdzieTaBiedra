package com.hedgehog.gdzietabiedra.utils.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalytics(val context: Context) : Analytics {

  private val analytics = FirebaseAnalytics.getInstance(context)

  override fun log(event: EventType) {
    analytics.logEvent(event.name(), event.getBundle())
  }
}
