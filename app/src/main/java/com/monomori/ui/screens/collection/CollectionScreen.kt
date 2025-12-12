package com.monomori.ui.screens.collection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.monomori.data.local.entity.BookEntity

/**
 * Collection Screen - Shows items in a specific category
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    category: String,
    onNavigateBack: () -> Unit,
    onAddItem: () -> Unit,
    onBookClick: (Long) -> Unit,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    val displayName = category.lowercase()
        .replace("_", " ")
        .split(" ")
        .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
    val books by viewModel.books.collectAsState(initial = emptyList())

    // Track which book user wants to delete
    var bookToDelete by remember { mutableStateOf<BookEntity?>(null) }

    // Confirmation dialog
    if (bookToDelete != null) {
        AlertDialog(
            onDismissRequest = { bookToDelete = null },
            title = { Text("Delete Book? ") },
            text = { Text("Are you sure you want to delete \"${bookToDelete!!.title}\"?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteBook(bookToDelete!!)
                        bookToDelete = null
                    }
                ) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { bookToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = displayName) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddItem,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
                )
            }
        }
    ) { paddingValues ->
        if (books.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "No items yet!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Tap the + button to add your first $displayName item",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = books,
                    key = { it.id }
                ) { book ->
                    SwipeToDeleteBookItem(
                        book = book,
                        onDelete = { bookToDelete = book },
                        onClick = { onBookClick(book.id) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteBookItem(
    book: BookEntity,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                false  // Don't dismiss yet - wait for confirmation
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            // Red background with delete icon when swiping
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.error,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
        },
        content = {
            BookItem(book = book, onClick = onClick)
        },
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true
    )
}

@Composable
fun BookItem(book: BookEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            val infoParts = mutableListOf<String>()

            if (book.authors.isNotEmpty()) {
                infoParts.add(book.authors.joinToString(", "))
            }

            book.pageCount?.let {
                infoParts.add("$it pgs")
            }

            if (book.series != null && book.volumeNumber != null) {
                infoParts.add("${book.series} Vol. ${book.volumeNumber}")
            } else if (book.series != null) {
                infoParts.add(book.series)
            } else if (book.volumeNumber != null) {
                infoParts.add("Vol. ${book.volumeNumber}")
            }

            if (infoParts.isNotEmpty()) {
                Text(
                    text = infoParts.joinToString("  •  "),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}