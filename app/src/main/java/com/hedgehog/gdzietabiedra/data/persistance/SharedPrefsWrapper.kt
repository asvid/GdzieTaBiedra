package com.hedgehog.gdzietabiedra.data.persistance

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsWrapper(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("Notifications", Context.MODE_PRIVATE)

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun saveString(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

}