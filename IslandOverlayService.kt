package com.dynamicisland.pro.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.dynamicisland.pro.core.manager.IslandManager
import com.dynamicisland.pro.core.ui.components.DynamicIslandRoot
import com.dynamicisland.pro.service.lifecycle.OverlayLifecycleOwner

class IslandOverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var overlayView: ComposeView? = null
    private val lifecycleOwner = OverlayLifecycleOwner()

    override fun onCreate() {
        super.onCreate()
        lifecycleOwner.onCreate()
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        initOverlay()
    }

    private fun initOverlay() {
        val layoutFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutFlag,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            y = 12
        }

        overlayView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(lifecycleOwner)
            setViewTreeViewModelStoreOwner(lifecycleOwner)
            setViewTreeSavedStateRegistryOwner(lifecycleOwner)

            setContent {
                val state by IslandManager.islandState.collectAsState()
                DynamicIslandRoot(islandState = state)
            }
        }

        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        overlayView?.let { windowManager.removeView(it) }
        lifecycleOwner.onDestroy()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}\n