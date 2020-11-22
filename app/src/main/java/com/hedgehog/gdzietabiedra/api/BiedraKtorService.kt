package com.hedgehog.gdzietabiedra.api

import com.hedgehog.gdzietabiedra.api.response.shopKtor.ShopsResponse
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class BiedraKtorService {

    private val BASE_URL = "http://www.biedronka.pl/api/mobile"
    private val client = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getShops(lat: Double, lng: Double): ShopsResponse =
            client.get("$BASE_URL/shop?lat=$lat&lon=$lng")
}