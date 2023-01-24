package com.example.cryptowithservices

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MyIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, getNotification())
    }

    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        for (i in 0..20) {
            Thread.sleep(1000)
            log("Timer: $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(str: String) {
        Log.d("SERVICE_TAG", "MyForegroundService: $str")
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foregraund notification")
            .setContentText("text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    companion object {

        private const val CHANNEL_ID = "ID"
        private const val CHANNEL_NAME = "Foreground notification"
        private const val NOTIFICATION_ID = 1
        private const val NAME = "MyIntentService"

        fun newServiceIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}

