package com.bahrlou.workingwithservices

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class ForegrndService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopForeground(true)
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startForeground(1005 , createNotification())

        return START_STICKY //automatically service will be started if android system closes this service
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun doYourJob() {
        for (i in 0..1000000) {
            Log.d("", "doYourJob: testMyService --> number $i")
        }
    }

    private fun createNotification(): Notification {

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notifChannel = NotificationChannel(
                "myChannel",
                "myAppChannel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notifChannel)
        }


        val notification = NotificationCompat.Builder(this, "myChannel")
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle("Service is running")
            .setContentText("it's ok ")
            .build()

        return notification


    }
}