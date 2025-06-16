
package com.vaishu.assistant.network

import java.io.PrintWriter
import java.net.Socket

object PCCommandSender {
    fun sendCommandToPC(command: String, ip: String = "192.168.1.100", port: Int = 5050) {
        Thread {
            try {
                val socket = Socket(ip, port)
                val out = PrintWriter(socket.getOutputStream(), true)
                out.println(command)
                out.close()
                socket.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}
