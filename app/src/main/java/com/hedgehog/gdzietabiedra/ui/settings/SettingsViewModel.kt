package com.hedgehog.gdzietabiedra.ui.settings

import androidx.lifecycle.ViewModel
import timber.log.Timber

class SettingsViewModel : ViewModel() {

    fun handleShoppingSundayNotificatonChange(newValue: Any): Boolean {
        if (newValue as Boolean) {
            // turn on notifications, check
        } else {
            // turn off notifications, clear jobs and saved IDs
        }
        return true
    }

    fun handleShoppingSundayNotificationDaysBeforeChange(newValue: Any?): Boolean {
        Timber.d("new days before is: $newValue")
        // update notifications - remove old jobs and create new ones
        return true
    }

    fun handleShoppingSundayNotificationTimeChange(newValue: Any?): Boolean {
        Timber.d("new time is: $newValue")
        // update notifications - remove old jobs and create new ones
        return true
    }
}