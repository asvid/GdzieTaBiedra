package com.hedgehog.gdzietabiedra.data.repository

import com.hedgehog.gdzietabiedra.data.persistance.SharedPrefsWrapper

const val SHOPPING_SUNDAYS_NOTIFICATION_IDS = "ShoppingSundayNotificationIds"
const val IDS_SEPARATOR = ","

class NotificationsRepository constructor(private val sharedPrefsWrapper: SharedPrefsWrapper) {

    suspend fun getShoppingSundayNotificationIds(): List<Int> {
        val rawStringData: String? = sharedPrefsWrapper.getString(SHOPPING_SUNDAYS_NOTIFICATION_IDS)
        return rawStringData?.split(IDS_SEPARATOR)?.map { it.toInt() } ?: listOf()
    }

    suspend fun addShoppingSundayNotificationId(id: Int) {
        val ids = getShoppingSundayNotificationIds().toMutableList()
        ids.add(id)
        sharedPrefsWrapper.saveString(SHOPPING_SUNDAYS_NOTIFICATION_IDS, ids.joinToString(IDS_SEPARATOR))
    }

    suspend fun removeAllShoppingSundayNotificationIds() {
        sharedPrefsWrapper.saveString(SHOPPING_SUNDAYS_NOTIFICATION_IDS, "")
    }

    suspend fun removeShoppingSundayNotificationId(id: Long) {
        val ids = getShoppingSundayNotificationIds().toMutableList()
        ids.remove(id)
        sharedPrefsWrapper.saveString(SHOPPING_SUNDAYS_NOTIFICATION_IDS, ids.joinToString(IDS_SEPARATOR))
    }
}