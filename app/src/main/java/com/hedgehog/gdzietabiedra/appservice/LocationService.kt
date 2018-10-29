package com.hedgehog.gdzietabiedra.appservice

import android.content.Context
import com.github.asvid.biedra.domain.Position
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

class LocationService(val context: Context) {

  private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
      context)

  fun getLocation(): Single<Position> {
    return Single.fromPublisher { subscriber ->
      fusedLocationClient.lastLocation
          .addOnSuccessListener {
            it?.let {
              subscriber.onNext(Position(it.latitude, it.longitude))
              subscriber.onComplete()
            }
          }
    }
  }

}