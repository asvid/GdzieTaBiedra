package com.hedgehog.gdzietabiedra.utils

import com.github.asvid.biedra.domain.Location
import com.google.android.gms.maps.model.LatLng

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return Math.round(this * multiplier) / multiplier
}

fun Location.toLatLng(): LatLng = LatLng(this.lat, this.lng)

fun LatLng.toLocation(): Location = Location(this.latitude, this.longitude)
