package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.MusicEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Music entities
 */
@Dao
interface MusicDao {
    
    @Query("SELECT * FROM music ORDER BY dateAdded DESC")
    fun getAllMusic(): Flow<List<MusicEntity>>
    
    @Query("SELECT * FROM music WHERE id = :id")
    fun getMusicById(id: String): Flow<MusicEntity?>
    
    @Query("SELECT * FROM music WHERE albumTitle LIKE '%' || :query || '%' OR artists LIKE '%' || :query || '%'")
    fun searchMusic(query: String): Flow<List<MusicEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(music: MusicEntity)
    
    @Update
    suspend fun updateMusic(music: MusicEntity)
    
    @Delete
    suspend fun deleteMusic(music: MusicEntity)
    
    @Query("DELETE FROM music WHERE id = :id")
    suspend fun deleteMusicById(id: String)
    
    @Query("SELECT COUNT(*) FROM music")
    fun getMusicCount(): Flow<Int>
}
