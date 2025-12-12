package com.monomori.data.repository

import com.monomori.BuildConfig
import com.monomori.data.local.dao.BookDao
import com.monomori.data.local.entity.BookEntity
import com.monomori.data.remote.api.GoogleBooksApi
import com.monomori.data.remote.dto.BookVolume
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Book data
 * Handles both local database operations and Google Books API calls
 */
@Singleton
class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val googleBooksApi: GoogleBooksApi
) {

    // Local database operations
    fun getAllBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    fun getBookById(id: Long): Flow<BookEntity?> = bookDao.getBookById(id)

    fun searchBooks(query: String): Flow<List<BookEntity>> = bookDao.searchBooks(query)

    suspend fun insertBook(book: BookEntity) = bookDao.insertBook(book)

    suspend fun updateBook(book: BookEntity) = bookDao.updateBook(book)

    suspend fun deleteBook(book: BookEntity) = bookDao.deleteBook(book)

    fun getBooksCount(): Flow<Int> = bookDao.getBookCount()

    // API operations
    /**
     * Search for books using Google Books API
     * @param query Search query (title, author, or keywords)
     * @param maxResults Maximum number of results (default: 10)
     * @return List of BookVolume from API or empty list if API key not configured
     */
    suspend fun searchBooksOnline(query: String, maxResults: Int = 10): List<BookVolume> {
        return try {
            if (BuildConfig.GOOGLE_BOOKS_API_KEY.isEmpty()) {
                emptyList()
            } else {
                val response = googleBooksApi.searchBooks(
                    query = query,
                    apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY,
                    maxResults = maxResults
                )
                response.items ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    /**
     * Search for books by ISBN using Google Books API
     * @param isbn ISBN-10 or ISBN-13
     * @return List of BookVolume from API or empty list if error/not configured
     */
    suspend fun searchBooksByIsbn(isbn: String): List<BookVolume> {
        return try {
            if (BuildConfig.GOOGLE_BOOKS_API_KEY.isEmpty()) {
                emptyList()
            } else {
                val response = googleBooksApi.searchByIsbn(
                    isbn = "isbn:$isbn",
                    apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY
                )
                response.items ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    /**
     * Convert BookVolume from API to BookEntity for local storage
     */
    fun bookVolumeToEntity(volume: BookVolume): BookEntity {
        val volumeInfo = volume.volumeInfo
        val isbn13 = volumeInfo.industryIdentifiers?.firstOrNull { it.type == "ISBN_13" }?.identifier
        val isbn10 = volumeInfo.industryIdentifiers?.firstOrNull { it.type == "ISBN_10" }?.identifier

        // Get best quality image URL
        val coverImageUrl = volumeInfo.imageLinks?.let { images ->
            images.large
                ?: images.medium
                ?: images.small
                ?: images.thumbnail?.replace("http://", "https://")
        }

        return BookEntity(
            title = volumeInfo.title,
            authors = volumeInfo.authors ?: emptyList(),
            publisher = volumeInfo.publisher,
            isbn = isbn13 ?: isbn10,
            pageCount = volumeInfo.pageCount,
            genre = volumeInfo.categories?.firstOrNull(),
            language = volumeInfo.language,
            synopsis = volumeInfo.description,
            description = volumeInfo.description,
            coverImageUrl = coverImageUrl
        )
    }
}
