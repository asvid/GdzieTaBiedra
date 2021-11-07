package com.hedgehog.gdzietabiedra.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hedgehog.gdzietabiedra.appservice.notifications.ShoppingSundayNotificationService
import com.hedgehog.gdzietabiedra.data.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalTime

class SettingsViewModel(
    val shoppingSundayNotificationService: ShoppingSundayNotificationService,
    val notificationsRepository: NotificationsRepository,
) : ViewModel() {

    //TODO: read preferences and set them in view

    fun handleShoppingSundayNotificatonChange(newValue: Any): Boolean {
        if (newValue as Boolean) {
            // turn on notifications, check saved days before and time
            setNotificationsForSundays()
        } else {
            // turn off notifications, clear jobs and saved IDs
            viewModelScope.launch(Dispatchers.IO) {
                shoppingSundayNotificationService.cancelShoppingSundayNotifications()
            }
        }
        return true
    }

    private fun setNotificationsForSundays() {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingSundayNotificationService.cancelShoppingSundayNotifications()
            shoppingSundayNotificationService.scheduleAllShoppingSundayNotifications()
        }
    }

    fun handleShoppingSundayNotificationDaysBeforeChange(newValue: Int): Boolean {
        Timber.d("new days before is: $newValue")
        notificationsRepository.setNotificationDays(newValue)
        setNotificationsForSundays()
        return true
    }

    fun handleShoppingSundayNotificationTimeChange(newValue: LocalTime): Boolean {
        Timber.d("new time is: $newValue")
        notificationsRepository.setNotificationTime(newValue)
        setNotificationsForSundays()
        return true
    }

    fun getNotificationOn(): Boolean {
        return notificationsRepository.getNotificationOn()
    }

    fun getNotificationDays(): Int {
        return notificationsRepository.getNotificationDays()
    }

    fun getNotificationTime(): LocalTime {
        return notificationsRepository.getNotificationTime()
    }
}