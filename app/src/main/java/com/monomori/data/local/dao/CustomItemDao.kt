package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.CustomItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for CustomItem entities
 */
@Dao
interface CustomItemDao {
    
    @Query("SELECT * FROM custom_items ORDER BY dateAdded DESC")
    fun getAllCustomItems(): Flow<List<CustomItemEntity>>
    
    @Query("SELECT * FROM custom_items WHERE id = :id")
    fun getCustomItemById(id: String): Flow<CustomItemEntity?>
    
    @Query("SELECT * FROM custom_items WHERE categoryName = :categoryName ORDER BY dateAdded DESC")
    fun getCustomItemsByCategory(categoryName: String): Flow<List<CustomItemEntity>>
    
    @Query("SELECT * FROM custom_items WHERE name LIKE '%' || :query || '%' OR categoryName LIKE '%' || :query || '%'")
    fun searchCustomItems(query: String): Flow<List<CustomItemEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomItem(item: CustomItemEntity)
    
    @Update
    suspend fun updateCustomItem(item: CustomItemEntity)
    
    @Delete
    suspend fun deleteCustomItem(item: CustomItemEntity)
    
    @Query("DELETE FROM custom_items WHERE id = :id")
    suspend fun deleteCustomItemById(id: String)
    
    @Query("SELECT COUNT(*) FROM custom_items")
    fun getCustomItemCount(): Flow<Int>
}
