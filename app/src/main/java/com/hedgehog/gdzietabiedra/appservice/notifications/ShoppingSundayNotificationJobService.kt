package com.hedgehog.gdzietabiedra.appservice.notifications

import android.app.job.JobParameters
import android.app.job.JobService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.appservice.SHOPPING_SUNDAY_CHANNEL_ID
import timber.log.Timber

class ShoppingSundayNotificationJobService : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        Timber.d("starting a job")
        val builder = NotificationCompat.Builder(this, SHOPPING_SUNDAY_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle("Biedra")
                .setContentText("Niedziela handlowa!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Timber.d("stopping a job")
        return true
    }
}
