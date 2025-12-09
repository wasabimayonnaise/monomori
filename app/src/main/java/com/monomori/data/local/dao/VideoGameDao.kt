package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.VideoGameEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for VideoGame entities
 */
@Dao
interface VideoGameDao {
    
    @Query("SELECT * FROM video_games ORDER BY dateAdded DESC")
    fun getAllVideoGames(): Flow<List<VideoGameEntity>>
    
    @Query("SELECT * FROM video_games WHERE id = :id")
    fun getVideoGameById(id: String): Flow<VideoGameEntity?>
    
    @Query("SELECT * FROM video_games WHERE title LIKE '%' || :query || '%' OR platform LIKE '%' || :query || '%'")
    fun searchVideoGames(query: String): Flow<List<VideoGameEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoGame(game: VideoGameEntity)
    
    @Update
    suspend fun updateVideoGame(game: VideoGameEntity)
    
    @Delete
    suspend fun deleteVideoGame(game: VideoGameEntity)
    
    @Query("DELETE FROM video_games WHERE id = :id")
    suspend fun deleteVideoGameById(id: String)
    
    @Query("SELECT COUNT(*) FROM video_games")
    fun getVideoGameCount(): Flow<Int>
}
