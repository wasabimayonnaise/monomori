package com.monomori.ui.screens.collection

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.monomori.data.local.entity.BookEntity
import com.monomori.ui.theme.NeonPink
import com.monomori.ui.theme.NeonBlue
import com.monomori.ui.theme.JapanMidNight
import com.monomori.ui.theme.DeepViolet

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
    val books by viewModel.booksForCategory(category).collectAsState(initial = emptyList())
    var bookToDelete by remember { mutableStateOf<BookEntity?>(null) }

    if (bookToDelete != null) {
        AlertDialog(
            onDismissRequest = { bookToDelete = null },
            title = { Text("Delete Book? ", color = NeonPink) },
            text = { Text("Are you sure you want to delete \"${bookToDelete!!.title}\"?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteBook(bookToDelete!!)
                        bookToDelete = null
                    }
                ) {
                    Text("Delete", color = NeonPink)
                }
            },
            dismissButton = {
                TextButton(onClick = { bookToDelete = null }) {
                    Text("Cancel")
                }
            },
            containerColor = Color(0xFF25243A)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = displayName,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = NeonBlue,
                            fontWeight = FontWeight.Black
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddItem,
                containerColor = NeonPink,
                contentColor = Color.Black,
                modifier = Modifier.shadow(16.dp, shape = MaterialTheme.shapes.extraLarge)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
                )
            }
        },
        containerColor = JapanMidNight
    ) { paddingValues ->
        if (books.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(JapanMidNight),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(26.dp)
                ) {
                    Text(
                        text = "No items yet!",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = NeonPink,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Tap the + button to add your first $displayName item",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = NeonBlue
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(JapanMidNight),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                items(
                    items = books,
                    key = { it.id }
                ) { book ->
                    NeonBookItem(
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
fun NeonBookItem(
    book: BookEntity,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                false
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(listOf(NeonPink, NeonBlue)),
                        shape = MaterialTheme.shapes.large
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .graphicsLayer { shadowElevation = 24f }
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(DeepViolet, JapanMidNight)
                        ),
                        shape = MaterialTheme.shapes.large
                    )
                    .clickable(
                        onClick = onClick,
                        indication = LocalIndication.current,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = NeonBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    val infoParts = mutableListOf<String>()
                    if (book.authors.isNotEmpty()) {
                        infoParts.add(book.authors.joinToString(", "))
                    }
                    book.pageCount?.let { infoParts.add("$it pgs") }
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
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = NeonPink
                            )
                        )
                    }
                }
            }
        },
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true
    )
}