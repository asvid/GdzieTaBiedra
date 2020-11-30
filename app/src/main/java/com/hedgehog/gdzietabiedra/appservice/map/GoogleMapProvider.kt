package com.hedgehog.gdzietabiedra.appservice.map

import android.content.Context
import com.github.asvid.biedra.domain.Location
import com.github.asvid.biedra.domain.Shop
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.collections.MarkerManager
import com.hedgehog.gdzietabiedra.appservice.map.MapZoom.*
import com.hedgehog.gdzietabiedra.utils.toLatLng
import com.hedgehog.gdzietabiedra.utils.toPosition
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

/**
 * Map provider build around [GoogleMap]
 * */

@FlowPreview
@ExperimentalCoroutinesApi
class GoogleMapProvider private constructor(private val context: Context) : MapProvider {

    private lateinit var markerClusterRenderer: MarkerClusterRenderer
    private lateinit var map: GoogleMap
    private lateinit var clusterManager: ClusterManager<ShopMarker>
    private lateinit var markerManager: MarkerManager

    private val markerChannel = BroadcastChannel<ShopMarker>(1)
    private val mapClickChannel = BroadcastChannel<LatLng>(1)

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
        setUpClusterer(googleMap)
        this.map.setOnMapClickListener {
            mapClickChannel.offer(it)
        }
    }

    override fun drawMarkers(points: Collection<ShopMarker>) {
        clusterManager.clearItems()
        points.forEach { shopMarker ->
            clusterManager.addItem(shopMarker)
        }
        clusterManager.cluster()
    }

    override fun drawMarker(point: ShopMarker, showInfo: Boolean) {
        clusterManager.addItem(point)
        clusterManager.cluster()
    }

    override fun getMapCenterPosition(): Location {
        return map.cameraPosition.target.toPosition()
    }

    override fun shopMarkerClicked(): Flow<ShopMarker> = markerChannel.asFlow()

    override fun mapClicked(): Flow<LatLng> = mapClickChannel.asFlow()

    override fun userMovedMap(): Flow<Location> = callbackFlow {
        map.setOnCameraMoveStartedListener { reason ->
            if (reason == REASON_GESTURE) {
                map.setOnCameraIdleListener {
                    this.offer(getMapCenterPosition())
                    clusterManager.clearItems()
                }
            } else {
                map.setOnCameraIdleListener(null)
            }
        }
        awaitClose { map.setOnCameraMoveStartedListener(null) }
    }

    override fun clearMap() {
        clusterManager.clearItems()
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
        clusterManager.addItem(shopMarker)
        goToPosition(shop.location, CLOSE)
        markerClusterRenderer.listenOnceForRender { marker ->
            marker.showInfoWindow()
            markerChannel.offer(shopMarker)
        }
    }

    private fun setUpClusterer(googleMap: GoogleMap) {
        markerManager = MarkerManager(googleMap)
        clusterManager = ClusterManager(context, googleMap, markerManager)
        markerClusterRenderer = MarkerClusterRenderer(context, googleMap, clusterManager)
        clusterManager.renderer = markerClusterRenderer
        markerClusterRenderer.setAnimation(false)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
        markerManager.Collection().setOnMarkerClickListener {
            it.showInfoWindow()
            false
        }
        clusterManager.setOnClusterItemClickListener {
            markerChannel.offer(it)
            false
        }
    }
}