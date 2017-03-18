package com.hedgehog.gdzietabiedra

import android.app.Application
import android.content.Intent
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.hedgehog.gdzietabiedra.utils.Api
import com.hedgehog.gdzietabiedra.utils.Const
import com.hedgehog.gdzietabiedra.utils.NotificationService
import com.squareup.okhttp.OkHttpClient
import io.realm.RealmObject
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import kotlin.properties.Delegates

/**
 * Created by Adam on 2015-06-15.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()

        if (Di.storage.readBoolean("radar", Const.radiusCheckDefault)) {
            startService(Intent(this, NotificationService::class.java))
        }
    }

    private fun initDi() {
        Di.set(this)
    }
}
