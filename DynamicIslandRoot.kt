package com.dynamicisland.pro.core.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.dynamicisland.pro.core.manager.IslandManager
import com.dynamicisland.pro.core.model.IslandMode
import com.dynamicisland.pro.core.model.IslandState
import com.dynamicisland.pro.core.ui.geometry.SquircleShape
import com.dynamicisland.pro.core.ui.theme.IslandThemePreset

@Composable
fun DynamicIslandRoot(
    islandState: IslandState,
    theme: IslandThemePreset = IslandThemePreset.OLED_OBSIDIAN,
    modifier: Modifier = Modifier
) {
    val animatedWidth by animateDpAsState(
        targetValue = islandState.targetWidth,
        animationSpec = spring(dampingRatio = 0.72f, stiffness = 320f),
        label = "IslandWidth"
    )
    val animatedHeight by animateDpAsState(
        targetValue = islandState.targetHeight,
        animationSpec = spring(dampingRatio = 0.76f, stiffness = 340f),
        label = "IslandHeight"
    )

    Box(
        modifier = modifier
            .size(width = animatedWidth, height = animatedHeight)
            .graphicsLayer {
                shape = SquircleShape(cornerRadius = 22.dp, smoothness = 0.85f)
                clip = true
                shadowElevation = if (islandState.isExpanded) 18f else 0f
            }
            .background(theme.backgroundColor.copy(alpha = theme.backgroundAlpha))
            .border(
                width = 0.5.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        theme.borderColor.copy(alpha = theme.borderAlpha),
                        Color.Transparent
                    )
                ),
                shape = SquircleShape(cornerRadius = 22.dp, smoothness = 0.85f)
            )
            .clickable { IslandManager.toggleExpand() }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount > 25f || dragAmount < -25f) {
                        IslandManager.dismissCurrent()
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = islandState.mode,
            animationSpec = tween(durationMillis = 180),
            label = "IslandCrossfade"
        ) { mode ->
            when (mode) {
                IslandMode.IDLE -> Box(modifier = Modifier.size(24.dp))
                IslandMode.MEDIA -> {
                    EnhancedMediaView(
                        media = islandState.media,
                        isExpanded = islandState.isExpanded,
                        onTogglePlay = { /* Toggle session */ },
                        onNext = { /* Next track */ },
                        onPrev = { /* Prev track */ }
                    )
                }
                IslandMode.CALL -> {
                    EnhancedCallView(
                        call = islandState.call,
                        isExpanded = islandState.isExpanded,
                        onAccept = { /* Answer */ },
                        onDecline = { IslandManager.dismissCurrent() }
                    )
                }
                IslandMode.NOTIFICATION -> {
                    MaskedNotificationView(
                        notification = islandState.notification,
                        onCopyOtp = { IslandManager.dismissCurrent() },
                        onDismiss = { IslandManager.dismissCurrent() }
                    )
                }
                else -> Box(modifier = Modifier.size(24.dp))
            }
        }
    }
}\n