package com.hedgehog.gdzietabiedra.appservice

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import com.github.asvid.biedra.domain.Location
import com.github.asvid.biedra.domain.location
import com.google.android.gms.location.*
import com.hedgehog.gdzietabiedra.utils.isLocationServiceAvailable
import com.hedgehog.gdzietabiedra.utils.resumeIfActive
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@SuppressLint("MissingPermission")
class LocationService(private val context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    suspend fun getLocation(): LocationServiceResult = suspendCancellableCoroutine { continuation ->
        if (!isPermissionGranted()) {
            continuation.resumeIfActive(PermissionRequired)
        } else if (context.isLocationServiceAvailable()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { foundLocation ->
                if (foundLocation == null) {
                    requestNewLocationAndContinue {
                        continuation.resumeIfActive(it)
                    }
                } else {
                    continuation.resumeIfActive(Success(Location(foundLocation.latitude, foundLocation.longitude)))
                }
            }
        } else {
            continuation.resumeIfActive(LocationNotAvailable)
        }
    }

    private fun requestNewLocationAndContinue(continueFunction: (LocationServiceResult) -> Unit) {
        fusedLocationClient.requestLocationUpdates(getLocationRequest(), object : LocationCallback() {
            override fun onLocationResult(locationServiceResult: LocationResult?) {
                if (locationServiceResult != null && locationServiceResult.locations.isNotEmpty()) {
                    val newLocation = locationServiceResult.locations[0]
                    continueFunction(Success(Location(newLocation.latitude, newLocation.longitude)))
                } else {
                    continueFunction(Error("location was not available"))
                }
            }
        }, Looper.myLooper())
    }

    private fun getLocationRequest() = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        setExpirationDuration(5000)
        numUpdates = 1
    }

    private fun isPermissionGranted(): Boolean {
        return when (PackageManager.PERMISSION_GRANTED) {
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) -> true
            else -> false
        }
    }
}

sealed class LocationServiceResult
data class Success(val location: Location) : LocationServiceResult()
data class Error(val message: String) : LocationServiceResult()
object PermissionRequired : LocationServiceResult()
object LocationNotAvailable : LocationServiceResult()