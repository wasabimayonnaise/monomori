package com.monomori.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Spreadsheet-style row for displaying collection items
 * Compact table-like view showing multiple columns of data
 */
@Composable
fun SpreadsheetRow(
    columns: List<SpreadsheetColumn>,
    onRowClick: () -> Unit,
    modifier: Modifier = Modifier,
    isHeader: Boolean = false
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (!isHeader) Modifier.clickable(onClick = onRowClick)
                else Modifier
            ),
        color = if (isHeader) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.surface
        },
        tonalElevation = if (isHeader) 3.dp else 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .animateContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            columns.forEach { column ->
                Box(
                    modifier = Modifier.weight(column.weight),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = column.value,
                        style = if (isHeader) {
                            MaterialTheme.typography.labelLarge
                        } else {
                            MaterialTheme.typography.bodyMedium
                        },
                        maxLines = if (isHeader) 1 else 2,
                        overflow = TextOverflow.Ellipsis,
                        color = if (isHeader) {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }
        }
    }
    
    if (!isHeader) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )
    }
}

/**
 * Spreadsheet list view with header
 */
@Composable
fun SpreadsheetList(
    headerColumns: List<SpreadsheetColumn>,
    items: List<SpreadsheetItem>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Header row
        SpreadsheetRow(
            columns = headerColumns,
            onRowClick = {},
            isHeader = true
        )
        
        // Data rows
        items.forEach { item ->
            SpreadsheetRow(
                columns = item.columns,
                onRowClick = { onItemClick(item.id) }
            )
        }
    }
}

/**
 * Data class for spreadsheet columns
 */
data class SpreadsheetColumn(
    val value: String,
    val weight: Float = 1f
)

/**
 * Data class for spreadsheet items
 */
data class SpreadsheetItem(
    val id: String,
    val columns: List<SpreadsheetColumn>
)
