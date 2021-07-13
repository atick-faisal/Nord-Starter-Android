package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.services.background.DefaultWorker
import ai.andromeda.nordstarter.utils.DEFAULT_PERIODIC_WORK
import ai.andromeda.nordstarter.utils.DEFAULT_PERIODIC_WORK_INTERVAL
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        initializeLogger()

        // TODO: Modify Default Initializations
        createNotificationChannel()
        setUpAppTheme()
        delayInit()
    }

    private fun initializeLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("starting application ... ")
        }
    }

    private fun setUpAppTheme() {
        var theme: String? = null

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                theme = getAppTheme()
            }

            when (theme) {
                "LIGHT" -> setDefaultNightMode(MODE_NIGHT_NO)
                "DARK" -> setDefaultNightMode(MODE_NIGHT_YES)
                else -> setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        Timber.d("app theme loaded --> [$theme]")
    }

    private fun getAppTheme(): String? {
        return sharedPreferences
            .getString(getString(R.string.app_theme_key), "SYSTEM")
    }

    private fun delayInit() {
        CoroutineScope(Dispatchers.Default).launch {
            setUpPeriodicWorks()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val defaultChannel = NotificationChannel(
                getString(R.string.default_channel_id),
                getString(R.string.default_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                setShowBadge(false)
                enableLights(false)
                enableVibration(false)
                description = getString(R.string.default_channel_desc)
            }

            val fcmChannel = NotificationChannel(
                getString(R.string.fcm_channel_id),
                getString(R.string.fcm_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(true)
                enableLights(true)
                enableVibration(false)
                description = getString(R.string.fcm_channel_desc)
            }

            notificationManager.apply {
                createNotificationChannel(defaultChannel)
                createNotificationChannel(fcmChannel)
            }
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