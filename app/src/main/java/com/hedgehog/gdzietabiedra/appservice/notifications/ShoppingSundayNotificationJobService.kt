package com.hedgehog.gdzietabiedra.appservice.notifications

import android.app.job.JobParameters
import android.app.job.JobService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.github.asvid.biedra.domain.SundayShopping
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.print
import timber.log.Timber
import java.text.DateFormat

class ShoppingSundayNotificationJobService : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        Timber.d("starting a job")
        val nextShoppingSunday = SundayShopping.getNextShoppingSunday().print(applicationContext)
        val builder = NotificationCompat.Builder(this, SHOPPING_SUNDAY_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle(getString(R.string.shopping_sunday_notification_title))
                .setContentText(getString(R.string.shopping_sunday_notification_content, nextShoppingSunday))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }

        // todo: schedule next notification

        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Timber.d("stopping a job")
        return true
    }
}
