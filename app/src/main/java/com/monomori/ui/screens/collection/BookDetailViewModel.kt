package com.monomori.ui.screens.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monomori.data.local.entity.BookEntity
import com.monomori.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _book = MutableStateFlow<BookEntity?>(null)
    val book: StateFlow<BookEntity?> = _book.asStateFlow()

    fun loadBookById(id: Long) {
        viewModelScope.launch {
            bookRepository.getBookById(id)
                .collectLatest { bookResult ->
                    _book.value = bookResult
                }
        }
    }
}