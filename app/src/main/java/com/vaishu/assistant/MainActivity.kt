package com.vaishu.assistant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start the voice command service
        val serviceIntent = Intent(this, VoiceCommandService::class.java)
        startForegroundService(serviceIntent)
        val intent = Intent(this, AppLockService::class.java)
startService(intent)


        // Optional: Simple UI
        finish() // No UI for now — exits immediately
    }
}

