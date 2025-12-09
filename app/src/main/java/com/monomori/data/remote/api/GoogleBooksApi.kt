package com.monomori.data.remote.api

import com.monomori.data.remote.dto.GoogleBooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API interface for Google Books API
 * Documentation: https://developers.google.com/books/docs/v1/reference/volumes
 */
interface GoogleBooksApi {
    
    /**
     * Search for books by query
     * @param query Search query (can be title, author, ISBN, etc.)
     * @param apiKey API key from BuildConfig
     * @param maxResults Maximum number of results (default: 10, max: 40)
     * @param startIndex Index of the first result to return (for pagination)
     * @return GoogleBooksResponse containing list of books
     */
    @GET("books/v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 10,
        @Query("startIndex") startIndex: Int = 0
    ): GoogleBooksResponse
    
    /**
     * Search for books by ISBN
     * @param isbn ISBN-10 or ISBN-13
     * @param apiKey API key from BuildConfig
     * @return GoogleBooksResponse containing matching books
     */
    @GET("books/v1/volumes")
    suspend fun searchByIsbn(
        @Query("q") isbn: String,
        @Query("key") apiKey: String
    ): GoogleBooksResponse
    
    /**
     * Search for books by title and author
     * @param title Book title
     * @param author Author name
     * @param apiKey API key from BuildConfig
     * @param maxResults Maximum number of results
     * @return GoogleBooksResponse containing matching books
     */
    @GET("books/v1/volumes")
    suspend fun searchByTitleAndAuthor(
        @Query("q") query: String, // Format: "intitle:${title}+inauthor:${author}"
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 10
    ): GoogleBooksResponse
}
