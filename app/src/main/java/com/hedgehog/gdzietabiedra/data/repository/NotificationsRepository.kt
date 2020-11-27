package com.hedgehog.gdzietabiedra.data.repository

import com.hedgehog.gdzietabiedra.data.persistance.SharedPrefsWrapper
import timber.log.Timber

const val SHOPPING_SUNDAYS_NOTIFICATION_IDS = "ShoppingSundayNotificationIds"
const val IDS_SEPARATOR = ","

class NotificationsRepository constructor(private val sharedPrefsWrapper: SharedPrefsWrapper) {

    suspend fun getNotificationTime(): Long? {
        return sharedPrefsWrapper.getLong("shopping_sunday_notification_time")
    }

    suspend fun getNotificationDays(): Int? {
        return sharedPrefsWrapper.getInt("shopping_sunday_notification_days")
    }

    suspend fun addSundayNotificationId(jobId: Int) {
        Timber.d("adding sunday notification with ID: $jobId")
        getSundayNotificationIds().toMutableList().let {
            it.add(jobId)
            sharedPrefsWrapper.saveString(
                    SHOPPING_SUNDAYS_NOTIFICATION_IDS,
                    it.joinToString(",")
            )
        }
    }

    suspend fun getSundayNotificationIds(): List<Int> {
        return sharedPrefsWrapper.getString(SHOPPING_SUNDAYS_NOTIFICATION_IDS)
                ?.split(",")
                ?.filter { it.isNotEmpty() }
                ?.map {
                    it.toInt()
                }
                ?: listOf()
    }

    suspend fun removeSundayNotificationIds() {
        sharedPrefsWrapper.remove(SHOPPING_SUNDAYS_NOTIFICATION_IDS)
    }
}