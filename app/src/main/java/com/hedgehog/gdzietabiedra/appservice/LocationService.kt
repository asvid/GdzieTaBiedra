package com.hedgehog.gdzietabiedra.appservice

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.hedgehog.gdzietabiedra.domain.Point
import io.reactivex.Single

class LocationService(val context: Context) {

  private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
      context)

  fun getLocation(): Single<Point> {
    return Single.fromPublisher { subscriber ->
      fusedLocationClient.lastLocation
          .addOnSuccessListener {
            subscriber.onNext(Point(it.latitude, it.longitude))
            subscriber.onComplete()
          }
    }
  }

}