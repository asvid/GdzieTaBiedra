package com.hedgehog.gdzietabiedra.data.persistance

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import timber.log.Timber

class SharedPrefsWrapper(context: Context) {

    private val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    suspend fun getString(key: String): String? {
        val string = sharedPref.getString(key, null)
        Timber.d("getting $string at $key")
        return string
    }

    suspend fun saveString(key: String, value: String) {
        Timber.d("saving $value at $key")
        sharedPref.edit().putString(key, value).apply()
    }

    suspend fun remove(key: String) {
        Timber.d("removing data at $key")
        sharedPref.edit().remove(key).apply()
    }

    suspend fun getLong(key: String): Long? {
        val defaultValue: Long = -1
        val long = sharedPref.getLong(key, defaultValue)
        Timber.d("getting $long at $key")
        return if(long == defaultValue) null else long
    }

    suspend fun getInt(key: String): Int? {
        val defaultValue: Int = -1
        val int = sharedPref.getInt(key, defaultValue)
        Timber.d("getting $int at $key")
        return if(int == defaultValue) null else int
    }

}