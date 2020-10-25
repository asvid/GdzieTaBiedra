package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import com.github.asvid.biedra.domain.Shop
import com.google.android.gms.maps.model.LatLng
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.MEDIUM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Interface that hides implementation of concrete maps provider, allows easy switch from Google Maps to OpenMaps etc.
 * */
@ExperimentalCoroutinesApi
interface MapProvider {

  fun drawMarkers(points: Collection<ShopMarker>)

  fun drawMarker(point: ShopMarker, showInfo: Boolean)

  fun getMapCenterPosition(): Position

  fun shopMarkerClicked(): Flow<ShopMarker>

  fun clearMap()

  fun goToPosition(position: Position, mapZoom: MapZoom = MEDIUM)

  fun mapClicked(): Flow<LatLng>

  fun userMovedMap(): Flow<Position>

  fun selectShop(shop: Shop)
}

enum class MapZoom {
  CLOSE, MEDIUM, FAR
}