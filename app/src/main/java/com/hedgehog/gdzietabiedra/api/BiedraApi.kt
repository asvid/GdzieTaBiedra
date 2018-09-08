package com.hedgehog.gdzietabiedra.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BiedraApi {

//  http://www.biedronka.pl/api/mobile/shop?lat=51&lon=21
  @GET("shop")
  fun listRepos(@Query("lat") lat: Float, @Query("lon") lng: Float): Call<List<ShopApi>>
}