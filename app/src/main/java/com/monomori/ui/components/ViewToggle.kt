package com.monomori.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.monomori.data.model.ViewMode

/**
 * Toggle button for switching between Card and Spreadsheet view modes
 */
@Composable
fun ViewToggle(
    currentMode: ViewMode,
    onModeChange: (ViewMode) -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = {
            val newMode = when (currentMode) {
                ViewMode.CARD -> ViewMode.SPREADSHEET
                ViewMode.SPREADSHEET -> ViewMode.CARD
            }
            onModeChange(newMode)
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = when (currentMode) {
                ViewMode.CARD -> Icons.Default.ViewList
                ViewMode.SPREADSHEET -> Icons.Default.GridView
            },
            contentDescription = when (currentMode) {
                ViewMode.CARD -> "Switch to list view"
                ViewMode.SPREADSHEET -> "Switch to grid view"
            }
        )
    }
}

/**
 * Segmented button group for view mode selection
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewModeSelector(
    currentMode: ViewMode,
    onModeChange: (ViewMode) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        ViewMode.entries.forEachIndexed { index, mode ->
            SegmentedButton(
                selected = currentMode == mode,
                onClick = { onModeChange(mode) },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = ViewMode.entries.size
                ),
                icon = {
                    SegmentedButtonDefaults.Icon(active = currentMode == mode) {
                        Icon(
                            imageVector = getViewModeIcon(mode),
                            contentDescription = mode.name,
                            modifier = Modifier
                        )
                    }
                }
            ) {
                Text(getViewModeLabel(mode))
            }
        }
    }
}

private fun getViewModeIcon(mode: ViewMode): ImageVector {
    return when (mode) {
        ViewMode.CARD -> Icons.Default.GridView
        ViewMode.SPREADSHEET -> Icons.Default.ViewList
    }
}

private fun getViewModeLabel(mode: ViewMode): String {
    return when (mode) {
        ViewMode.CARD -> "Cards"
        ViewMode.SPREADSHEET -> "List"
    }
}
