package com.monomori.ui.screens.collection

import androidx.lifecycle. ViewModel
import androidx.lifecycle. viewModelScope
import com.monomori.data.local.dao.BookDao
import com.monomori.data.local.entity.BookEntity
import dagger. hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val bookDao: BookDao
) : ViewModel() {

    val books: Flow<List<BookEntity>> = bookDao.getAllBooks()

    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            bookDao.deleteBook(book)
        }
    }
}