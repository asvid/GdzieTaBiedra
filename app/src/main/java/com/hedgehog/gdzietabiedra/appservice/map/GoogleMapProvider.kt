package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.CLOSE
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.FAR
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.MEDIUM
import com.hedgehog.gdzietabiedra.domain.Shop
import com.hedgehog.gdzietabiedra.utils.toLatLng
import com.hedgehog.gdzietabiedra.utils.toPosition
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

private const val MEDIUM_MAP_ZOOM = 13f
private const val CLOSE_MAP_ZOOM = 15f
private const val FAR_MAP_ZOOM = 10f

class GoogleMapProvider private constructor() : IMapProvider {

  private lateinit var map: GoogleMap
  private val mapMarkers = hashMapOf<Marker, ShopMarker>()
  private val markerSubject: PublishSubject<ShopMarker> = PublishSubject.create()
  private val mapClickSubject: PublishSubject<Any> = PublishSubject.create()

  companion object {
    fun create(googleMap: GoogleMap): GoogleMapProvider {
      return GoogleMapProvider().also { it.initialize(googleMap) }
    }
  }

  private fun initialize(googleMap: GoogleMap) {
    this.map = googleMap
    this.map.setOnMarkerClickListener { marker ->
      mapMarkers[marker]?.let {
        markerSubject.onNext(it)
        marker.showInfoWindow()
      }
      true
    }
    this.map.setOnMapClickListener {
      mapClickSubject.onNext(Any())
    }
  }

  override fun drawMarkers(points: Collection<ShopMarker>) {
    points.forEach { shopMarker ->
      val markerOptions = MarkerOptions()
          .position(shopMarker.position.toLatLng())
          .title(shopMarker.shop.address)
          .snippet(shopMarker.shop.openHours)
          .icon(
              BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round)
          )
      val marker = map.addMarker(markerOptions)
      mapMarkers[marker] = shopMarker
    }
  }

  override fun drawMarker(shopMarker: ShopMarker, showInfo: Boolean) {
    val markerOptions = MarkerOptions()
        .position(shopMarker.position.toLatLng())
        .title(shopMarker.shop.address)
        .snippet(shopMarker.shop.openHours)
        .icon(
            BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round)
        )
    val marker = map.addMarker(markerOptions)
    if (showInfo) marker.showInfoWindow()
    mapMarkers[marker] = shopMarker
  }

  override fun readMapPosition(): Position {
    return map.cameraPosition.target.toPosition()
  }

  override fun shopMarkerClicked(): Observable<ShopMarker> = markerSubject

  override fun mapClicked(): Observable<Any> = mapClickSubject

  override fun clearMap() {
    mapMarkers.clear()
    map.clear()
  }

  override fun goToPosition(position: Position, mapZoom: MapZoom) {
    val googleMapZoom = when (mapZoom) {
      CLOSE -> CLOSE_MAP_ZOOM
      MEDIUM -> MEDIUM_MAP_ZOOM
      FAR -> FAR_MAP_ZOOM
    }
    map.animateCamera(
        CameraUpdateFactory
            .newLatLngZoom(position.toLatLng(), googleMapZoom), 100, null
    )
  }

  override fun mapMoved(): Observable<Position> {
    return Observable
        .fromPublisher<Boolean> {
          map.setOnCameraMoveStartedListener { reason ->
            when (reason) {
              OnCameraMoveStartedListener.REASON_GESTURE -> it.onNext(true)
              else -> it.onNext(false)
            }
          }
        }
        .flatMap { userInput ->
          Timber.d("user input: $userInput")
          Observable.fromPublisher<Position> { position ->
            this.map.setOnCameraIdleListener {
              if (userInput)
                position.onNext(readMapPosition())
            }
          }
        }
  }

  override fun selectShop(shop: Shop): Completable = Completable.fromAction {
    map.clear()
    val shopMarker = ShopMarker.create(shop)
    drawMarker(shopMarker, true)
    goToPosition(shop.location, CLOSE)
    markerSubject.onNext(shopMarker)
  }
}