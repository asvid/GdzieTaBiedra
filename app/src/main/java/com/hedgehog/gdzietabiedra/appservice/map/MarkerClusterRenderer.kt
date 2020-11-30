package com.hedgehog.gdzietabiedra.appservice.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.ViewGroup
import android.widget.ImageView
import com.github.asvid.biedra.domain.SundayShopping
import com.github.asvid.biedra.domain.getForToday
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.toLatLng

private const val MARKER_DIMENSION = 128

class MarkerClusterRenderer(
        val context: Context,
        map: GoogleMap,
        clusterManager: ClusterManager<ShopMarker>
) : DefaultClusterRenderer<ShopMarker>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: ShopMarker, markerOptions: MarkerOptions) {

        val markerIcon = BitmapDescriptorFactory.fromBitmap(
                resizeMapIcons(R.mipmap.bierdra_map_marker, MARKER_DIMENSION, MARKER_DIMENSION))

        val openingHoursText: String =
                if (SundayShopping.isShoppingAllowed())
                    item.shop.openHours.getForToday().toString()
                else context.resources.getString(R.string.shop_closed)

        markerOptions.apply {
            position(item.location.toLatLng())
            title(item.shop.address.toString())
            snippet(openingHoursText)
            icon(markerIcon)
        }
    }

    private fun resizeMapIcons(iconResId: Int, width: Int, height: Int): Bitmap {
        val imageBitmap = BitmapFactory.decodeResource(context.resources, iconResId)
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }
}