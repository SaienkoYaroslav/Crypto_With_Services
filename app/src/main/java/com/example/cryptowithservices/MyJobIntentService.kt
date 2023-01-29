package com.example.cryptowithservices

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        log("onHandleIntent")
        val page = intent.getIntExtra(PAGE, 0)
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
        Log.d("SERVICE_TAG", "MyJobIntentService: $str")
    }


    companion object {

        private const val PAGE = "page"
        private const val JOB_ID = 2

        fun enqueue(context: Context, page: Int) {
            enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newServiceIntent(context, page)
            )
        }

        private fun newServiceIntent(context: Context, page: Int): Intent {
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}

