package com.hedgehog.gdzietabiedra.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager

/**
 * Created by Adam on 2015-06-18.
 */
class WifiReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val lastLocation = (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).getLastKnownLocation(
                LocationManager.PASSIVE_PROVIDER)
        val info = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
        if (info != null) {
            if (info.isConnected && lastLocation != null) {
                Database
                        .populate(lastLocation.latitude, lastLocation.longitude)
            }
        }
    }
}