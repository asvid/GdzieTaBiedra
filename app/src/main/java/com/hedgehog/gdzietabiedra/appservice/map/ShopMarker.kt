package com.hedgehog.gdzietabiedra.appservice.map

import com.github.asvid.biedra.domain.shops.Location
import com.github.asvid.biedra.domain.shops.Shop
import com.github.asvid.biedra.domain.shops.getForToday
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Map marker for [Shop]
 *
 * @property location - position of shop
 * @property shop - shop connected with map marker
 * */
data class ShopMarker private constructor(
        val location: Location,
        val shop: Shop
) : ClusterItem {
    companion object {
        /**
         * Factory for [ShopMarker]
         *
         * @param shop - shop for which map marker should be created
         * */
        fun create(shop: Shop) = ShopMarker(shop.location, shop)
    }

    override fun getPosition(): LatLng {
        return LatLng(location.lat, location.lng)
    }

    override fun getTitle(): String {
        return shop.address.streetName
    }

    override fun getSnippet(): String {
        return shop.openHours.getForToday().toString()
    }
}