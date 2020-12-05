package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.shops.Location
import com.github.asvid.biedra.domain.shops.Shop
import com.google.android.gms.maps.model.LatLng
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.MEDIUM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Interface that hides implementation of concrete maps provider, allows easy switch from Google Maps to OpenMaps etc.
 * */
@ExperimentalCoroutinesApi
interface MapProvider {

    fun drawMarkersForShops(shops: List<Shop>)

    fun drawMarker(point: ShopMarker, showInfo: Boolean)

    fun getMapCenterPosition(): Location

    fun shopMarkerClicked(): Flow<ShopMarker>

    fun clearMap()

    fun goToPosition(location: Location, mapZoom: MapZoom = MEDIUM, onFinished: (() -> Unit)?)

    fun mapClicked(): Flow<LatLng>

    fun userMovedMap(): Flow<Location>

    fun showSingleShop(shop: Shop)
}

enum class MapZoom {
    CLOSE, MEDIUM, FAR
}