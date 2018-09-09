package com.hedgehog.gdzietabiedra.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

  var retrofit: Retrofit = Retrofit.Builder()
      .baseUrl("http://www.biedronka.pl/api/mobile/")
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create())
      .build()

  var biedraService: BiedraService = retrofit.create(BiedraService::class.java)
}