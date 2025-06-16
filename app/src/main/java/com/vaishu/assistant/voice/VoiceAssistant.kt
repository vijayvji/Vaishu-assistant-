package com.vaishu.assistant.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class VoiceAssistant(context: Context, val isMale: Boolean = false) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val voice = if (isMale) {
                Locale.UK
            } else {
                Locale.US
            }
            tts?.language = voice
        }
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}

