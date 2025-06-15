package com.vaishu.assistant

import android.util.Log
import java.io.DataOutputStream

object RootCommandExecutor {

    fun execute(command: String) {
        try {
            val process = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(process.outputStream)
            outputStream.writeBytes("$command\n")
            outputStream.writeBytes("exit\n")
            outputStream.flush()
            process.waitFor()
            Log.d("RootCommand", "Executed: $command")
        } catch (e: Exception) {
            Log.e("RootCommand", "Failed to execute: $command", e)
        }
    }
}

