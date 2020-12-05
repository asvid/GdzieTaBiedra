package com.hedgehog.gdzietabiedra.appservice.map

import android.content.Context
import com.github.asvid.biedra.domain.Location
import com.github.asvid.biedra.domain.Shop
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
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
import timber.log.Timber

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
                        it.initialize(googleMap)
                    }
        }
    }

    private fun initialize(googleMap: GoogleMap) {
        this.map = googleMap
        setUpClusterer(googleMap)
        this.map.setOnMapClickListener {
            mapClickChannel.offer(it)
        }
    }

    override fun drawMarkersForShops(shops: List<Shop>) {
        clearMap()
        Timber.d("${shops.size}")
        val latLngBounds = getMapVisibleBounds()
        shops.forEach { shop ->
            if (isInBounds(shop.location, latLngBounds)) {
                clusterManager.addItem(ShopMarker.create(shop))
            } else {
                Timber.d("shop outside visible map")
            }
        }

        clusterManager.cluster()
        Timber.d("cluster size: ${clusterManager.markerCollection.markers.size}")

    }

    private fun getMapVisibleBounds(): LatLngBounds {
        val latLngBounds = map.projection.visibleRegion.latLngBounds
        Timber.d("latLngBounds: $latLngBounds")
        return latLngBounds
    }

    override fun drawMarker(point: ShopMarker, showInfo: Boolean) {
        // NOOP
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

    override fun goToPosition(location: Location, mapZoom: MapZoom, onFinished: (() -> Unit)?) {
        val googleMapZoom = when (mapZoom) {
            CLOSE -> CLOSE_MAP_ZOOM
            MEDIUM -> MEDIUM_MAP_ZOOM
            FAR -> FAR_MAP_ZOOM
        }
        map.animateCamera(
                CameraUpdateFactory
                        .newLatLngZoom(location.toLatLng(), googleMapZoom), 100, object : GoogleMap.CancelableCallback {
            override fun onFinish() {
                onFinished?.invoke()
            }

            override fun onCancel() {
                // noop
            }
        }
        )
    }

    override fun showSingleShop(shop: Shop) {
        clearMap()
        val shopMarker = ShopMarker.create(shop)
        clusterManager.addItem(shopMarker)
        goToPosition(shop.location, CLOSE) {
            markerClusterRenderer.listenToRenderOnce { marker ->
                marker.showInfoWindow()
                markerChannel.offer(shopMarker)
            }
        }
    }

    private fun setUpClusterer(googleMap: GoogleMap) {
        markerManager = MarkerManager(googleMap)
        markerManager.Collection().setOnMarkerClickListener {
            it.showInfoWindow()
            false
        }

        clusterManager = ClusterManager(context, googleMap, markerManager)

        markerClusterRenderer = MarkerClusterRenderer(context, googleMap, clusterManager)
        markerClusterRenderer.setAnimation(true)

        clusterManager.markerCollection.setInfoWindowAdapter(BiedraInfoAdapter(context))
        clusterManager.renderer = markerClusterRenderer
        clusterManager.setOnClusterItemClickListener {
            markerChannel.offer(it)
            false
        }

        map.setOnCameraIdleListener(clusterManager)
    }

    private fun isInBounds(location: Location, latLngBounds: LatLngBounds): Boolean {
        return latLngBounds.contains(location.toLatLng())
    }
}