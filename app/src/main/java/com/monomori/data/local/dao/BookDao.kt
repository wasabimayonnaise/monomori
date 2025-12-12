package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Book entities
 */
@Dao
interface BookDao {
    
    @Query("SELECT * FROM books ORDER BY dateAdded DESC")
    fun getAllBooks(): Flow<List<BookEntity>>
    
    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: Long): Flow<BookEntity?>
    
    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%' OR authors LIKE '%' || :query || '%'")
    fun searchBooks(query: String): Flow<List<BookEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)
    
    @Update
    suspend fun updateBook(book: BookEntity)
    
    @Delete
    suspend fun deleteBook(book: BookEntity)
    
    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteBookById(id: String)
    
    @Query("SELECT COUNT(*) FROM books")
    fun getBookCount(): Flow<Int>

}
