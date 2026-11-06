package com.dynamicisland.pro.core.ui.geometry

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.min

/**
 * iOS G2 Continuous Curvature Superellipse (Squircle)
 * Replaces standard Android RoundedCornerShape with true Apple-grade Bézier continuity.
 */
class SquircleShape(
    private val cornerRadius: Dp = 22.dp,
    private val smoothness: Float = 0.85f
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radiusPx = with(density) { cornerRadius.toPx() }
        val maxRadius = min(size.width, size.height) / 2f
        val r = min(radiusPx, maxRadius)

        val path = Path().apply {
            reset()
            val w = size.width
            val h = size.height

            val c = r * (1f - smoothness * 0.45f)

            moveTo(r, 0f)
            lineTo(w - r, 0f)
            cubicTo(w - c, 0f, w, c, w, r)

            lineTo(w, h - r)
            cubicTo(w, h - c, w - c, h, w - r, h)

            lineTo(r, h)
            cubicTo(c, h, 0f, h - c, 0f, h - r)

            lineTo(0f, r)
            cubicTo(0f, c, c, 0f, r, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}\n