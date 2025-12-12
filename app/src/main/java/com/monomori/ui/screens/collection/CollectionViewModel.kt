package com.monomori.ui.screens.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monomori.data.local.dao.BookDao
import com.monomori.data.local.entity.BookEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val bookDao: BookDao
) : ViewModel() {

    // This is the universal (unfiltered) books list which you can still use elsewhere
    val books: Flow<List<BookEntity>> = bookDao.getAllBooks()

    // NEW: Books for a specific category
    fun booksForCategory(category: String): Flow<List<BookEntity>> =
        bookDao.getBooksByCategory(category)

    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            bookDao.deleteBook(book)
        }
    }
}