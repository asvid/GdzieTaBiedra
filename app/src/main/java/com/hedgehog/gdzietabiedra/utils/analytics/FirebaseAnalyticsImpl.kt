package com.hedgehog.gdzietabiedra.utils.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class FirebaseAnalyticsImpl(val context: Context) : Analytics {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val analytics = Firebase.analytics

    override fun log(event: EventType) {
        analytics.logEvent(event.name(), event.getBundle())
    }
}
