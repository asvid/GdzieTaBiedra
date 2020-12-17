package com.hedgehog.gdzietabiedra.appservice.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import timber.log.Timber

class LocationProviderChangedReceiver(private val locationEnabledListener: (Boolean) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.let { act ->
            Timber.d("broadcast receceiving $act")
            if (act.matches("android.location.PROVIDERS_CHANGED".toRegex())) {
                val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                Timber.i("Location Providers changed, is location Enabled: ${locationManager.isLocationEnabled}")
                locationEnabledListener.invoke(locationManager.isLocationEnabled)
            }
        }
    }
}
