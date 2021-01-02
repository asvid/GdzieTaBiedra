package com.hedgehog.gdzietabiedra.appservice

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.provider.Settings
import com.github.asvid.biedra.domain.Location
import com.google.android.gms.location.*
import com.hedgehog.gdzietabiedra.appservice.notifications.LocationProviderChangedReceiver
import com.hedgehog.gdzietabiedra.utils.resumeIfActive
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber

@SuppressLint("MissingPermission")
@ExperimentalCoroutinesApi
class LocationService(private val context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    val locationServiceStatus: Flow<Boolean> = callbackFlow {
        val br: BroadcastReceiver = LocationProviderChangedReceiver { isLocationOn ->
            offer(isLocationOn)
        }
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        context.registerReceiver(br, filter)
        awaitClose {
            Timber.d("unregistering receiver")
            context.unregisterReceiver(br)
        }
    }.distinctUntilChanged()

    suspend fun getLocation(): LocationServiceResult = suspendCancellableCoroutine { continuation ->
        if (!isPermissionGranted()) {
            continuation.resumeIfActive(PermissionRequired)
        } else if (isLocationServiceAvailable()) {
            getLastKnownLocation(continuation)
        } else {
                continuation.resumeIfActive(LocationNotAvailable)
        }
    }

    private fun getLastKnownLocation(continuation: CancellableContinuation<LocationServiceResult>) {
        fusedLocationClient.lastLocation.addOnSuccessListener { foundLocation ->
            if (foundLocation == null) {
                requestNewLocationAndContinue {
                    continuation.resumeIfActive(it)
                }
            } else {
                continuation.resumeIfActive(Success(Location(foundLocation.latitude, foundLocation.longitude)))
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
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // This is new method provided in API 28
            val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            lm.isLocationEnabled
        } else {
            // This is Deprecated in API 28
            val mode: Int = Settings.Secure.getInt(context.contentResolver, Settings.Secure.LOCATION_MODE,
                    Settings.Secure.LOCATION_MODE_OFF)
            mode != Settings.Secure.LOCATION_MODE_OFF
        }
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