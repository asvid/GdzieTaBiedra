package com.hedgehog.gdzietabiedra.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BiedraApi {

  @GET("shop/searchnearestlist")
  fun listRepos(@Query("lat") lat: Float, @Query("lng") lng: Float): Call<List<Repo>>
}