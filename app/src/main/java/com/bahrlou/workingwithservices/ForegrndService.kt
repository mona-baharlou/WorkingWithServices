package com.bahrlou.workingwithservices

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat

class ForegrndService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        ServiceCompat.startForeground(
            this, 1005, createNotification(), if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
            } else {
                0
            }
        )
        /*} else {
            startForeground(
                1005, createNotification(),
                FOREGROUND_SERVICE_TYPE_SPECIAL_USE
            )
        }*/


        Thread.sleep(8000)
        //Log.d("myFore", "onStartCommand: thissss")
        stopSelf()

        return START_STICKY //automatically service will be started if android system closes this service
    }


    override fun onBind(intent: Intent): IBinder? {
        return null
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