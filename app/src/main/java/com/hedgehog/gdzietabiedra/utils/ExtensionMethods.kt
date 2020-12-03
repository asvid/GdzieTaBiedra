package com.hedgehog.gdzietabiedra.utils

import android.content.Context
import com.github.asvid.biedra.domain.Location
import com.google.android.gms.maps.model.LatLng
import org.joda.time.LocalDate
import java.text.DateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.math.roundToInt

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (this * multiplier).roundToInt() / multiplier
}

fun Location.toLatLng(): LatLng = LatLng(this.lat, this.lng)

fun LatLng.toPosition(): Location = Location(this.latitude, this.longitude)

fun LocalDate.toLocalFormat(context: Context): String {
    val date = this.toDate()
    val dateFormat: DateFormat = android.text.format.DateFormat.getDateFormat(context)
    return dateFormat.format(date)
}

fun String.toLocalTime(separator: String = ":"): LocalTime {
    val timeString = this.split(separator)
    return LocalTime.of(timeString[0].toInt(), timeString[1].toInt())
}

fun LocalTime.toLocalFormat(locale: Locale = Locale.getDefault()): String {
    val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(locale)
    return this.format(formatter)
}
