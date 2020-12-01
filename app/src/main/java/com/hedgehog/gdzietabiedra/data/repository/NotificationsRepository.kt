package com.hedgehog.gdzietabiedra.data.repository

import com.hedgehog.gdzietabiedra.data.persistance.SharedPrefsWrapper
import com.hedgehog.gdzietabiedra.utils.toLocalTime
import timber.log.Timber
import java.time.LocalTime

const val SHOPPING_SUNDAYS_NOTIFICATION_IDS = "ShoppingSundayNotificationIds"
const val IDS_SEPARATOR = ","
const val DEFAULT_DAYS_BEFORE = 2
const val DEFAULT_NOTIFICATION_HOUR = 11
const val DEFAULT_NOTIFICATION_MINUTE = 11

class NotificationsRepository constructor(private val sharedPrefsWrapper: SharedPrefsWrapper) {

    suspend fun getNotificationTime(): LocalTime {
        val timeString = sharedPrefsWrapper.getString("shopping_sunday_notification_time")
        timeString?.let {
            return timeString.toLocalTime()
        }
        return LocalTime.of(DEFAULT_NOTIFICATION_HOUR, DEFAULT_NOTIFICATION_MINUTE)
    }

    suspend fun getNotificationDays(): Int {
        return sharedPrefsWrapper.getInt("shopping_sunday_notification_days_before")
                ?: DEFAULT_DAYS_BEFORE
    }

    suspend fun addSundayNotificationId(jobId: Int) {
        Timber.d("adding sunday notification with ID: $jobId")
        getSundayNotificationIds().toMutableList().let {
            it.add(jobId)
            sharedPrefsWrapper.saveString(
                    SHOPPING_SUNDAYS_NOTIFICATION_IDS,
                    it.joinToString(IDS_SEPARATOR)
            )
        }
    }

    suspend fun getSundayNotificationIds(): List<Int> {
        return sharedPrefsWrapper.getString(SHOPPING_SUNDAYS_NOTIFICATION_IDS)
                ?.split(IDS_SEPARATOR)
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