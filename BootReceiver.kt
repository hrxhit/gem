package com.dynamicisland.pro.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dynamicisland.pro.service.IslandOverlayService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED && context != null) {
            val serviceIntent = Intent(context, IslandOverlayService::class.java)
            context.startService(serviceIntent)
        }
    }
}\n