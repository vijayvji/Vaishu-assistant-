package com.vaishu.assistant.applock

import android.content.Context

class LockedAppsManager(context: Context) {

    private val prefs = context.getSharedPreferences("LockedApps", Context.MODE_PRIVATE)

    fun isLocked(packageName: String?): Boolean {
        return prefs.getBoolean(packageName ?: "", false)
    }

    fun lockApp(packageName: String) {
        prefs.edit().putBoolean(packageName, true).apply()
    }

    fun unlockApp(packageName: String) {
        prefs.edit().remove(packageName).apply()
    }
}

