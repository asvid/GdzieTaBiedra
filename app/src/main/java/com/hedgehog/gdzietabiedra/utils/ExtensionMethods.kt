package com.hedgehog.gdzietabiedra.utils

import android.content.Context
import com.github.asvid.biedra.domain.Location
import com.github.asvid.biedra.domain.SundayShopping
import com.google.android.gms.maps.model.LatLng
import org.joda.time.LocalDate
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return Math.round(this * multiplier) / multiplier
}

fun Location.toLatLng(): LatLng = LatLng(this.lat, this.lng)

fun LatLng.toPosition(): Location = Location(this.latitude, this.longitude)

fun LocalDate.print(context: Context): String {
    val date = this.toDate()
    val dateFormat: DateFormat = android.text.format.DateFormat.getDateFormat(context)
    return dateFormat.format(date)
}
