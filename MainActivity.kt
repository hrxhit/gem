package com.dynamicisland.pro.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dynamicisland.pro.core.manager.IslandManager
import com.dynamicisland.pro.core.model.CallState
import com.dynamicisland.pro.core.model.MediaState
import com.dynamicisland.pro.core.model.NotificationState
import com.dynamicisland.pro.service.IslandOverlayService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                SettingsDashboard(
                    onRequestOverlayPermission = {
                        val intent = Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:$packageName")
                        )
                        startActivity(intent)
                    },
                    onRequestNotificationPermission = {
                        startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
                    },
                    onStartService = {
                        startService(Intent(this, IslandOverlayService::class.java))
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsDashboard(
    onRequestOverlayPermission: () -> Unit,
    onRequestNotificationPermission: () -> Unit,
    onStartService: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1012))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Smart Island Reborn",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "100% Offline • iOS & One UI 7 Grade Physics • Zero Leak",
            fontSize = 13.sp,
            color = Color(0xFF00FF66)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF1B1C20))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Permissions & Activation", fontWeight = FontWeight.SemiBold, color = Color.White)

            Button(
                onClick = onRequestOverlayPermission,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2979FF))
            ) {
                Text("1. Grant Overlay Permission")
            }

            Button(
                onClick = onRequestNotificationPermission,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2979FF))
            ) {
                Text("2. Grant Notification Access")
            }

            Button(
                onClick = onStartService,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676))
            ) {
                Text("3. Launch Island Service", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF1B1C20))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Interactive Visual Playground", fontWeight = FontWeight.SemiBold, color = Color.White)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        IslandManager.updateMedia(
                            MediaState(
                                title = "Starboy",
                                artist = "The Weeknd ft. Daft Punk",
                                isPlaying = true,
                                dominantColor = 0xFF8E24AA
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Test Music", fontSize = 11.sp)
                }

                Button(
                    onClick = {
                        IslandManager.updateCall(
                            CallState(
                                callerName = "Aryan Sharma",
                                isOngoing = false
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Test Call", fontSize = 11.sp)
                }

                Button(
                    onClick = {
                        IslandManager.updateNotification(
                            NotificationState(
                                title = "HDFC Bank Alert",
                                message = "Your OTP is 849201 for INR 4,200 txn",
                                isOtp = true,
                                extractedOtp = "849201"
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Test OTP", fontSize = 11.sp)
                }
            }
        }
    }
}\n