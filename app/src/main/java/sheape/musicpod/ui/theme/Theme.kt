package sheape.musicpod.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Red,
    onPrimary = Text,
    error = Red,
    background = Base,
    surface = Surface0,
    surfaceContainerLowest = Base
)

// TODO: Add light theme colors.
private val LightColorScheme = lightColorScheme(
    primary = Red,
    onPrimary = Text,
    error = Red,
    background = Base,
    surface = Surface0,
)

@Composable
fun extendedColor(light: Color, dark: Color): Color {
    return if(isSystemInDarkTheme()) dark else light
}

val ColorScheme.success: Color @Composable get() = extendedColor(Green, Green)
val ColorScheme.warning: Color @Composable get() = extendedColor(Yellow, Yellow)
val ColorScheme.create: Color @Composable get() = extendedColor(Green, Green)

@Composable
fun MusicPodTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}