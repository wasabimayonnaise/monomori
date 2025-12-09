package com.monomori.ui.screens.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monomori.data.local.dao.BookDao
import com.monomori.data.local.entity.BookEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val bookDao: BookDao
) : ViewModel() {

    fun saveBook(
        title: String,
        author: String,
        pageCount: Int?,
        genre: String,
        releaseDate: String
    ) {
        viewModelScope.launch {
            val book = BookEntity(
                title = title,
                authors = if (author.isNotBlank()) listOf(author) else emptyList(),
                pageCount = pageCount,
                genre = genre.ifBlank { null },
                notes = releaseDate.ifBlank { null }  // We'll fix this properly later!
            )
            bookDao. insertBook(book)
        }
    }
}