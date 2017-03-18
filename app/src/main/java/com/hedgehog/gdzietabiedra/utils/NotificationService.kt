package com.hedgehog.gdzietabiedra.utils

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import com.hedgehog.gdzietabiedra.Di
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.activity.MainActivity
import io.github.asvid.notti.NottiFactory
import io.github.asvid.notti.actions.ContentAction

class NotificationService : Service(), LocationListener {

    private var mLocationManager: LocationManager? = null
    private var lastLocation: Location? = null

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()

        mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mLocationManager!!
                .requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,
                                        Const.locationTime, Const.distance, this)

        //lastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lastLocation = mLocationManager!!
                .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)

        if (Database.all.size == 0 && lastLocation != null) {
            Database.populate(lastLocation!!.latitude,
                              lastLocation!!.longitude)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onLocationChanged(location: Location) {
        lastLocation = location
        try {
            val closest = Database.listClosest[0]
            if (java.lang.Double.parseDouble(closest.distance) < java.lang.Double.parseDouble(
                    Di.storage.readFromPreferences("radiusValue",
                                                   Const.radiusDefault))) {
                sendNotification(getString(R.string.shop_close),
                                 "${closest.city}, ${closest.street} ${closest
                                         .streetNumber}", 0, closest.id!!)
            }
        } catch (e: Exception) {

        }

    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    private fun sendNotification(title: String, text: String, notificationId: Int,
                                 shopId: String) {

        val notificationIntent = Intent(baseContext,
                                        MainActivity::class.java)
        notificationIntent.putExtra("shopId", shopId)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val intent = PendingIntent
                .getActivity(baseContext, 0, notificationIntent,
                             PendingIntent.FLAG_UPDATE_CURRENT)


        Di.notti
                .show(NottiFactory.get(NottiFactory.TYPE.BIG_TEXT, title, text)
                              .setId(notificationId)
                              .setContentAction(ContentAction(intent)))
    }
}
