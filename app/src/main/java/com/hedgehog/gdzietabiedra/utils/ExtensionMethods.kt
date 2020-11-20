package com.hedgehog.gdzietabiedra.utils

import com.github.asvid.biedra.domain.Location
import com.google.android.gms.maps.model.LatLng
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Int.toLocalNumberFormat(locale: Locale): String {
    return NumberFormat.getNumberInstance(locale).format(this)
}

fun String.toDate(format: String): Date? {
    val formatter = SimpleDateFormat(format, Locale("pl"))
    return formatter.parse(this)
}

fun Date.print(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale("pl"))
    return dateFormat.format(this)
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return Math.round(this * multiplier) / multiplier
}

fun Location.toLatLng(): LatLng = LatLng(this.lat, this.lng)

fun LatLng.toPosition(): Location = Location(this.latitude, this.longitude)
