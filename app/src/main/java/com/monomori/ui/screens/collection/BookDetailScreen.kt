package com.monomori.ui.screens.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.monomori.data.local.entity.BookEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    itemId: Long, // The ID passed via navigation (from route)
    onNavigateBack: () -> Unit,
    onEdit: (Long) -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    // Load the book by id when this screen shows
    LaunchedEffect(itemId) {
        viewModel.loadBookById(itemId)
    }
    // Observe the book from the ViewModel
    val book by viewModel.book.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(book?.title ?: "Book Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { book?.id?.let { onEdit(it) } },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = (book != null)
            ) {
                Text(text = "Edit")
            }
        }
    ) { paddingValues ->
        book?.let { bookObj ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Only show image if there is one
                bookObj.primaryImage?.let { uriString ->
                    Image(
                        painter = rememberAsyncImagePainter(uriString),
                        contentDescription = "Book Photo",
                        modifier = Modifier
                            .size(180.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                // Only show non-empty fields
                if (bookObj.title.isNotBlank())
                    InfoRow("Title", bookObj.title)
                if (!bookObj.authors.isNullOrEmpty())
                    InfoRow("Authors", bookObj.authors.joinToString())
                if (!bookObj.publisher.isNullOrBlank())
                    InfoRow("Publisher", bookObj.publisher!!)
                if (!bookObj.isbn.isNullOrBlank())
                    InfoRow("ISBN", bookObj.isbn!!)
                if (!bookObj.genre.isNullOrBlank())
                    InfoRow("Genre", bookObj.genre!!)
                if (!bookObj.series.isNullOrBlank())
                    InfoRow("Series", bookObj.series!!)
                if (bookObj.volumeNumber != null)
                    InfoRow("Volume", bookObj.volumeNumber.toString())
                if (bookObj.pageCount != null)
                    InfoRow("Page Count", bookObj.pageCount.toString())
                if (!bookObj.language.isNullOrBlank())
                    InfoRow("Language", bookObj.language!!)
                // Handle date display prettily as you like
                if (bookObj.releaseDate != null)
                    InfoRow("Release Date", bookObj.releaseDate.toString())
                if (!bookObj.notes.isNullOrBlank())
                    InfoRow("Notes", bookObj.notes!!)
            }
        } ?: Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            label,
            color = Color.Gray,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.width(110.dp)
        )
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}