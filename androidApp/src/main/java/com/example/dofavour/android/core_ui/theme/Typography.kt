package com.example.dofavour.android.core_ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dofavour.android.R

private val interFont = FontFamily(
    Font(R.font.inter_regular),
    Font(R.font.inter_medium, weight = FontWeight.Medium)
)

val typography = Typography(
    h1 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp
    ),
    h2 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    h3 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp
    ),
    h4 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    h5 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)