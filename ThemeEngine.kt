package com.dynamicisland.pro.core.ui.theme

import androidx.compose.ui.graphics.Color

enum class IslandThemePreset(
    val displayName: String,
    val backgroundColor: Color,
    val backgroundAlpha: Float,
    val borderColor: Color,
    val borderAlpha: Float,
    val textColor: Color,
    val accentColor: Color
) {
    OLED_OBSIDIAN(
        displayName = "OLED Obsidian",
        backgroundColor = Color(0xFF000000),
        backgroundAlpha = 1.0f,
        borderColor = Color(0xFF333333),
        borderAlpha = 0.35f,
        textColor = Color(0xFFFFFFFF),
        accentColor = Color(0xFF00E5FF)
    ),
    NEO_GLASSMORPHISM(
        displayName = "Neo-Glassmorphic Frost",
        backgroundColor = Color(0xFF141414),
        backgroundAlpha = 0.68f,
        borderColor = Color(0xFFFFFFFF),
        borderAlpha = 0.22f,
        textColor = Color(0xFFF0F0F0),
        accentColor = Color(0xFFB388FF)
    ),
    SAMSUNG_ONEUI_7(
        displayName = "One UI 7 Now Pill",
        backgroundColor = Color(0xFF18191B),
        backgroundAlpha = 0.94f,
        borderColor = Color(0xFF3C3E44),
        borderAlpha = 0.40f,
        textColor = Color(0xFFE8EAED),
        accentColor = Color(0xFF2979FF)
    ),
    IOS_18_TITANIUM(
        displayName = "iOS 18 Dark Titanium",
        backgroundColor = Color(0xFF0C0D0E),
        backgroundAlpha = 0.98f,
        borderColor = Color(0xFF2C2D30),
        borderAlpha = 0.50f,
        textColor = Color(0xFFFFFFFF),
        accentColor = Color(0xFFFF5252)
    ),
    CYBER_DOT_MATRIX(
        displayName = "Cyberpunk Dot Matrix",
        backgroundColor = Color(0xFF050505),
        backgroundAlpha = 1.0f,
        borderColor = Color(0xFF00FF66),
        borderAlpha = 0.60f,
        textColor = Color(0xFF00FF66),
        accentColor = Color(0xFF00FF66)
    )
}\n