package com.hedgehog.gdzietabiedra.appservice.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.github.asvid.biedra.domain.shops.SundayShopping
import com.github.asvid.biedra.domain.shops.getForToday
import com.github.asvid.biedra.domain.shops.printName
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.toLatLng
import kotlin.math.roundToInt


private const val MARKER_DIMENSION = 32

class MarkerClusterRenderer(
        val context: Context,
        val map: GoogleMap,
        clusterManager: ClusterManager<ShopMarker>
) : DefaultClusterRenderer<ShopMarker>(context, map, clusterManager) {

    private var renderingListener: ((marker: Marker) -> Unit)? = null
    private val markerIcon = BitmapDescriptorFactory.fromBitmap(
            resizeMapIcons(R.mipmap.map_marker, MARKER_DIMENSION, MARKER_DIMENSION))

    override fun onBeforeClusterItemRendered(item: ShopMarker, markerOptions: MarkerOptions) {
        markerOptions.apply {
            position(item.location.toLatLng())
            title(item.shop.printName())
            snippet(generateSnippet(item))
            icon(markerIcon)
        }
    }

    override fun onClusterItemRendered(clusterItem: ShopMarker, marker: Marker) {
        super.onClusterItemRendered(clusterItem, marker)
        renderingListener?.invoke(marker)
        renderingListener = null
    }

    private fun generateSnippet(item: ShopMarker): String {
        return if (SundayShopping.isShoppingAllowed())
            item.shop.openHours.getForToday().toString()
        else context.resources.getString(R.string.shop_closed)
    }

    private fun resizeMapIcons(iconResId: Int, width: Int, height: Int): Bitmap {
        val density = context.resources.displayMetrics.density
        val imageBitmap = BitmapFactory.decodeResource(context.resources, iconResId)
        return Bitmap.createScaledBitmap(imageBitmap, (width * density).roundToInt(), (height * density).toInt(), false)
    }

    fun listenToRenderOnce(renderingListener: (marker: Marker) -> Unit) {
        this.renderingListener = renderingListener
    }
}