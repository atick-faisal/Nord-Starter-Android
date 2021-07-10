package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.services.background.DefaultWorker
import ai.andromeda.nordstarter.utils.DEFAULT_PERIODIC_WORK
import ai.andromeda.nordstarter.utils.DEFAULT_PERIODIC_WORK_INTERVAL
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    companion object {
        const val DEFAULT_CHANNEL_ID = "COM.ANDROMEDA.NORD"
        const val DEFAULT_CHANNEL_NAME = "Nord Notifications"
        const val DEFAULT_CHANNEL_DESC = "This is the default notification channel"
    }

    override fun onCreate() {
        super.onCreate()
        initializeLogger()

        // TODO: Initial Steps -> Modify Default Initializations
        createNotificationChannel()
        delayedInit()
    }

    private fun initializeLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("starting application ... ")
        }
    }

    private fun delayedInit() {
        CoroutineScope(Dispatchers.Default).launch {
            setUpPeriodicWorks()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DEFAULT_CHANNEL_ID,
                DEFAULT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                setShowBadge(false)
                enableLights(false)
                enableVibration(false)
                description = DEFAULT_CHANNEL_DESC
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setUpPeriodicWorks() {
        val defaultWorkRequest = PeriodicWorkRequestBuilder<DefaultWorker>(
            // TODO: Periodic Work -> Customize Repeating Interval
            DEFAULT_PERIODIC_WORK_INTERVAL,
            TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            DEFAULT_PERIODIC_WORK,
            ExistingPeriodicWorkPolicy.KEEP,
            defaultWorkRequest
        )
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}