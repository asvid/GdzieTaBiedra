package com.hedgehog.gdzietabiedra.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BiedraService {

  //  http://www.biedronka.pl/api/mobile/shop?lat=51&lon=21
  @GET("shop")
  fun listRepos(@Query("lat") lat: Float, @Query("lon") lng: Float): Single<ShopsResponse>
}