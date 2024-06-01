package com.bahrlou.workingwithservices

import android.app.Service
import android.content.Intent
import android.os.IBinder

class LogService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY //automatically service will be started if android system closes this service

        //return START_NOT_STICKY ////service will be started if android system closes this service

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}