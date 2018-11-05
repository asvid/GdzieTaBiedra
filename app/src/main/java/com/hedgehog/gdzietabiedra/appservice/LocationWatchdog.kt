package com.hedgehog.gdzietabiedra.appservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import com.github.asvid.biedra.domain.Position
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

private val WARSAW = Position(52.229990, 21.011572)

class LocationWatchdog(val context: Context) {

  private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
      context)

  private var locationSubject = BehaviorSubject.create<Position>()
  private var locationEnabled = BehaviorSubject.create<Boolean>()
  private var locationAvailable = BehaviorSubject.create<Boolean>()

  private val compositeDisposable = CompositeDisposable()

  init {
    Timber.d("LocationWatchdog init")
  }

  private fun checkLocationAvailable() {
    Timber.d("checking if location is available")
    fusedLocationClient.locationAvailability
        .addOnSuccessListener {
          Timber.d("checking if location is available: ${it.isLocationAvailable}")
          locationAvailable.onNext(it.isLocationAvailable)
        }
  }

  private fun publishLocation() {
    Timber.d("publishing Location")
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
          Timber.d("publishing Location: $location")
          if (location == null) publishDefaultLocation()
          else locationSubject.onNext(Position(location.latitude, location.longitude))
        }
        .addOnFailureListener {
          publishDefaultLocation()
        }
  }

  fun getLocation() = locationSubject.startWith(WARSAW)

  fun locationEnabledSubject() = locationEnabled

  private fun serviceUpdate() {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    if (isGpsEnabled or isNetworkEnabled) {
      Timber.d("LocationWatchdog serviceUpdate TRUE")
      locationEnabled.onNext(true)
    } else {
      Timber.d("LocationWatchdog serviceUpdate FALSE")
      locationEnabled.onNext(false)
    }
  }

  private fun publishDefaultLocation() {
    if (!locationSubject.hasValue()) {
      locationSubject.onNext(WARSAW)
    }
  }

  private val gpsReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      if (intent.action!!.matches(LocationManager.PROVIDERS_CHANGED_ACTION.toRegex())) {
        serviceUpdate()
      }
    }
  }

  fun register() {
    Timber.d("LocationWatchdog register")
    registrSubscribers()
    serviceUpdate()
    context.registerReceiver(gpsReceiver, IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION))
  }

  private fun registrSubscribers() {
    Timber.d("LocationWatchdog registrSubscribers")
    locationEnabled
        .filter { it }
        .flatMap { enabled ->
          Observable
              .interval(1000, TimeUnit.MILLISECONDS)
              .map {
                Timber.d("LocationWatchdog interval $enabled")
                checkLocationAvailable()
              }
              .takeWhile { enabled }
              .takeUntil { locationAvailable.value ?: true }
        }
        .subscribe {
          Timber.d("LocationWatchdog locationEnabled onNext")
        }
        .addToDisposables()

    locationAvailable
        .startWith(false)
        .filter { it }
        .map { available ->
          Timber.d("LocationWatchdog map $available")
          publishLocation()
        }
        .subscribe {
          Timber.d("LocationWatchdog locationAvailable onNext")
        }
        .addToDisposables()
  }

  fun unregister() {
    Timber.d("LocationWatchdog unregister")
    context.unregisterReceiver(gpsReceiver)
    compositeDisposable.dispose()
  }

  private fun Disposable.addToDisposables() {
    this.addTo(compositeDisposable)
  }
}