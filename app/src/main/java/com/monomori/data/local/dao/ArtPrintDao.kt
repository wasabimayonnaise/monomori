package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.ArtPrintEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for ArtPrint entities
 */
@Dao
interface ArtPrintDao {
    
    @Query("SELECT * FROM art_prints ORDER BY dateAdded DESC")
    fun getAllArtPrints(): Flow<List<ArtPrintEntity>>
    
    @Query("SELECT * FROM art_prints WHERE id = :id")
    fun getArtPrintById(id: String): Flow<ArtPrintEntity?>
    
    @Query("SELECT * FROM art_prints WHERE title LIKE '%' || :query || '%' OR artist LIKE '%' || :query || '%'")
    fun searchArtPrints(query: String): Flow<List<ArtPrintEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtPrint(artPrint: ArtPrintEntity)
    
    @Update
    suspend fun updateArtPrint(artPrint: ArtPrintEntity)
    
    @Delete
    suspend fun deleteArtPrint(artPrint: ArtPrintEntity)
    
    @Query("DELETE FROM art_prints WHERE id = :id")
    suspend fun deleteArtPrintById(id: String)
    
    @Query("SELECT COUNT(*) FROM art_prints")
    fun getArtPrintCount(): Flow<Int>
}
