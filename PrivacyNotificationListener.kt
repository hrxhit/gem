package com.dynamicisland.pro.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.dynamicisland.pro.core.manager.IslandManager
import com.dynamicisland.pro.core.privacy.PrivacyFilter

class PrivacyNotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if (sbn == null || sbn.isOngoing) return

        val extras = sbn.notification.extras ?: return
        val title = extras.getString("android.title") ?: ""
        val text = extras.getCharSequence("android.text")?.toString() ?: ""

        if (title.isBlank() && text.isBlank()) return

        val appName = try {
            val pm = applicationContext.packageManager
            pm.getApplicationLabel(pm.getApplicationInfo(sbn.packageName, 0)).toString()
        } catch (e: Exception) {
            sbn.packageName
        }

        val sanitizedNotification = PrivacyFilter.processNotification(
            pkg = sbn.packageName,
            appName = appName,
            rawTitle = title,
            rawText = text
        )

        IslandManager.updateNotification(sanitizedNotification)
    }
}\n