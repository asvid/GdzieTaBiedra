package com.hedgehog.gdzietabiedra.utils

import com.hedgehog.gdzietabiedra.pojo.Shops.ShopList

import retrofit.Callback
import retrofit.http.Field
import retrofit.http.FormUrlEncoded
import retrofit.http.POST

/**
 * Created by Adam on 2015-06-15.
 */
class Api {
    interface Shop {
        @FormUrlEncoded
        @POST("/shop")
        fun getShops(@Field("lat") lat: Double?,
                     @Field("lon") lon: Double?,
                     cb: Callback<ShopList>)
    }
}
