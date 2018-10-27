package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.MEDIUM
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.Completable
import io.reactivex.Observable

interface MapProvider {

  fun drawMarkers(points: Collection<ShopMarker>)

  fun drawMarker(point: ShopMarker, showInfo: Boolean)

  fun readMapPosition(): Position

  fun shopMarkerClicked(): Observable<ShopMarker>

  fun clearMap()

  fun goToPosition(position: Position, mapZoom: MapZoom = MEDIUM)

  fun mapClicked(): Observable<Any>

  fun mapMoved(): Observable<Position>

  fun selectShop(shop: Shop): Completable
}

enum class MapZoom {
  CLOSE, MEDIUM, FAR
}