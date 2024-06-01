package com.bahrlou.workingwithservices

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissionsLauncher.launch(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS)
            )
        }

       // startMyBackgroundService()
    }

    private fun startMyBackgroundService() {
        val intent = Intent(this, LogService::class.java)
        startService(intent) //background Service
    }


    private fun startMyForegroundService() {
        val intent = Intent(this, ForegrndService::class.java)
        startService(intent) //background Service
    }

    val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value })
                startMyForegroundService()
            else Toast.makeText(this, "not accepted all the permissions", Toast.LENGTH_LONG).show()

        }
}