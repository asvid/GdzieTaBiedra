package com.hedgehog.gdzietabiedra.appservice

import android.content.Context
import android.location.LocationManager
import com.github.asvid.biedra.domain.Position
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.hedgehog.gdzietabiedra.appservice.LocationService.LocationException.LocationUnavailable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


class LocationService(val context: Context) {

  private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
      context)

  private var locationSubject = BehaviorSubject.create<Position>()

//  TODO: get events from Location service broadcast receiver and post events on subject on every change
//  subscribers should handle resubscribing on their own after error

  init {
    postLocationUpdate()
  }

  private fun postLocationUpdate() {
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
          location?.let {
            locationSubject.onNext(Position(it.latitude, it.longitude))
          }
        }
        .addOnFailureListener {
          locationSubject.onError(it)
        }
  }

  //  TODO change to [locationSubject] and fix uses in app interactors
  fun getLocation() = Single.just(Position(0.0, 0.0))

  fun serviceUpdate() {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    if (isGpsEnabled or isNetworkEnabled) postLocationUpdate()
    else postLocationUnavailable()
  }

  private fun postLocationUnavailable() {
    locationSubject.onError(LocationUnavailable)
  }

  sealed class LocationException : Throwable() {
    object LocationUnavailable : LocationException()
  }

}