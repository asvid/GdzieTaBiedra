package com.hedgehog.gdzietabiedra.ui.styles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val LightColorPalette = lightColors(
    primary = primaryColor,
    secondary = secondaryColor,
    primaryVariant = primaryDarkColor,
    onPrimary = primaryLightColor,
    onSurface = primaryLightColor,
    secondaryVariant = primaryLightColor,
    onSecondary = secondaryLightColor
)

private val DarkColorPalette = darkColors(
    primary = primaryColor,
    secondary = secondaryColor,
    primaryVariant = primaryDarkColor,
    onPrimary = primaryLightColor,
    onSurface = primaryLightColor,
    secondaryVariant = primaryLightColor,
    onSecondary = secondaryLightColor
)

@Composable
fun BiedraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun SettingsTitle(text: String){
    Text(text, style = settingsTitle, modifier = Modifier.padding(4.dp))
}
