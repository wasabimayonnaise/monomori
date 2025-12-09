package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.MagazineEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Magazine entities
 */
@Dao
interface MagazineDao {
    
    @Query("SELECT * FROM magazines ORDER BY dateAdded DESC")
    fun getAllMagazines(): Flow<List<MagazineEntity>>
    
    @Query("SELECT * FROM magazines WHERE id = :id")
    fun getMagazineById(id: String): Flow<MagazineEntity?>
    
    @Query("SELECT * FROM magazines WHERE title LIKE '%' || :query || '%' OR issueNumber LIKE '%' || :query || '%'")
    fun searchMagazines(query: String): Flow<List<MagazineEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMagazine(magazine: MagazineEntity)
    
    @Update
    suspend fun updateMagazine(magazine: MagazineEntity)
    
    @Delete
    suspend fun deleteMagazine(magazine: MagazineEntity)
    
    @Query("DELETE FROM magazines WHERE id = :id")
    suspend fun deleteMagazineById(id: String)
    
    @Query("SELECT COUNT(*) FROM magazines")
    fun getMagazineCount(): Flow<Int>
}
