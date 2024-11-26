package app.playground

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable fun Theme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    darkTheme -> DarkColors
    else -> LightColors
  }
  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content,
  )
}

val color = Color.Red
private val LightColors = lightColorScheme(
  primary = color,
  secondaryContainer = color,
)

private val DarkColors = darkColorScheme(
  primary = color,
  secondaryContainer = color,
)

private val Typography = Typography()
