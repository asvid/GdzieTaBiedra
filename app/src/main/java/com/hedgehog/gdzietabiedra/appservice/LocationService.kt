package com.hedgehog.gdzietabiedra.appservice

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import com.github.asvid.biedra.domain.Position
import com.github.asvid.biedra.domain.position
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val WARSAW = position {
    lat = 52.229990
    lng = 21.011572
}

@SuppressLint("MissingPermission")
class LocationService(private val context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    suspend fun getPosition(): LocationResult = suspendCoroutine {
        if (!isPermissionGranted()) {
            it.resume(PermissionRequired)
        }
        if (isLocationServiceAvailable()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                run {
                    it.resume(Success(Position(location.latitude, location.longitude)))
                }
            }
        } else {
            it.resume(LocationNotAvailable)
        }
    }

    private fun isLocationServiceAvailable(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        return isGpsEnabled or isNetworkEnabled
    }

    private fun isPermissionGranted(): Boolean {
        return when (PackageManager.PERMISSION_GRANTED) {
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) -> true
            else -> false
        }
    }
}

sealed class LocationResult
data class Success(val position: Position) : LocationResult()
data class Error(val message: String) : LocationResult()
object PermissionRequired : LocationResult()
object LocationNotAvailable : LocationResult()