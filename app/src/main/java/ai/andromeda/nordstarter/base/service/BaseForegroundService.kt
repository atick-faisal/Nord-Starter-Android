package ai.andromeda.nordstarter.base.service

import ai.andromeda.nordstarter.App
import ai.andromeda.nordstarter.R
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat

open class BaseForegroundService : Service() {

    companion object {
        const val PERSISTENT_NOTIFICATION_ID = 101
    }

    private lateinit var persistentNotification: Notification

    private val binder = object : Binder() {
        fun getInstance() = this@BaseForegroundService
    }

    open fun initService() {}
    open fun doInBackground() {}
    open fun setupNotification() = NotificationCompat
        .Builder(this, App.DEFAULT_CHANNEL_ID)
        .setContentTitle(getText(R.string.app_name))
        .build()

    override fun onCreate() {
        super.onCreate()
        initService()
        persistentNotification = setupNotification()
        startForeground(PERSISTENT_NOTIFICATION_ID, persistentNotification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        doInBackground()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }
}