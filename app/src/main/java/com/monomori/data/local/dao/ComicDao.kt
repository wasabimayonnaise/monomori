package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.ComicEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Comic entities
 */
@Dao
interface ComicDao {
    
    @Query("SELECT * FROM comics ORDER BY dateAdded DESC")
    fun getAllComics(): Flow<List<ComicEntity>>
    
    @Query("SELECT * FROM comics WHERE id = :id")
    suspend fun getComicById(id: String): ComicEntity?
    
    @Query("SELECT * FROM comics WHERE series = :series ORDER BY issueNumber ASC")
    fun getComicsBySeries(series: String): Flow<List<ComicEntity>>
    
    @Query("SELECT * FROM comics WHERE readStatus = :status ORDER BY dateAdded DESC")
    fun getComicsByReadStatus(status: String): Flow<List<ComicEntity>>
    
    @Query("SELECT DISTINCT series FROM comics WHERE series IS NOT NULL ORDER BY series ASC")
    fun getAllSeries(): Flow<List<String>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comic: ComicEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(comics: List<ComicEntity>)
    
    @Update
    suspend fun updateComic(comic: ComicEntity)
    
    @Delete
    suspend fun deleteComic(comic: ComicEntity)
    
    @Query("DELETE FROM comics WHERE id = :id")
    suspend fun deleteComicById(id: String)
    
    @Query("DELETE FROM comics")
    suspend fun deleteAllComics()
    
    @Query("SELECT COUNT(*) FROM comics")
    suspend fun getComicsCount(): Int
    
    @Query("""
        SELECT * FROM comics 
        WHERE title LIKE '%' || :query || '%' 
        OR series LIKE '%' || :query || '%'
        OR issueNumber LIKE '%' || :query || '%'
        ORDER BY dateAdded DESC
    """)
    fun searchComics(query: String): Flow<List<ComicEntity>>
}
