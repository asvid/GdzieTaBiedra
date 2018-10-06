package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hedgehog.gdzietabiedra.utils.toLatLng
import com.hedgehog.gdzietabiedra.utils.toPosition
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

const val DEFAULT_MAP_ZOOM = 13f

class GoogleMapProvider private constructor() : IMapProvider {

  private lateinit var map: GoogleMap
  private val mapMarkers = hashMapOf<Marker, ShopMarker>()
  private lateinit var markerSubject: PublishSubject<ShopMarker>

  companion object {
    fun create(googleMap: GoogleMap): GoogleMapProvider {
      return GoogleMapProvider().also { it.initialize(googleMap) }
    }
  }

  private fun initialize(googleMap: GoogleMap) {
    this.map = googleMap

    markerSubject = PublishSubject.create()
    this.map.setOnMarkerClickListener { marker ->
      mapMarkers[marker]?.let {
        markerSubject.onNext(it)
      }
      true
    }
  }

  override fun drawMarkers(points: Collection<ShopMarker>) {
    points.forEach { shopMarker ->
      val markerOptions = MarkerOptions()
          .position(shopMarker.position.toLatLng())
          .title(shopMarker.shop.address)
      val marker = map.addMarker(markerOptions)
      mapMarkers[marker] = shopMarker
    }
  }

  override fun readMapPosition(): Position {
    return map.cameraPosition.target.toPosition()
  }

  override fun shopMarkerClicked(): Observable<ShopMarker> = markerSubject

  override fun clearMap() {
    mapMarkers.clear()
    map.clear()
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun goToPosition(position: Position) {
    map.animateCamera(
        CameraUpdateFactory
            .newLatLngZoom(position.toLatLng(), DEFAULT_MAP_ZOOM)
    )
  }
}