package com.vaishu.assistant

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.*

class VoiceCommandService : Service() {

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent

    override fun onCreate() {
        super.onCreate()

        startForeground(1, createNotification())

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                matches?.firstOrNull()?.let { handleCommand(it.lowercase(Locale.getDefault())) }
                restartListening()
            }

            override fun onError(error: Int) {
                Log.e("VoiceCommand", "Recognition error: $error")
                restartListening()
            }

            override fun onBeginningOfSpeech() {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onRmsChanged(rmsdB: Float) {}
        })

        restartListening()
    }

    private fun restartListening() {
        speechRecognizer.stopListening()
        speechRecognizer.startListening(recognizerIntent)
    }

    private fun handleCommand(command: String) {
        if (!command.contains("vaishu")) return

        when {
            command.contains("reboot") -> RootCommandExecutor.execute("reboot")
            command.contains("wifi on") -> RootCommandExecutor.execute("svc wifi enable")
            command.contains("wifi off") -> RootCommandExecutor.execute("svc wifi disable")
            command.contains("camera") -> {
                val launchIntent = packageManager.getLaunchIntentForPackage("com.android.camera")
                launchIntent?.let { startActivity(it) }
            }
            else -> Log.d("VoiceCommand", "Unrecognized command: $command")
        }
    }

    private fun createNotification(): Notification {
        val channelId = "vaishu_assistant_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Voice Assistant", NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Vaishu Assistant Listening…")
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

