package com.hedgehog.gdzietabiedra.appservice

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import com.github.asvid.biedra.domain.Location
import com.google.android.gms.location.*
import com.hedgehog.gdzietabiedra.appservice.notifications.LocationProviderChangedReceiver
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

@SuppressLint("MissingPermission")
@ExperimentalCoroutinesApi
class LocationService(private val context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    val locationServiceStatus : Flow<Boolean> = callbackFlow {
        val br: BroadcastReceiver = LocationProviderChangedReceiver { isLocationOn ->
            offer(isLocationOn)
        }
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        context.registerReceiver(br, filter)
        awaitClose{
            Timber.d("unregistering receiver")
            context.unregisterReceiver(br)
        }
    }.distinctUntilChanged()

    suspend fun getLocation(): LocationServiceResult = suspendCancellableCoroutine { continuation ->
        if (!isPermissionGranted()) {
            continuation.resume(PermissionRequired)
        } else if (isLocationServiceAvailable()) {
            getLastKnownLocation(continuation)
        } else {
            continuation.resume(LocationNotAvailable)
        }
    }

    private fun getLastKnownLocation(continuation: CancellableContinuation<LocationServiceResult>) {
        fusedLocationClient.lastLocation.addOnSuccessListener { foundLocation ->
            if (foundLocation == null) {
                requestNewLocationAndContinue {
                    continuation.resume(it)
                }
            } else {
                continuation.resume(Success(Location(foundLocation.latitude, foundLocation.longitude)))
            }
        }
    }

    private fun requestNewLocationAndContinue(continueFunction: (LocationServiceResult) -> Unit) {
        fusedLocationClient.requestLocationUpdates(getLocationRequest(), object : LocationCallback() {
            override fun onLocationResult(locationServiceResult: LocationResult) {
                val lastLocation = locationServiceResult.lastLocation
                if (lastLocation != null) {
                    continueFunction(Success(Location(lastLocation.latitude, lastLocation.longitude)))
                } else {
                    continueFunction(Error("location was not available"))
                }
            }
        }, Looper.getMainLooper())
    }

    private fun getLocationRequest() = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        setExpirationDuration(5000)
        numUpdates = 1
    }

    private fun isLocationServiceAvailable(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isLocationEnabled
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