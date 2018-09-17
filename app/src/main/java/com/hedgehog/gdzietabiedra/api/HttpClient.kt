package com.hedgehog.gdzietabiedra.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class HttpClient {

  private fun buildHttpClient(): OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor(
          HttpLoggingInterceptor.Logger { message -> Timber.tag("OkHttp").d(message) })
          .setLevel(HttpLoggingInterceptor.Level.BODY))
      .build()

  fun buildRetrofit(): Retrofit = Retrofit.Builder()
      .baseUrl("http://www.biedronka.pl/api/mobile/")
      .client(buildHttpClient())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create())
      .build()

}