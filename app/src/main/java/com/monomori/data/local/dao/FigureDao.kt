package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.FigureEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Figure entities
 */
@Dao
interface FigureDao {
    
    @Query("SELECT * FROM figures ORDER BY dateAdded DESC")
    fun getAllFigures(): Flow<List<FigureEntity>>
    
    @Query("SELECT * FROM figures WHERE id = :id")
    fun getFigureById(id: String): Flow<FigureEntity?>
    
    @Query("SELECT * FROM figures WHERE character LIKE '%' || :query || '%' OR seriesAnime LIKE '%' || :query || '%'")
    fun searchFigures(query: String): Flow<List<FigureEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFigure(figure: FigureEntity)
    
    @Update
    suspend fun updateFigure(figure: FigureEntity)
    
    @Delete
    suspend fun deleteFigure(figure: FigureEntity)
    
    @Query("DELETE FROM figures WHERE id = :id")
    suspend fun deleteFigureById(id: String)
    
    @Query("SELECT COUNT(*) FROM figures")
    fun getFigureCount(): Flow<Int>
}
