package com.hedgehog.gdzietabiedra

import android.content.Context
import android.graphics.Color
import com.hedgehog.gdzietabiedra.viewHolders.LocationService
import io.github.asvid.notti.Notti
import io.github.asvid.notti.config.LightSettings
import io.github.asvid.notti.config.NottiConf
import io.github.asvid.notti.config.VibrationSettings
import kotlin.properties.Delegates

object Di {

    var context: Context by Delegates.notNull()

    fun set(context: Context) {
        this.context = context
    }

    val storage: Storage by lazy {
        Storage(context)
    }

    val locationService: LocationService by lazy {
        LocationService(context)
    }
    val Communication: Communication by lazy {
        Communication()
    }

    val notti: Notti by lazy {
        val lightSettings = LightSettings(Color.RED, 500, 500)
        val vibrationSettings = VibrationSettings(null, false)
        Notti(context, NottiConf(R.mipmap.ic_launcher, vibrationSettings, lightSettings))
    }
}