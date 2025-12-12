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
        releaseDate: String,
        publisher: String? = null,
        isbn: String? = null,
        series: String? = null,
        volumeNumber: Int? = null,
        language: String? = null,
        notes: String? = null,
        primaryImage: String? = null
    ) {
        viewModelScope.launch {
            val book = BookEntity(
                title = title,
                authors = if (author.isNotBlank()) listOf(author) else emptyList(),
                pageCount = pageCount,
                genre = genre.ifBlank { null },
                releaseDate = null, // TODO: parse releaseDate string to Date if you want, or leave as null for now
                publisher = publisher?.ifBlank { null },
                isbn = isbn?.ifBlank { null },
                series = series?.ifBlank { null },
                volumeNumber = volumeNumber,
                language = language?.ifBlank { null },
                notes = notes?.ifBlank { null },
                primaryImage = primaryImage
            )
            bookDao.insertBook(book)
        }
    }
}