package com.hedgehog.gdzietabiedra.appservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.data.repository.NotificationsRepository
import timber.log.Timber
import java.util.*

const val SHOPPING_SUNDAY_CHANNEL_ID = "Biedra_Sundays"

class NotificationService(
        private val context: Context,
        private val notificationsRepository: NotificationsRepository,
) {

    init {
        createNotificationChannel()
    }

    fun showNotification(title: String, body: String) {
        // TODO
    }

    private fun createNotificationChannel() {
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(SHOPPING_SUNDAY_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = context.getSystemService(JobService.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    suspend fun scheduleShoppingSundayNotification(dateTime: Date, jobId: Int = Random().nextInt()) {
        val serviceComponent = ComponentName(context, ShoppingSundayNotificationJobService::class.java)
        val builder = JobInfo.Builder(jobId, serviceComponent)
        val jobTime = dateTime.time - System.currentTimeMillis()
        builder.setMinimumLatency(jobTime)
        val jobScheduler: JobScheduler = context.getSystemService(JobScheduler::class.java)
        notificationsRepository.addShoppingSundayNotificationId(jobId)
        jobScheduler.schedule(builder.build())
    }

    suspend fun cancelShoppingSundayNotifications() {
        val jobScheduler: JobScheduler = context.getSystemService(JobScheduler::class.java)
        notificationsRepository.getShoppingSundayNotificationIds().onEach {
            jobScheduler.cancel(it)
        }
        notificationsRepository.removeAllShoppingSundayNotificationIds()
    }
}

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

