package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.Position
import io.reactivex.Observable

interface IMapProvider {

  fun drawMarkers(points: Collection<ShopMarker>)

  fun drawMarker(point: ShopMarker)

  fun readMapPosition(): Position

  fun shopMarkerClicked(): Observable<ShopMarker>

  fun clearMap()

  fun goToPosition(position: Position)

  fun mapClicked(): Observable<Any>

  fun mapMoved(): Observable<Position>
}