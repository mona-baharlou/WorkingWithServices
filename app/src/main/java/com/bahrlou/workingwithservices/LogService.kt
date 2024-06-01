package com.bahrlou.workingwithservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class LogService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        doYourJob()

        return START_STICKY //automatically service will be started if android system closes this service

        //return START_NOT_STICKY ////service will be started if android system closes this service

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun doYourJob() {
        for (i in 0..1000000) {
            Log.d("", "doYourJob: testMyService --> number $i")
        }
    }
}