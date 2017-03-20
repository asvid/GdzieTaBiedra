package com.hedgehog.gdzietabiedra.utils

import android.location.Location
import com.hedgehog.gdzietabiedra.Di
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop
import com.hedgehog.gdzietabiedra.pojo.Shops.ShopList
import de.greenrobot.event.EventBus
import io.realm.RealmResults
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.*

/**
 * Created by Adam on 2015-06-18.
 */
object Database {

    private val realm = Di.storage.realm

    fun populate(lat: Double?, lng: Double?) {
        Di.Communication.shopApi
                .getShops(lat, lng, object : Callback<ShopList> {
                    override fun success(shopList: ShopList, response: Response) {
                        realm.beginTransaction()
                        realm.copyToRealmOrUpdate(shopList.shops)
                        realm.commitTransaction()

                        EventBus.getDefault().post(EventBusClasses.DatabaseUpdate())

                    }

                    override fun failure(error: RetrofitError) {

                    }
                })
    }

    fun getShopInfo(id: String): RealmResults<Shop> {
        val query = realm.where(Shop::class.java)
        query.equalTo("id", id)
        return query.findAll()
    }

    val all: RealmResults<Shop>
        get() {
            val query = realm.where(Shop::class.java)
            return query.findAll()
        }

    val listClosest: List<Shop>
        get() {

            val lastLocation = Di.locationService
                    .getLastLocation()
            val query = all
            val result = ArrayList<Shop>()

            if (lastLocation != null) {
                var i = 0
                val l = query.size
                while (i < l) {
                    val current = query[i]
                    val currentLoc = Location(lastLocation)
                    currentLoc.latitude = java.lang.Double.parseDouble(current.latitude)
                    currentLoc.longitude = java.lang.Double.parseDouble(current.longitude)
                    if (lastLocation.distanceTo(currentLoc) < Const.radius) {
                        realm.beginTransaction()
                        current.distance = lastLocation.distanceTo(currentLoc).toString() + ""
                        realm.commitTransaction()
                        if (!result.contains(current)) {
                            result.add(current)
                        }
                    }
                    i++
                }
            }

            Collections.sort(result, Comparator<Shop> { lhs, rhs ->
                if (java.lang.Double.parseDouble(lhs.distance) == java.lang.Double
                        .parseDouble(rhs.distance))
                    return@Comparator 0
                if (java.lang.Double.parseDouble(lhs.distance) < java.lang.Double
                        .parseDouble(rhs.distance))
                    -1
                else
                    1
            })
            return result
        }

    fun getByFilter(filter: String): List<Shop> {
        val query = realm.where(Shop::class.java)
                .contains("name", filter)
        return query.findAll()
    }

    fun getById(id: String): Shop {
        val query = realm.where(Shop::class.java).contains("id", id)
        return query.findAll()[0]
    }

}
