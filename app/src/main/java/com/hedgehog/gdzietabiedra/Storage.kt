package com.hedgehog.gdzietabiedra

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import io.realm.Realm
import io.realm.RealmConfiguration

class Storage(context: Context) {

    val realm: Realm
    val sharedPreferences: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        Realm.init(context)
        realm = Realm.getInstance(
                RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
    }

    fun saveToPreferences(preferenceName: String,
                          preferenceValue: String) {
        editor.putString(preferenceName, preferenceValue)
        editor.apply()
    }

    fun readFromPreferences(preferenceName: String,
                            defaultValue: String): String {
        return sharedPreferences.getString(preferenceName, defaultValue)
    }

    fun saveBoolean(preferenceName: String,
                    preferenceValue: Boolean?) {
        editor.putBoolean(preferenceName, preferenceValue!!)
        editor.apply()
    }

    fun readBoolean(preferenceName: String,
                    defaultValue: Boolean?): Boolean {
        return sharedPreferences.getBoolean(preferenceName, defaultValue!!)
    }
}