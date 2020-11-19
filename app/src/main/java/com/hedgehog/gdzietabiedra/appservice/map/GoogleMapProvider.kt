package com.hedgehog.gdzietabiedra.appservice.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createScaledBitmap
import android.graphics.BitmapFactory
import com.github.asvid.biedra.domain.Location
import com.github.asvid.biedra.domain.Shop
import com.github.asvid.biedra.domain.SundayShopping
import com.github.asvid.biedra.domain.getForToday
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.*
import com.hedgehog.gdzietabiedra.utils.toLatLng
import com.hedgehog.gdzietabiedra.utils.toLocation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow

private const val MEDIUM_MAP_ZOOM = 13f
private const val CLOSE_MAP_ZOOM = 15f
private const val FAR_MAP_ZOOM = 10f
private const val MAP_MARKER_SIZE = 300

/**
 * Map provider build around [GoogleMap]
 * */

@FlowPreview
@ExperimentalCoroutinesApi
class GoogleMapProvider private constructor(private val context: Context) : MapProvider {

    private lateinit var map: GoogleMap
    private val mapMarkers = hashMapOf<Marker, ShopMarker>()
    private val markerChannel = BroadcastChannel<ShopMarker>(1)
    private val mapClickChannel = BroadcastChannel<LatLng>(1)
    private val markerIcon = BitmapDescriptorFactory.fromBitmap(
            resizeMapIcons(R.mipmap.bierdra_map_marker, MAP_MARKER_SIZE, MAP_MARKER_SIZE))

    companion object {
        fun create(googleMap: GoogleMap, context: Context): GoogleMapProvider {
            return GoogleMapProvider(context)
                    .also {
                        it.initialize(googleMap, context)
                    }
        }
    }

    private fun initialize(googleMap: GoogleMap, context: Context) {
        this.map = googleMap
        this.map.setInfoWindowAdapter(BiedraInfoAdapter(context))
        this.map.setOnMarkerClickListener { marker ->
            mapMarkers[marker]?.let {
                markerChannel.offer(it)
                marker.showInfoWindow()
            }
            true
        }
        this.map.setOnMapClickListener {
            mapClickChannel.offer(it)
        }
    }

    override fun drawMarkers(points: Collection<ShopMarker>) {
        points.forEach { shopMarker ->
            drawMarker(shopMarker, false)
        }
    }

    private fun resizeMapIcons(iconResId: Int, width: Int, height: Int): Bitmap {
        val imageBitmap = BitmapFactory.decodeResource(context.resources, iconResId)
        return createScaledBitmap(imageBitmap, width, height, false)
    }

    override fun drawMarker(point: ShopMarker, showInfo: Boolean) {
        if (mapMarkers.values.contains(point)) return

        val openingHoursText: String =
                if (SundayShopping.isShoppingAllowed())
                    point.shop.openHours.getForToday().toString()
                else context.resources.getString(R.string.shop_closed)

        val markerOptions = MarkerOptions()
                .position(point.location.toLatLng())
                .title(point.shop.address.toString())
                .snippet(openingHoursText)
                .icon(markerIcon)
        val marker = map.addMarker(markerOptions)
        if (showInfo) marker.showInfoWindow()
        mapMarkers[marker] = point
    }

    override fun getMapCenterPosition(): Location {
        return map.cameraPosition.target.toLocation()
    }

    override fun shopMarkerClicked(): Flow<ShopMarker> = markerChannel.asFlow()

    override fun mapClicked(): Flow<LatLng> = mapClickChannel.asFlow()

    override fun userMovedMap(): Flow<Location> = callbackFlow {
        map.setOnCameraMoveStartedListener { reason ->
            if (reason == REASON_GESTURE) {
                map.setOnCameraIdleListener {
                    this.offer(getMapCenterPosition())
                    mapMarkers.keys.forEach { marker ->
                        marker.hideInfoWindow()
                        val bounds = map.projection.visibleRegion.latLngBounds
                        if (!bounds.contains(marker.position)) marker.remove()
                    }
                }
            } else {
                map.setOnCameraIdleListener(null)
            }
        }
        awaitClose { map.setOnCameraMoveStartedListener(null) }
    }

    override fun clearMap() {
        mapMarkers.clear()
        map.clear()
    }

    override fun goToPosition(location: Location, mapZoom: MapZoom) {
        val googleMapZoom = when (mapZoom) {
            CLOSE -> CLOSE_MAP_ZOOM
            MEDIUM -> MEDIUM_MAP_ZOOM
            FAR -> FAR_MAP_ZOOM
        }
        map.animateCamera(
                CameraUpdateFactory
                        .newLatLngZoom(location.toLatLng(), googleMapZoom), 100, null
        )
    }

    override fun selectShop(shop: Shop) {
        map.clear()
        val shopMarker = ShopMarker.create(shop)
        drawMarker(shopMarker, true)
        goToPosition(shop.location, CLOSE)
        markerChannel.offer(shopMarker)
    }
}