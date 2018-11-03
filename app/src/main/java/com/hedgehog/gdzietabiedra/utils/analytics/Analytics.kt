package com.hedgehog.gdzietabiedra.utils.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class Analytics @Inject constructor(val context: Context) {

    private val analytics = FirebaseAnalytics.getInstance(context)

    fun log(event: EventType) {
        analytics.logEvent(event.name(), event.getBundle())
    }
}
