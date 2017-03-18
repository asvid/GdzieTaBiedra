package com.hedgehog.gdzietabiedra.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle

/**
 * Created by Adam on 2015-08-06.
 */
class GpsLocationReceiver : BroadcastReceiver() {
    private var locationManager: LocationManager? = null
    private var mWifi: NetworkInfo? = null
    private var connManager: ConnectivityManager? = null
    private val locationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            try {
                gotLocation(location)
            } catch (e: Exception) {
            }

        }

        override fun onStatusChanged(provider: String, status: Int,
                                     extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
            locationManager = context
                    .getSystemService(Context.LOCATION_SERVICE) as LocationManager
            connManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            mWifi = connManager!!.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (locationManager!!
                    .isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
                locationManager!!.requestLocationUpdates(
                        LocationManager.PASSIVE_PROVIDER, 5000, 5f,
                        locationListener)
                val gotLoc = locationManager!!
                        .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                gotLocation(gotLoc)
            }
        }
    }

    private fun gotLocation(gotLoc: Location?) {
        if (mWifi != null) {
            if (mWifi!!.isConnected && gotLoc != null) {
                Database.populate(gotLoc.latitude, gotLoc.longitude)
            }
        }
    }
}
