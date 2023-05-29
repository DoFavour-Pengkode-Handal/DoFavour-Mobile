package com.example.dofavour.android.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.core_ui.theme.darkColors
import com.example.dofavour.android.core_ui.theme.lightColors
import com.example.dofavour.android.core_ui.theme.typography

@Composable
fun DoFavourTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }

    val gradients = if (darkTheme) {
        DarkGradientPalette
    } else {
        LightGradientPalette
    }

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    CompositionLocalProvider(LocalGradient provides gradients) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
