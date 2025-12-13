package com.monomori.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.monomori.BuildConfig
import com.monomori.R
import com.monomori.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    var selectedThemeMode by remember { mutableStateOf(ThemeMode.DARK) }
    var selectedColorTheme by remember { mutableStateOf(ColorTheme.MONOMORI_DEFAULT) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings_title),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = NeonBlue,
                            fontWeight = FontWeight.Black
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = NeonPink
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepViolet
                )
            )
        },
        containerColor = JapanMidNight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(JapanMidNight)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 14.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Appearance Section
            SettingsSectionHeader(stringResource(R.string.settings_appearance))

            SettingsNeonCard {
                SettingsItem(
                    title = stringResource(R.string.settings_theme_mode),
                    subtitle = getThemeModeLabel(selectedThemeMode),
                    onClick = { /* TODO: Show dialog to select theme mode */ }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                SettingsItem(
                    title = stringResource(R.string.settings_color_theme),
                    subtitle = getColorThemeLabel(selectedColorTheme),
                    onClick = { /* TODO: Show dialog to select color theme */ }
                )
            }

            // Data Section
            SettingsSectionHeader(stringResource(R.string.settings_data))
            SettingsNeonCard {
                SettingsItem(
                    title = stringResource(R.string.settings_backup),
                    subtitle = "Backup and restore your collection",
                    onClick = { /* TODO: Implement backup */ }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                SettingsItem(
                    title = stringResource(R.string.settings_export),
                    subtitle = "Export data as JSON or CSV",
                    onClick = { /* TODO: Implement export */ }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                SettingsItem(
                    title = stringResource(R.string.settings_import),
                    subtitle = "Import data from file",
                    onClick = { /* TODO: Implement import */ }
                )
            }

            // About Section
            SettingsSectionHeader(stringResource(R.string.settings_about))
            SettingsNeonCard {
                SettingsItem(
                    title = stringResource(R.string.settings_version),
                    subtitle = "Version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                    onClick = { }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                SettingsItem(
                    title = "物守り - Guardian of Things",
                    subtitle = "A universal collection management app",
                    onClick = { }
                )
            }
        }
    }
}

@Composable
fun SettingsSectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = NeonPink
        ),
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
    )
}

@Composable
fun SettingsNeonCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = DeepViolet
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            NeonBlue.copy(alpha = 0.09f),
                            NeonPink.copy(alpha = 0.07f)
                        )
                    ),
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(vertical = 11.dp, horizontal = 12.dp)
        ) {
            content()
        }
    }
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
            .padding(horizontal = 6.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = NeonBlue,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = NeonPink
            )
        )
    }
}

// ...keep getThemeModeLabel, getColorThemeLabel as before
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
