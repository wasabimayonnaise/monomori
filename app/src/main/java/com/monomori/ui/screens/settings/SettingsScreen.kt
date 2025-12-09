package com.monomori.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.monomori.BuildConfig
import com.monomori.R
import com.monomori.ui.theme.ColorTheme
import com.monomori.ui.theme.ThemeMode

/**
 * Settings Screen - App configuration and preferences
 * 
 * Allows users to customize appearance (themes), manage data,
 * and view app information.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    // TODO: These should be stored in DataStore and provided via ViewModel
    var selectedThemeMode by remember { mutableStateOf(ThemeMode.DARK) }
    var selectedColorTheme by remember { mutableStateOf(ColorTheme.MONOMORI_DEFAULT) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings_title))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Appearance Section
            SettingsSectionHeader(stringResource(R.string.settings_appearance))
            
            SettingsItem(
                title = stringResource(R.string.settings_theme_mode),
                subtitle = getThemeModeLabel(selectedThemeMode),
                onClick = { /* TODO: Show dialog to select theme mode */ }
            )
            
            SettingsItem(
                title = stringResource(R.string.settings_color_theme),
                subtitle = getColorThemeLabel(selectedColorTheme),
                onClick = { /* TODO: Show dialog to select color theme */ }
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // Data Section
            SettingsSectionHeader(stringResource(R.string.settings_data))
            
            SettingsItem(
                title = stringResource(R.string.settings_backup),
                subtitle = "Backup and restore your collection",
                onClick = { /* TODO: Implement backup */ }
            )
            
            SettingsItem(
                title = stringResource(R.string.settings_export),
                subtitle = "Export data as JSON or CSV",
                onClick = { /* TODO: Implement export */ }
            )
            
            SettingsItem(
                title = stringResource(R.string.settings_import),
                subtitle = "Import data from file",
                onClick = { /* TODO: Implement import */ }
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // About Section
            SettingsSectionHeader(stringResource(R.string.settings_about))
            
            SettingsItem(
                title = stringResource(R.string.settings_version),
                subtitle = "Version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                onClick = { }
            )
            
            SettingsItem(
                title = "物守り - Guardian of Things",
                subtitle = "A universal collection management app",
                onClick = { }
            )
        }
    }
}

@Composable
fun SettingsSectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun getThemeModeLabel(mode: ThemeMode): String = when (mode) {
    ThemeMode.DARK -> stringResource(R.string.theme_mode_dark)
    ThemeMode.LIGHT -> stringResource(R.string.theme_mode_light)
    ThemeMode.SYSTEM -> stringResource(R.string.theme_mode_system)
}

@Composable
fun getColorThemeLabel(theme: ColorTheme): String = when (theme) {
    ColorTheme.MONOMORI_DEFAULT -> stringResource(R.string.color_theme_monomori)
    ColorTheme.SAKURA -> stringResource(R.string.color_theme_sakura)
    ColorTheme.MATCHA -> stringResource(R.string.color_theme_matcha)
    ColorTheme.OCEAN -> stringResource(R.string.color_theme_ocean)
    ColorTheme.SUNSET -> stringResource(R.string.color_theme_sunset)
    ColorTheme.DYNAMIC -> stringResource(R.string.color_theme_dynamic)
}
