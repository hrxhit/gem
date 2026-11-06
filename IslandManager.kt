package com.dynamicisland.pro.core.manager

import androidx.compose.ui.unit.dp
import com.dynamicisland.pro.core.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object IslandManager {

    private val _islandState = MutableStateFlow(IslandState())
    val islandState: StateFlow<IslandState> = _islandState.asStateFlow()

    fun updateMedia(mediaState: MediaState) {
        _islandState.update { current ->
            if (mediaState.isPlaying) {
                current.copy(
                    mode = IslandMode.MEDIA,
                    media = mediaState,
                    targetWidth = if (current.isExpanded) 340.dp else 180.dp,
                    targetHeight = if (current.isExpanded) 140.dp else 38.dp
                )
            } else if (current.mode == IslandMode.MEDIA) {
                current.copy(mode = IslandMode.IDLE, targetWidth = 110.dp, targetHeight = 38.dp)
            } else {
                current.copy(media = mediaState)
            }
        }
    }

    fun updateCall(callState: CallState) {
        _islandState.update { current ->
            current.copy(
                mode = IslandMode.CALL,
                call = callState,
                targetWidth = if (current.isExpanded) 360.dp else 210.dp,
                targetHeight = if (current.isExpanded) 110.dp else 42.dp
            )
        }
    }

    fun updateNotification(notificationState: NotificationState) {
        _islandState.update { current ->
            if (current.mode.priority > IslandMode.NOTIFICATION.priority) return@update current
            current.copy(
                mode = IslandMode.NOTIFICATION,
                notification = notificationState,
                isExpanded = true,
                targetWidth = 350.dp,
                targetHeight = if (notificationState.isOtp) 84.dp else 78.dp
            )
        }
    }

    fun toggleExpand() {
        _islandState.update { current ->
            val newExpanded = !current.isExpanded
            val newWidth = when (current.mode) {
                IslandMode.MEDIA -> if (newExpanded) 340.dp else 180.dp
                IslandMode.CALL -> if (newExpanded) 360.dp else 210.dp
                IslandMode.NOTIFICATION -> if (newExpanded) 350.dp else 190.dp
                else -> 110.dp
            }
            val newHeight = when (current.mode) {
                IslandMode.MEDIA -> if (newExpanded) 140.dp else 38.dp
                IslandMode.CALL -> if (newExpanded) 110.dp else 42.dp
                IslandMode.NOTIFICATION -> if (newExpanded) 80.dp else 38.dp
                else -> 38.dp
            }
            current.copy(isExpanded = newExpanded, targetWidth = newWidth, targetHeight = newHeight)
        }
    }

    fun dismissCurrent() {
        _islandState.update { current ->
            current.copy(
                mode = IslandMode.IDLE,
                isExpanded = false,
                targetWidth = 110.dp,
                targetHeight = 38.dp
            )
        }
    }
}\n