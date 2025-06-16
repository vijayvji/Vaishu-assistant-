import com.vaishu.assistant.voice.VoiceAssistant
import com.vaishu.assistant.network.PCCommandSender

lateinit var assistant: VoiceAssistant

// Initialize assistant (set `true` for male voice)
assistant = VoiceAssistant(this, isMale = false) // false = female

// Example usage after voice command recognized:
val command = "shutdown"
assistant.speak("Okay, shutting down your computer now.")
PCCommandSender.sendCommandToPC(command)
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

