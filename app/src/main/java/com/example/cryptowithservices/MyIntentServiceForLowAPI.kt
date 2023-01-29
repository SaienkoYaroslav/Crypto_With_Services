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

class MyIntentServiceForLowAPI : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true)
    }

    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        val page = p0?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0..5) {
            Thread.sleep(1000)
            log("Timer: $i, page $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(str: String) {
        Log.d("SERVICE_TAG", "MyIntentServiceForLowAPI: $str")
    }


    companion object {

        private const val PAGE = "page"
        private const val NAME = "MyIntentService"

        fun newServiceIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentServiceForLowAPI::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}

