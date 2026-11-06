package com.dynamicisland.pro.core.model

import android.graphics.Bitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class IslandMode(val priority: Int) {
    IDLE(0),
    MEDIA(40),
    NOTIFICATION(60),
    BATTERY_CHARGING(75),
    TIMER_STOPWATCH(80),
    NAVIGATION(90),
    CALL(100)
}

data class MediaState(
    val title: String = "",
    val artist: String = "",
    val isPlaying: Boolean = false,
    val durationMs: Long = 0L,
    val currentPositionMs: Long = 0L,
    val dominantColor: Long = 0xFF2A2A2A,
    val currentLyric: String = ""
)

data class CallState(
    val callerName: String = "",
    val isOngoing: Boolean = false,
    val durationSeconds: Long = 0L,
    val isVoip: Boolean = false,
    val appName: String = "Phone"
)

data class NotificationState(
    val id: String = "",
    val appPackage: String = "",
    val appName: String = "",
    val title: String = "",
    val message: String = "",
    val isOtp: Boolean = false,
    val extractedOtp: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

data class IslandState(
    val mode: IslandMode = IslandMode.IDLE,
    val isExpanded: Boolean = false,
    val targetWidth: Dp = 110.dp,
    val targetHeight: Dp = 38.dp,
    val media: MediaState = MediaState(),
    val call: CallState = CallState(),
    val notification: NotificationState = NotificationState(),
    val batteryPercent: Int = 100,
    val isCharging: Boolean = false
)\n