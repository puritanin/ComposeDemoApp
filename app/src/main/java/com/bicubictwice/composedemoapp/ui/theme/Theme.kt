package com.bicubictwice.composedemoapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val LightColors = lightColors(
    primary = Color(0xFF3700B3),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF3700B3),
    secondaryVariant = Color(0xFF3700B3),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFFB717A),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    onError = Color(0xFFFFFFFF),
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(LightColors.background, darkIcons = true)
    }

    MaterialTheme(
        colors = LightColors,
        content = content,
    )
}
