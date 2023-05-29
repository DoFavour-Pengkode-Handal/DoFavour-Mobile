package com.example.dofavour.android.core_ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.dofavour.core.presentation.Colors

val Green20 = Color(Colors.Green20)
val Green40 = Color(Colors.Green40)
val Green60 = Color(Colors.Green60)
val Green80 = Color(Colors.Green80)
val Green100 = Color(Colors.Green100)
val TextBlack = Color(Colors.TextBlack)
val Grey = Color(Colors.Grey)
val DarkGrey = Color(Colors.DarkGrey)
val Linear40100 = Brush.linearGradient(
    colors = listOf(
        Green100,
        Green40
    )
)

val Radial40100 = Brush.radialGradient(
    colors = listOf(
        Green40,
        Green100
    )
)

val lightColors = darkColors(
    primary = Green40,
    background = Grey,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkColors = lightColors(
    primary = Green40,
    background = DarkGrey,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White
)

val LightGradientPalette = GradientPalette(
    primary = Linear40100,
    primaryVariant = Radial40100
)

val DarkGradientPalette = GradientPalette(
    primary = Linear40100,
    primaryVariant = Radial40100
)