package com.monomori.ui.screens.home

import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.monomori.R
import com.monomori.data.model.CollectionCategory
import androidx.annotation.RequiresApi
import android.os.Build

val NeonPink = Color(0xFFFF59E4)
val NeonBlue = Color(0xFF44C0FF)
val JapanMidNight = Color(0xFF1A1B2B)
val DeepViolet = Color(0xFF232342)

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.S)
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
                            style = MaterialTheme.typography.headlineLarge.copy(
                                color = NeonBlue,
                                fontWeight = FontWeight.Black
                            )
                        )
                        Text(
                            text = stringResource(R.string.home_subtitle),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = NeonPink
                            )
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(R.string.nav_settings),
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
                .padding(paddingValues)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            // Welcome section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(22.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = DeepViolet
                ),
                shape = RoundedCornerShape(22.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(NeonBlue.copy(alpha = 0.24f), NeonPink.copy(alpha = 0.14f))
                            ),
                            shape = RoundedCornerShape(22.dp)
                        )
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    Text(
                        text = stringResource(R.string.home_welcome),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = NeonBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "0 ${stringResource(R.string.home_total_items)}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = NeonPink,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            }

            // Categories section
            Text(
                text = stringResource(R.string.home_categories),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = NeonPink,
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier.padding(horizontal = 2.dp)
            )

            // Category grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(getCategoryItems()) { item ->
                    NeonCategoryCard(
                        icon = item.icon,
                        category = item.category,
                        title = item.category.name.replace("_", " ").lowercase()
                            .replaceFirstChar { it.uppercase() },
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
fun NeonCategoryCard(
    icon: ImageVector,
    category: CollectionCategory,
    title: String,
    count: Int,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(9.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = DeepViolet
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = when (category) {
                            CollectionCategory.TRADING_CARDS ->
                                listOf(NeonBlue.copy(alpha = 0.13f), NeonPink.copy(alpha = 0.16f)) // REVERSED!
                            CollectionCategory.MODEL_KITS ->
                                listOf(NeonBlue.copy(alpha = 0.18f), NeonPink.copy(alpha = 0.10f))
                            else ->
                                listOf(
                                    NeonBlue.copy(alpha = 0.13f),
                                    NeonPink.copy(alpha = 0.12f)
                                )
                        }
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // --- Neon Glow Icon with RenderEffect blur! ---
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(62.dp)
                        .graphicsLayer {
                            renderEffect = RenderEffect.blur(30f, 30f, Shader.TileMode.Clamp)
                            alpha = 0.6f
                        }
                        .then(
                            if (category == CollectionCategory.TRADING_CARDS)
                                Modifier.graphicsLayer { rotationZ = 180f }
                            else Modifier
                        ),
                    tint = NeonPink
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(52.dp)
                        .then(
                            if (category == CollectionCategory.TRADING_CARDS)
                                Modifier.graphicsLayer { rotationZ = 180f }
                            else Modifier
                        ),
                    tint = NeonPink
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = NeonBlue,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "$count items",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.LightGray
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

data class CategoryItem(
    val category: CollectionCategory,
    val icon: ImageVector
)

fun getCategoryItems(): List<CategoryItem> = listOf(
    CategoryItem(CollectionCategory.BOOKS, Icons.Filled.AutoStories),
    CategoryItem(CollectionCategory.FIGURES, Icons.Filled.EmojiPeople),
    CategoryItem(CollectionCategory.MUSIC, Icons.Filled.Headset),
    CategoryItem(CollectionCategory.MOVIES_TV, Icons.Filled.MovieCreation),
    CategoryItem(CollectionCategory.VIDEO_GAMES, Icons.Filled.VideogameAsset),
    CategoryItem(CollectionCategory.TRADING_CARDS, Icons.Filled.Style),
    CategoryItem(CollectionCategory.MODEL_KITS, Icons.Filled.Build),
    CategoryItem(CollectionCategory.MAGAZINES, Icons.Filled.ChromeReaderMode),
    CategoryItem(CollectionCategory.ART_PRINTS, Icons.Filled.Palette),
    CategoryItem(CollectionCategory.CUSTOM, Icons.Filled.AutoAwesome)
)