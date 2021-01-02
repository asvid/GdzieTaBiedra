package com.hedgehog.gdzietabiedra.utils

import android.content.Context
import android.content.res.Resources
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import com.github.asvid.biedra.domain.Location
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.hedgehog.gdzietabiedra.R
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.suspendCancellableCoroutine
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.roundToInt

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (this * multiplier).roundToInt() / multiplier
}

fun Location.toLatLng(): LatLng = LatLng(this.lat, this.lng)

fun LatLng.toPosition(): Location = Location(this.latitude, this.longitude)

fun LocalDate.toLocalFormat(locale: Locale = Locale.getDefault()): String {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale)
    return this.format(formatter)
}

fun Context.getLocale(): Locale = resources.configuration.locale

fun String.toLocalTime(separator: String = ":"): LocalTime {
    val timeString = this.split(separator)
    return LocalTime.of(timeString[0].toInt(), timeString[1].toInt())
}

fun LocalTime.toLocalFormat(locale: Locale = Locale.getDefault()): String {
    val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(locale)
    return this.format(formatter)
}

fun Double?.generateDistanceText(resources: Resources): String {
    val distanceText = when {
        this == null -> ""
        this > 5000 -> "${(this / 1000).roundToInt()} km"
        this > 1000 -> "${(this / 1000).round(2)} km"
        else -> "${this.roundToInt()} m"
    }
    return resources.getString(R.string.distance, distanceText)
}

suspend fun <T> Task<T>.await(): T {
    // fast path
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) {
                throw CancellationException("Task $this was cancelled normally.")
            } else {
                @Suppress("UNCHECKED_CAST")
                result as T
            }
        } else {
            throw e
        }
    }

    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            val e = exception
            if (e == null) {
                @Suppress("UNCHECKED_CAST")
                if (isCanceled) cont.cancel() else cont.resume(result as T)
            } else {
                cont.resumeWithException(e)
            }
        }
    }
}

fun <T> CancellableContinuation<T>.resumeIfActive(result: T) {
    if (this.isActive)
        this.resume(result)
}

fun Context.isLocationServiceAvailable(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        // This is new method provided in API 28
        val lm = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        lm.isLocationEnabled
    } else {
        // This is Deprecated in API 28
        val mode: Int = Settings.Secure.getInt(this.contentResolver, Settings.Secure.LOCATION_MODE,
                Settings.Secure.LOCATION_MODE_OFF)
        mode != Settings.Secure.LOCATION_MODE_OFF
    }
}