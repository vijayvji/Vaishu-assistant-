package com.vaishu.assistant.applock

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.vaishu.assistant.R

class PinEntryActivity : Activity() {

    private val correctPin = "1122"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_entry)

        val pinInput = findViewById<EditText>(R.id.pinInput)
        val unlockBtn = findViewById<Button>(R.id.unlockButton)

        unlockBtn.setOnClickListener {
            if (pinInput.text.toString() == correctPin) {
                finish()
            } else {
                pinInput.error = "Wrong PIN"
            }
        }
    }
}
