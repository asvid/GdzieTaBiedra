package com.hedgehog.gdzietabiedra.api

import com.hedgehog.gdzietabiedra.api.response.shopKtor.ShopsResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

class BiedraKtorService {

  private val BASE_URL = "http://www.biedronka.pl/api/mobile"
  private val client = HttpClient(OkHttp) {
    install(JsonFeature) {
      serializer = KotlinxSerializer()
    }
  }

  suspend fun getShops(lat: Float, lng: Float): ShopsResponse =
      client.get("$BASE_URL/shop?lat=$lat&lon=$lng")

  companion object {
    var INSTANCE = BiedraKtorService()
  }
}