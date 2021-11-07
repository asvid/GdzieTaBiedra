package com.hedgehog.gdzietabiedra.data.repository

import com.hedgehog.gdzietabiedra.data.persistance.SharedPrefsWrapper
import com.hedgehog.gdzietabiedra.utils.toLocalTime
import timber.log.Timber
import java.time.LocalTime

const val SHOPPING_SUNDAYS_NOTIFICATION_IDS = "ShoppingSundayNotificationIds"
const val IDS_SEPARATOR = ","
const val DEFAULT_DAYS_BEFORE = 2
const val DEFAULT_NOTIFICATION_ON = false
const val DEFAULT_NOTIFICATION_HOUR = 11
const val DEFAULT_NOTIFICATION_MINUTE = 11

class NotificationsRepository constructor(private val sharedPrefsWrapper: SharedPrefsWrapper) {

    fun getNotificationTime(): LocalTime {
        val timeString = sharedPrefsWrapper.getString("shopping_sunday_notification_time")
        timeString?.let {
            return timeString.toLocalTime()
        }
        return LocalTime.of(DEFAULT_NOTIFICATION_HOUR, DEFAULT_NOTIFICATION_MINUTE)
    }

    fun getNotificationDays(): Int {
        return sharedPrefsWrapper.getInt("shopping_sunday_notification_days_before")
            ?: DEFAULT_DAYS_BEFORE
    }

    fun getNotificationOn(): Boolean {
        return sharedPrefsWrapper.getBoolean("shopping_sunday_notification")
            ?: DEFAULT_NOTIFICATION_ON
    }

    fun setNotificationOn(value: Boolean) {
        return sharedPrefsWrapper.saveBoolean("shopping_sunday_notification", value)
    }

    fun addSundayNotificationId(jobId: Int) {
        Timber.d("adding sunday notification with ID: $jobId")
        getSundayNotificationIds().toMutableList().let {
            it.add(jobId)
            sharedPrefsWrapper.saveString(
                SHOPPING_SUNDAYS_NOTIFICATION_IDS,
                it.joinToString(IDS_SEPARATOR)
            )
        }
    }

    fun getSundayNotificationIds(): List<Int> {
        return sharedPrefsWrapper.getString(SHOPPING_SUNDAYS_NOTIFICATION_IDS)
            ?.split(IDS_SEPARATOR)
            ?.filter { it.isNotEmpty() }
            ?.map {
                it.toInt()
            }
            ?: listOf()
    }

    fun removeSundayNotificationIds() {
        sharedPrefsWrapper.remove(SHOPPING_SUNDAYS_NOTIFICATION_IDS)
    }

    fun setNotificationDays(newValue: Int) {
        sharedPrefsWrapper.saveInt("shopping_sunday_notification_days_before", newValue)
    }

    fun setNotificationTime(newValue: LocalTime) {
        val selectedTime = "${String.format("%02d", newValue.hour)}:${
            String.format(
                "%02d",
                newValue.minute
            )
        }"
        sharedPrefsWrapper.saveString("shopping_sunday_notification_time", selectedTime)
    }
}