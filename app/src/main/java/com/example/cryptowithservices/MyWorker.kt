package com.example.cryptowithservices

import android.content.Context
import android.util.Log
import androidx.work.*

class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0..5) {
            Thread.sleep(1000)
            log("Timer: $i, page $page")
        }
        return Result.success()
    }

    private fun log(str: String) {
        Log.d("SERVICE_TAG", "MyWorker: $str")
    }

    companion object {

        private const val PAGE = "page"
        const val WORK_NAME = "worker"

        fun makeRequest(page: Int) : OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(PAGE to page))
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints() : Constraints {
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }

    }

}