package com.vaishu.assistant.applock

import android.app.Service
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder

class AppLockService : Service() {

    private val handler = Handler()
    private lateinit var lockedAppsManager: LockedAppsManager

    override fun onCreate() {
        super.onCreate()
        lockedAppsManager = LockedAppsManager(this)
        handler.post(checkRunnable)
    }

    private val checkRunnable = object : Runnable {
        override fun run() {
            val topApp = getForegroundApp()
            if (lockedAppsManager.isLocked(topApp)) {
                val intent = Intent(this@AppLockService, PinEntryActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            handler.postDelayed(this, 1000)
        }
    }

    private fun getForegroundApp(): String? {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val beginTime = endTime - 10000
        val stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime)
        return stats.maxByOrNull { it.lastTimeUsed }?.packageName
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

