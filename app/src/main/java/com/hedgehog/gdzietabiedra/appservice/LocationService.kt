package com.hedgehog.gdzietabiedra.appservice

import android.content.Context
import com.github.asvid.biedra.domain.Position
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.Single
import timber.log.Timber


class LocationService(val context: Context) {

  private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
      context)

  fun getLocation(): Single<Position> {

    return Single.fromPublisher { subscriber ->
      fusedLocationClient.lastLocation
          .addOnSuccessListener {
            Timber.d("Location lastLocation addOnSuccessListener: $it")
            it?.let {
              subscriber.onNext(Position(it.latitude, it.longitude))
              subscriber.onComplete()
            }
          }
          .addOnFailureListener {
            Timber.d("Location ERROR: $it")
            subscriber.onError(it)
          }
          .addOnCompleteListener {
            Timber.d("Location on complete: $it")
            subscriber.onComplete()
          }
    }
  }

}