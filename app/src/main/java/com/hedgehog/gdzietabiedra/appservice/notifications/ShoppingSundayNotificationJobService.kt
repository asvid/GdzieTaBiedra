package com.hedgehog.gdzietabiedra.appservice.notifications

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.github.asvid.biedra.domain.SundayShopping
import com.hedgehog.gdzietabiedra.MainActivity
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.toLocalFormat
import timber.log.Timber

class ShoppingSundayNotificationJobService : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        Timber.d("starting a job")
        val nextShoppingSunday = SundayShopping.getNextShoppingSunday().toLocalFormat(applicationContext)
        val resultIntent = Intent(this, MainActivity::class.java)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this, SHOPPING_SUNDAY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_ladybug)
                .setContentTitle(getString(R.string.shopping_sunday_notification_title))
                .setContentText(getString(R.string.shopping_sunday_notification_content, nextShoppingSunday))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultPendingIntent)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
        val jobScheduler: JobScheduler = applicationContext.getSystemService(JobScheduler::class.java)
        jobScheduler.cancel(params.jobId)
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Timber.d("stopping a job")
        return true
    }
}
