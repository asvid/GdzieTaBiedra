package com.hedgehog.gdzietabiedra.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hedgehog.gdzietabiedra.appservice.notifications.ShoppingSundayNotificationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingsViewModel(
        val shoppingSundayNotificationService: ShoppingSundayNotificationService
) : ViewModel() {

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

    fun handleShoppingSundayNotificationDaysBeforeChange(newValue: Any?): Boolean {
        Timber.d("new days before is: $newValue")
        setNotificationsForSundays()
        return true
    }

    fun handleShoppingSundayNotificationTimeChange(newValue: Any?): Boolean {
        Timber.d("new time is: $newValue")
        setNotificationsForSundays()
        return true
    }
}