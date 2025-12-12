package com.monomori.ui.screens.additem

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import com.monomori.data.remote.RetrofitClient
import com.monomori.BuildConfig
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    category: String,
    onNavigateBack: () -> Unit,
    onSaveItem: (String, String) -> Unit,
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val displayName = category.lowercase().replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }

    // Book fields
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var pageCount by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var releaseDate by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var volumeNumber by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    // Barcode scanner state
    var showScanner by remember { mutableStateOf(false) }

    // Permissions state for camera
    var cameraGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        cameraGranted = granted
        if (granted) {
            showScanner = true
        } else {
            snackbarMessage = "Camera permission is required to scan barcodes."
        }
    }

// And THEN, _inside your Composable body_ (after the if (showScanner) ...), add:
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }

    // ----- Image picker & camera -----
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var cameraImageUri by remember { mutableStateOf<Uri?>(null) }

    // Gallery launcher (pick image from gallery)
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) photoUri = uri
    }

    // Camera launcher (take a new photo)
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && cameraImageUri != null) photoUri = cameraImageUri
    }

    // Helper to create a uri for the camera image
    fun createImageUri(context: Context): Uri? {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFile = File(context.cacheDir, "IMG_$timestamp.jpg")
        return androidx.core.content.FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )
    }

    val scope = rememberCoroutineScope()

            if (showScanner) {
                BarcodeScannerScreen(
                    onBarcodeScanned = { code ->
                        showScanner = false
                        isbn = code
                        scope.launch {
                            try {
                                val response = RetrofitClient.googleBooksApi.searchByIsbn(
                                    isbn = "isbn:$code",
                                    apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY
                                )
                                val info = response.items?.firstOrNull()?.volumeInfo
                                if (info != null) {
                                    // ------ Title/Volume smart splitting ------
                                    val titleRegex = Regex("""(.+),\s*Vol\. (\d+)""")
                                    info.title?.let { originalTitle ->
                                        val match = titleRegex.matchEntire(originalTitle)
                                        if (match != null) {
                                            title = match.groupValues[1]
                                            volumeNumber = match.groupValues[2]
                                        } else {
                                            title = originalTitle
                                        }
                                    }
                                    // -------------------------------------------
                                    author = info.authors?.joinToString(", ") ?: ""
                                    publisher = info.publisher ?: ""
                                    releaseDate = info.publishedDate ?: ""
                                    pageCount = info.pageCount?.toString() ?: ""
                                    genre = info.categories?.joinToString(", ") ?: ""
                                    // ------ Cover photo autofill (if present) ------
                                    photoUri = info.imageLinks?.thumbnail?.let {
                                        // fix http→https for better compatibility
                                        Uri.parse(it.replace("http://", "https://"))
                                    }
                                    // ------ Language code to display name ------
                                    language = info.language?.let {
                                        Locale(it).getDisplayLanguage(Locale.getDefault())
                                    } ?: ""
                                }
                            } catch (e: Exception) {
                                // Optionally: show error
                            }
                        }
                    }
                )
                return
            }

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
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // --- PHOTO PREVIEW + BUTTONS ROW ---
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (photoUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(photoUri),
                        contentDescription = "Book Photo",
                        modifier = Modifier.size(120.dp).clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    Box(
                        Modifier.size(120.dp).clip(RoundedCornerShape(8.dp)).background(Color.Gray),
                        contentAlignment = Alignment.Center
                    ) { Text("No Photo") }
                }
                Column {
                    Button(
                        onClick = { galleryLauncher.launch("image/*") }
                    ) { Text("Add Photo") }
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = {
                            val uri = createImageUri(context)
                            cameraImageUri = uri
                            cameraLauncher.launch(uri)
                        }
                    ) { Text("Take Photo") }
                }
            }

            Spacer(Modifier.height(8.dp))

            // --- Scan ISBN row ---
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = isbn,
                    onValueChange = { isbn = it },
                    label = { Text("ISBN") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(
                    onClick = {
                        when (PackageManager.PERMISSION_GRANTED) {
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) -> {
                                showScanner = true
                            }
                            else -> {
                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }
                    },
                    modifier = Modifier.height(56.dp) // matches textfield
                ) {
                    Text("Scan ISBN")
                }
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title*") },
                placeholder = { Text("Enter book title...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Author(s)") },
                placeholder = { Text("Enter author name...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = publisher,
                onValueChange = { publisher = it },
                label = { Text("Publisher") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = series,
                onValueChange = { series = it },
                label = { Text("Series") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = volumeNumber,
                onValueChange = { volumeNumber = it.filter { ch -> ch.isDigit() } },
                label = { Text("Volume Number") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = pageCount,
                onValueChange = { pageCount = it.filter { char -> char.isDigit() } },
                label = { Text("Page Count") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Genre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = language,
                onValueChange = { language = it },
                label = { Text("Language") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = releaseDate,
                onValueChange = { releaseDate = it },
                label = { Text("Release Date") },
                placeholder = { Text("e.g. 2023 or March 2023") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                minLines = 2
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // Add all fields to saveBook when you update your ViewModel!
                    viewModel.saveBook(
                        title = title,
                        author = author,
                        pageCount = pageCount.toIntOrNull(),
                        genre = genre,
                        releaseDate = releaseDate,
                        publisher = publisher,
                        isbn = isbn,
                        series = series,
                        volumeNumber = volumeNumber.toIntOrNull(),
                        language = language,
                        notes = notes,
                        primaryImage = photoUri?.toString()
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