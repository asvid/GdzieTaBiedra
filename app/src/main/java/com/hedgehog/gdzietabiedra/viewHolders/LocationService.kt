package com.hedgehog.gdzietabiedra.viewHolders

import android.content.Context
import android.location.Location
import android.location.LocationManager

class LocationService(val context: Context) {

    fun getLastLocation(): Location? {
        return (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
                .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
    }
}