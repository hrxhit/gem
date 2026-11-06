package com.dynamicisland.pro.core.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dynamicisland.pro.core.model.MediaState

@Composable
fun EnhancedMediaView(
    media: MediaState,
    isExpanded: Boolean,
    onTogglePlay: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isExpanded) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(media.dominantColor)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                )
            }

            AudioWaveVisualizer(isPlaying = media.isPlaying)
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(media.dominantColor).copy(alpha = 0.45f),
                            Color.Transparent
                        ),
                        radius = 400f
                    )
                )
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(media.dominantColor))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = media.title.ifEmpty { "Playing Music" },
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = media.artist.ifEmpty { "Audio Track" },
                        color = Color(0xFFAAAAAA),
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }

                AudioWaveVisualizer(isPlaying = media.isPlaying)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF333333))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.42f)
                        .fillMaxHeight()
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = "Previous",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp).clickable { onPrev() }
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { onTogglePlay() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (media.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Play/Pause",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp).clickable { onNext() }
                )
            }
        }
    }
}

@Composable
fun AudioWaveVisualizer(isPlaying: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "WaveTrans")
    val h1 by infiniteTransition.animateFloat(
        initialValue = 6f,
        targetValue = 18f,
        animationSpec = infiniteRepeatable(tween(350, easing = LinearEasing), RepeatMode.Reverse),
        label = "h1"
    )
    val h2 by infiniteTransition.animateFloat(
        initialValue = 16f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(tween(280, easing = LinearEasing), RepeatMode.Reverse),
        label = "h2"
    )
    val h3 by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(tween(420, easing = LinearEasing), RepeatMode.Reverse),
        label = "h3"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.5.dp)
    ) {
        Box(modifier = Modifier.width(3.dp).height(if (isPlaying) h1.dp else 6.dp).clip(CircleShape).background(Color(0xFF00FF66)))
        Box(modifier = Modifier.width(3.dp).height(if (isPlaying) h2.dp else 10.dp).clip(CircleShape).background(Color(0xFF00FF66)))
        Box(modifier = Modifier.width(3.dp).height(if (isPlaying) h3.dp else 6.dp).clip(CircleShape).background(Color(0xFF00FF66)))
    }
}\n