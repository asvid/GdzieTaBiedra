package com.hedgehog.gdzietabiedra

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.hedgehog.gdzietabiedra.utils.Api
import com.hedgehog.gdzietabiedra.utils.Const
import com.squareup.okhttp.OkHttpClient
import io.realm.RealmObject
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import kotlin.properties.Delegates

class Communication {

    var shopApi: Api.Shop by Delegates.notNull()

    init {
        val okHttpClient = OkHttpClient()

        val gson = GsonBuilder()
                .setExclusionStrategies(object : ExclusionStrategy {
                    override fun shouldSkipField(f: FieldAttributes): Boolean {
                        return f.declaringClass == RealmObject::class.java
                    }

                    override fun shouldSkipClass(clazz: Class<*>): Boolean {
                        return false
                    }
                }).create()

        val restAdapter = RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(Const.SERVER_URL)
                .setConverter(GsonConverter(gson))
                .setClient(OkClient(okHttpClient)).build()

        shopApi = restAdapter.create(Api.Shop::class.java)
    }

}