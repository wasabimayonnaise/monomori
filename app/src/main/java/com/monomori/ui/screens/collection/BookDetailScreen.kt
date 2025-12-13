package com.monomori.ui.screens.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.monomori.data.local.entity.BookEntity
import com.monomori.ui.theme.DeepViolet
import com.monomori.ui.theme.JapanMidNight
import com.monomori.ui.theme.NeonBlue
import com.monomori.ui.theme.NeonPink
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.TileMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    itemId: Long,
    onNavigateBack: () -> Unit,
    onEdit: (Long) -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(itemId) { viewModel.loadBookById(itemId) }
    val book by viewModel.book.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        book?.title ?: "Book Details",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = NeonBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = NeonPink
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepViolet)
            )
        },
        bottomBar = {
            Button(
                onClick = { book?.id?.let { onEdit(it) } },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .graphicsLayer {
                        shadowElevation = 16f
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonPink,
                    contentColor = Color.White
                ),
                enabled = (book != null)
            ) {
                Text(text = "Edit", style = MaterialTheme.typography.titleMedium)
            }
        },
        containerColor = JapanMidNight
    ) { paddingValues ->
        book?.let { bookObj ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                // ---- Neon Glow Book Cover ----
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp)
                ) {
                    // Neon pink glow (blurred colored shape behind image)
                    Box(
                        Modifier
                            .size(192.dp)
                            .align(Alignment.Center)
                            .graphicsLayer {
                                renderEffect = BlurEffect(44f, 44f, TileMode.Clamp)
                                alpha = 0.45f
                            }
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(NeonPink, Color.Transparent),
                                    center = Offset(96f, 96f),
                                    radius = 96f
                                ),
                                shape = CircleShape
                            )
                    )
                    bookObj.primaryImage?.let { uriString ->
                        Image(
                            painter = rememberAsyncImagePainter(uriString),
                            contentDescription = "Book Photo",
                            modifier = Modifier
                                .size(180.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                    }
                }

                // ---- Neon-styled Card for Book Info ----
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(8.dp, MaterialTheme.shapes.extraLarge),
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
                                    colors = listOf(
                                        NeonBlue.copy(alpha = 0.18f),
                                        NeonPink.copy(alpha = 0.13f)
                                    )
                                ),
                                shape = RoundedCornerShape(22.dp)
                            )
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Only show non-empty fields
                        if (bookObj.title.isNotBlank())
                            InfoRowNeon("Title", bookObj.title)
                        if (!bookObj.authors.isNullOrEmpty())
                            InfoRowNeon("Authors", bookObj.authors.joinToString())
                        if (!bookObj.publisher.isNullOrBlank())
                            InfoRowNeon("Publisher", bookObj.publisher!!)
                        if (!bookObj.isbn.isNullOrBlank())
                            InfoRowNeon("ISBN", bookObj.isbn!!)
                        if (!bookObj.genre.isNullOrBlank())
                            InfoRowNeon("Genre", bookObj.genre!!)
                        if (!bookObj.series.isNullOrBlank())
                            InfoRowNeon("Series", bookObj.series!!)
                        if (bookObj.volumeNumber != null)
                            InfoRowNeon("Volume", bookObj.volumeNumber.toString())
                        if (bookObj.pageCount != null)
                            InfoRowNeon("Page Count", bookObj.pageCount.toString())
                        if (!bookObj.language.isNullOrBlank())
                            InfoRowNeon("Language", bookObj.language!!)
                        if (bookObj.releaseDate != null)
                            InfoRowNeon("Release Date", bookObj.releaseDate.toString())
                        if (!bookObj.notes.isNullOrBlank())
                            InfoRowNeon("Notes", bookObj.notes!!)
                    }
                }
            }
        } ?: Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = NeonPink)
        }
    }
}

@Composable
fun InfoRowNeon(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = label,
            color = NeonPink,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.width(110.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = NeonBlue,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}