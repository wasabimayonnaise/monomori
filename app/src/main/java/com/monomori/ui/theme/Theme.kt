package com.monomori.ui.theme

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
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Available color themes for the app
 */
enum class ColorTheme {
    MONOMORI_DEFAULT,
    SAKURA,
    MATCHA,
    OCEAN,
    SUNSET,
    DYNAMIC // Material You
}

/**
 * Theme modes
 */
enum class ThemeMode {
    LIGHT,
    DARK,
    SYSTEM
}

// Monomori Default Color Schemes
private val MonomoriDefaultDarkColorScheme = darkColorScheme(
    primary = MonomoriDefaultColors.Primary,
    onPrimary = MonomoriDefaultColors.OnPrimary,
    primaryContainer = MonomoriDefaultColors.PrimaryContainer,
    onPrimaryContainer = MonomoriDefaultColors.OnPrimaryContainer,
    secondary = MonomoriDefaultColors.Secondary,
    onSecondary = MonomoriDefaultColors.OnSecondary,
    secondaryContainer = MonomoriDefaultColors.SecondaryContainer,
    onSecondaryContainer = MonomoriDefaultColors.OnSecondaryContainer,
    tertiary = MonomoriDefaultColors.Tertiary,
    onTertiary = MonomoriDefaultColors.OnTertiary,
    tertiaryContainer = MonomoriDefaultColors.TertiaryContainer,
    onTertiaryContainer = MonomoriDefaultColors.OnTertiaryContainer,
    background = MonomoriDefaultColors.Background,
    onBackground = MonomoriDefaultColors.OnBackground,
    surface = MonomoriDefaultColors.Surface,
    onSurface = MonomoriDefaultColors.OnSurface,
    surfaceVariant = MonomoriDefaultColors.SurfaceVariant,
    onSurfaceVariant = MonomoriDefaultColors.OnSurfaceVariant,
    error = ErrorColor,
    onError = OnErrorColor
)

private val MonomoriDefaultLightColorScheme = lightColorScheme(
    primary = MonomoriDefaultColors.Primary,
    onPrimary = MonomoriDefaultColors.OnPrimary,
    primaryContainer = MonomoriDefaultColors.OnPrimaryContainer,
    onPrimaryContainer = MonomoriDefaultColors.PrimaryContainer,
    secondary = MonomoriDefaultColors.Secondary,
    onSecondary = MonomoriDefaultColors.OnSecondary,
    secondaryContainer = MonomoriDefaultColors.OnSecondaryContainer,
    onSecondaryContainer = MonomoriDefaultColors.SecondaryContainer,
    background = MonomoriDefaultColors.OnBackground,
    onBackground = MonomoriDefaultColors.Background,
    surface = MonomoriDefaultColors.OnSurface,
    onSurface = MonomoriDefaultColors.Surface
)

// Sakura Color Schemes
private val SakuraDarkColorScheme = darkColorScheme(
    primary = SakuraColors.Primary,
    onPrimary = SakuraColors.OnPrimary,
    primaryContainer = SakuraColors.PrimaryContainer,
    onPrimaryContainer = SakuraColors.OnPrimaryContainer,
    secondary = SakuraColors.Secondary,
    onSecondary = SakuraColors.OnSecondary,
    secondaryContainer = SakuraColors.SecondaryContainer,
    onSecondaryContainer = SakuraColors.OnSecondaryContainer,
    background = SakuraColors.OnBackground,
    onBackground = SakuraColors.Background,
    surface = SakuraColors.OnSurface,
    onSurface = SakuraColors.Surface
)

private val SakuraLightColorScheme = lightColorScheme(
    primary = SakuraColors.Primary,
    onPrimary = SakuraColors.OnPrimary,
    primaryContainer = SakuraColors.PrimaryContainer,
    onPrimaryContainer = SakuraColors.OnPrimaryContainer,
    secondary = SakuraColors.Secondary,
    onSecondary = SakuraColors.OnSecondary,
    secondaryContainer = SakuraColors.SecondaryContainer,
    onSecondaryContainer = SakuraColors.OnSecondaryContainer,
    background = SakuraColors.Background,
    onBackground = SakuraColors.OnBackground,
    surface = SakuraColors.Surface,
    onSurface = SakuraColors.OnSurface,
    surfaceVariant = SakuraColors.SurfaceVariant,
    onSurfaceVariant = SakuraColors.OnSurfaceVariant
)

// Matcha Color Schemes
private val MatchaDarkColorScheme = darkColorScheme(
    primary = MatchaColors.Primary,
    onPrimary = MatchaColors.OnPrimary,
    primaryContainer = MatchaColors.PrimaryContainer,
    onPrimaryContainer = MatchaColors.OnPrimaryContainer,
    secondary = MatchaColors.Secondary,
    onSecondary = MatchaColors.OnSecondary,
    secondaryContainer = MatchaColors.SecondaryContainer,
    onSecondaryContainer = MatchaColors.OnSecondaryContainer,
    background = MatchaColors.OnBackground,
    onBackground = MatchaColors.Background,
    surface = MatchaColors.OnSurface,
    onSurface = MatchaColors.Surface
)

private val MatchaLightColorScheme = lightColorScheme(
    primary = MatchaColors.Primary,
    onPrimary = MatchaColors.OnPrimary,
    primaryContainer = MatchaColors.PrimaryContainer,
    onPrimaryContainer = MatchaColors.OnPrimaryContainer,
    secondary = MatchaColors.Secondary,
    onSecondary = MatchaColors.OnSecondary,
    secondaryContainer = MatchaColors.SecondaryContainer,
    onSecondaryContainer = MatchaColors.OnSecondaryContainer,
    background = MatchaColors.Background,
    onBackground = MatchaColors.OnBackground,
    surface = MatchaColors.Surface,
    onSurface = MatchaColors.OnSurface,
    surfaceVariant = MatchaColors.SurfaceVariant,
    onSurfaceVariant = MatchaColors.OnSurfaceVariant
)

// Ocean Color Schemes
private val OceanDarkColorScheme = darkColorScheme(
    primary = OceanColors.Primary,
    onPrimary = OceanColors.OnPrimary,
    primaryContainer = OceanColors.PrimaryContainer,
    onPrimaryContainer = OceanColors.OnPrimaryContainer,
    secondary = OceanColors.Secondary,
    onSecondary = OceanColors.OnSecondary,
    secondaryContainer = OceanColors.SecondaryContainer,
    onSecondaryContainer = OceanColors.OnSecondaryContainer,
    background = OceanColors.Background,
    onBackground = OceanColors.OnBackground,
    surface = OceanColors.Surface,
    onSurface = OceanColors.OnSurface,
    surfaceVariant = OceanColors.SurfaceVariant,
    onSurfaceVariant = OceanColors.OnSurfaceVariant
)

private val OceanLightColorScheme = lightColorScheme(
    primary = OceanColors.Primary,
    onPrimary = OceanColors.OnPrimary,
    primaryContainer = OceanColors.OnPrimaryContainer,
    onPrimaryContainer = OceanColors.PrimaryContainer,
    secondary = OceanColors.Secondary,
    onSecondary = OceanColors.OnSecondary,
    background = OceanColors.OnBackground,
    onBackground = OceanColors.Background,
    surface = OceanColors.OnSurface,
    onSurface = OceanColors.Surface
)

// Sunset Color Schemes
private val SunsetDarkColorScheme = darkColorScheme(
    primary = SunsetColors.Primary,
    onPrimary = SunsetColors.OnPrimary,
    primaryContainer = SunsetColors.PrimaryContainer,
    onPrimaryContainer = SunsetColors.OnPrimaryContainer,
    secondary = SunsetColors.Secondary,
    onSecondary = SunsetColors.OnSecondary,
    secondaryContainer = SunsetColors.SecondaryContainer,
    onSecondaryContainer = SunsetColors.OnSecondaryContainer,
    background = SunsetColors.Background,
    onBackground = SunsetColors.OnBackground,
    surface = SunsetColors.Surface,
    onSurface = SunsetColors.OnSurface,
    surfaceVariant = SunsetColors.SurfaceVariant,
    onSurfaceVariant = SunsetColors.OnSurfaceVariant
)

private val SunsetLightColorScheme = lightColorScheme(
    primary = SunsetColors.Primary,
    onPrimary = SunsetColors.OnPrimary,
    primaryContainer = SunsetColors.OnPrimaryContainer,
    onPrimaryContainer = SunsetColors.PrimaryContainer,
    secondary = SunsetColors.Secondary,
    onSecondary = SunsetColors.OnSecondary,
    background = SunsetColors.OnBackground,
    onBackground = SunsetColors.Background,
    surface = SunsetColors.OnSurface,
    onSurface = SunsetColors.Surface
)

/**
 * Main theme composable for Monomori
 * 
 * Supports multiple color themes and dark/light/system modes.
 * Implements Japanese-inspired design with clean whitespace (Ma - 間)
 * and smooth animations.
 */
@Composable
fun MonomoriTheme(
    colorTheme: ColorTheme = ColorTheme.MONOMORI_DEFAULT,
    themeMode: ThemeMode = ThemeMode.DARK,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isDarkTheme = when (themeMode) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }
    
    val colorScheme: ColorScheme = when {
        // Material You dynamic colors (Android 12+)
        colorTheme == ColorTheme.DYNAMIC && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (isDarkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        // Custom color themes
        colorTheme == ColorTheme.SAKURA -> {
            if (isDarkTheme) SakuraDarkColorScheme else SakuraLightColorScheme
        }
        colorTheme == ColorTheme.MATCHA -> {
            if (isDarkTheme) MatchaDarkColorScheme else MatchaLightColorScheme
        }
        colorTheme == ColorTheme.OCEAN -> {
            if (isDarkTheme) OceanDarkColorScheme else OceanLightColorScheme
        }
        colorTheme == ColorTheme.SUNSET -> {
            if (isDarkTheme) SunsetDarkColorScheme else SunsetLightColorScheme
        }
        // Default Monomori theme
        else -> {
            if (isDarkTheme) MonomoriDefaultDarkColorScheme else MonomoriDefaultLightColorScheme
        }
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = MonomoriShapes,
        content = content
    )
}
