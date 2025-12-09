package com.monomori.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.monomori.R
import com.monomori.data.model.CollectionCategory

/**
 * Home Screen - Main entry point showing collection overview
 * 
 * Displays all collection categories with item counts and provides
 * quick access to settings and other features.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToCategory: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(R.string.home_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = stringResource(R.string.home_subtitle),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(R.string.nav_settings)
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Welcome section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.home_welcome),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "0 ${stringResource(R.string.home_total_items)}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            
            // Categories section
            Text(
                text = stringResource(R.string.home_categories),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            
            // Category grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(getCategoryItems()) { item ->
                    CategoryCard(
                        icon = item.icon,
                        title = item.title,
                        count = 0,
                        onClick = { onNavigateToCategory(item.category.name) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    icon: ImageVector,
    title: String,
    count: Int,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = "$count items",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class CategoryItem(
    val category: CollectionCategory,
    val title: String,
    val icon: ImageVector
)

@Composable
fun getCategoryItems(): List<CategoryItem> = listOf(
    CategoryItem(
        CollectionCategory.BOOKS,
        stringResource(R.string.category_books),
        Icons.Default.MenuBook
    ),
    CategoryItem(
        CollectionCategory.FIGURES,
        stringResource(R.string.category_figures),
        Icons.Default.Interests
    ),
    CategoryItem(
        CollectionCategory.MUSIC,
        stringResource(R.string.category_music),
        Icons.Default.Album
    ),
    CategoryItem(
        CollectionCategory.MOVIES_TV,
        stringResource(R.string.category_movies),
        Icons.Default.Movie
    ),
    CategoryItem(
        CollectionCategory.VIDEO_GAMES,
        stringResource(R.string.category_games),
        Icons.Default.SportsEsports
    ),
    CategoryItem(
        CollectionCategory.TRADING_CARDS,
        stringResource(R.string.category_cards),
        Icons.Default.Style
    ),
    CategoryItem(
        CollectionCategory.MODEL_KITS,
        stringResource(R.string.category_models),
        Icons.Default.Handyman
    ),
    CategoryItem(
        CollectionCategory.MAGAZINES,
        stringResource(R.string.category_magazines),
        Icons.Default.LibraryBooks
    ),
    CategoryItem(
        CollectionCategory.ART_PRINTS,
        stringResource(R.string.category_art),
        Icons.Default.Palette
    ),
    CategoryItem(
        CollectionCategory.CUSTOM,
        stringResource(R.string.category_custom),
        Icons.Default.AddBox
    )
)
