package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.ModelKitEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for ModelKit entities
 */
@Dao
interface ModelKitDao {
    
    @Query("SELECT * FROM model_kits ORDER BY dateAdded DESC")
    fun getAllModelKits(): Flow<List<ModelKitEntity>>
    
    @Query("SELECT * FROM model_kits WHERE id = :id")
    fun getModelKitById(id: String): Flow<ModelKitEntity?>
    
    @Query("SELECT * FROM model_kits WHERE kitName LIKE '%' || :query || '%' OR seriesSource LIKE '%' || :query || '%'")
    fun searchModelKits(query: String): Flow<List<ModelKitEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModelKit(kit: ModelKitEntity)
    
    @Update
    suspend fun updateModelKit(kit: ModelKitEntity)
    
    @Delete
    suspend fun deleteModelKit(kit: ModelKitEntity)
    
    @Query("DELETE FROM model_kits WHERE id = :id")
    suspend fun deleteModelKitById(id: String)
    
    @Query("SELECT COUNT(*) FROM model_kits")
    fun getModelKitCount(): Flow<Int>
}
