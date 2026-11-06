package com.dynamicisland.pro.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dynamicisland.pro.core.model.CallState

@Composable
fun EnhancedCallView(
    call: CallState,
    isExpanded: Boolean,
    onAccept: () -> Unit,
    onDecline: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isExpanded) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF00E676)))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = call.callerName.ifEmpty { "Call" },
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = "${call.durationSeconds / 60}:%02d".format(call.durationSeconds % 60),
                color = Color(0xFF00E676),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2C2D30)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color.LightGray)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = call.callerName.ifEmpty { "Incoming Call" },
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = if (call.isOngoing) "Active Call" else "Mobile Incoming",
                        color = Color(0xFFAAAAAA),
                        fontSize = 12.sp
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF3B30))
                        .clickable { onDecline() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.CallEnd, contentDescription = "Decline", tint = Color.White)
                }

                if (!call.isOngoing) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF34C759))
                            .clickable { onAccept() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = "Accept", tint = Color.White)
                    }
                }
            }
        }
    }
}\n