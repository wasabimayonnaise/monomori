package com.monomori.ui.screens.additem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Add Item Screen - Form to add a new item to a collection
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    category: String,
    onNavigateBack: () -> Unit,
    onSaveItem: (String, String) -> Unit,
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val displayName = category. lowercase().replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var pageCount by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var releaseDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add $displayName") },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement. spacedBy(16.dp)
        ) {
            // Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                placeholder = { Text("Enter book title...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Author
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Author") },
                placeholder = { Text("Enter author name...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Page Count
            OutlinedTextField(
                value = pageCount,
                onValueChange = { pageCount = it.filter { char -> char.isDigit() } },
                label = { Text("Page Count") },
                placeholder = { Text("Enter number of pages...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Genre
            OutlinedTextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Genre") },
                placeholder = { Text("Enter genre...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Release Date
            OutlinedTextField(
                value = releaseDate,
                onValueChange = { releaseDate = it },
                label = { Text("Release Date") },
                placeholder = { Text("e.g. 2023 or March 2023") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.saveBook(
                        title = title,
                        author = author,
                        pageCount = pageCount.toIntOrNull(),
                        genre = genre,
                        releaseDate = releaseDate
                    )
                    onNavigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank()
            ) {
                Text("Save Item")
            }
        }
    }
}