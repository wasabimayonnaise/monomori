package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Movie entities
 */
@Dao
interface MovieDao {
    
    @Query("SELECT * FROM movies ORDER BY dateAdded DESC")
    fun getAllMovies(): Flow<List<MovieEntity>>
    
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: String): Flow<MovieEntity?>
    
    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%' OR director LIKE '%' || :query || '%'")
    fun searchMovies(query: String): Flow<List<MovieEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)
    
    @Update
    suspend fun updateMovie(movie: MovieEntity)
    
    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
    
    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteMovieById(id: String)
    
    @Query("SELECT COUNT(*) FROM movies")
    fun getMovieCount(): Flow<Int>
}
