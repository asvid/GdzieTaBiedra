package com.hedgehog.gdzietabiedra.appservice.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import com.hedgehog.gdzietabiedra.utils.isLocationServiceAvailable
import timber.log.Timber

class LocationProviderChangedReceiver(private val locationEnabledListener: (Boolean) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.let { act ->
            Timber.d("broadcast receceiving $act")
            if (act.matches("android.location.PROVIDERS_CHANGED".toRegex())) {
                val isLocationAvailable = context.isLocationServiceAvailable()
                Timber.d("Location Providers changed, is location Enabled: $isLocationAvailable")
                locationEnabledListener.invoke(isLocationAvailable)
            }
        }
    }
}
