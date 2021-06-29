package ai.andromeda.nordstarter

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    companion object {
        const val DEFAULT_CHANNEL_ID = "COM.ANDROMEDA.NORD"
        const val DEFAULT_CHANNEL_NAME = "Nord Notifications"
        const val DEFAULT_CHANNEL_DESC = "This is the default notification channel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
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
}