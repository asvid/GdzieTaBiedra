package com.hedgehog.gdzietabiedra.appservice.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.github.asvid.biedra.domain.SundayShopping
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.data.repository.NotificationsRepository
import org.joda.time.Duration
import org.joda.time.LocalDateTime
import timber.log.Timber
import kotlin.random.Random

const val SHOPPING_SUNDAY_CHANNEL_ID = "Biedra_Sundays"

class ShoppingSundayNotificationService(
        private val context: Context,
        private val notificationsRepository: NotificationsRepository,
) {

    init {
        Timber.d("Starting notification service")
        createNotificationChannel()
        context.getSystemService(JobScheduler::class.java).allPendingJobs.forEach {
            Timber.d("Pending job: $it")
        }
    }

    private fun createNotificationChannel() {
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            val notificationManager: NotificationManager = context.getSystemService(JobService.NOTIFICATION_SERVICE) as NotificationManager
            if (notificationManager.getNotificationChannel(SHOPPING_SUNDAY_CHANNEL_ID) == null) {
                Timber.d("Creating new notification channel")
                val name = context.getString(R.string.channel_name)
                val descriptionText = context.getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(SHOPPING_SUNDAY_CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                notificationManager.createNotificationChannel(channel)
            } else {
                Timber.d("Notification channel was already created")
            }
        }
    }

    suspend fun scheduleAllShoppingSundayNotifications() {
        Timber.d("scheduling all new notifications")

        val serviceComponent = ComponentName(context, ShoppingSundayNotificationJobService::class.java)
        val nextShoppingSundays = SundayShopping.getAllRemainingSundays()
        val notificationDays = notificationsRepository.getNotificationDays()
        val notificationTime = notificationsRepository.getNotificationTime()
        nextShoppingSundays.forEach { localDate ->
            val jobId = Random.nextInt()
            val builder = JobInfo.Builder(jobId, serviceComponent)
            val jobTime: Long = SundayShopping.calculateJobTime(localDate, notificationDays, notificationTime)
            builder.setMinimumLatency(jobTime)
            val jobScheduler: JobScheduler = context.getSystemService(JobScheduler::class.java)
            builder.build().let { jobInfo ->
                jobScheduler.schedule(jobInfo)
                notificationsRepository.addSundayNotificationId(jobInfo.id)
                Timber.d("setting new notification for date/time: ${LocalDateTime().plus(Duration.millis(jobTime))}")
            }
        }
    }

    suspend fun cancelShoppingSundayNotifications() {
        Timber.d("canceling notifications")
        val jobScheduler: JobScheduler = context.getSystemService(JobScheduler::class.java)
        notificationsRepository.getSundayNotificationIds().forEach {
            jobScheduler.cancel(it)
            Timber.d("canceling notification with ID: $it")
        }
        notificationsRepository.removeSundayNotificationIds()
        Timber.d("canceling notifications DONE")
    }
}