package com.monomori.data.local.dao

import androidx.room.*
import com.monomori.data.local.entity.TradingCardEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for TradingCard entities
 */
@Dao
interface TradingCardDao {
    
    @Query("SELECT * FROM trading_cards ORDER BY dateAdded DESC")
    fun getAllTradingCards(): Flow<List<TradingCardEntity>>
    
    @Query("SELECT * FROM trading_cards WHERE id = :id")
    fun getTradingCardById(id: String): Flow<TradingCardEntity?>
    
    @Query("SELECT * FROM trading_cards WHERE cardName LIKE '%' || :query || '%' OR setExpansion LIKE '%' || :query || '%'")
    fun searchTradingCards(query: String): Flow<List<TradingCardEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTradingCard(card: TradingCardEntity)
    
    @Update
    suspend fun updateTradingCard(card: TradingCardEntity)
    
    @Delete
    suspend fun deleteTradingCard(card: TradingCardEntity)
    
    @Query("DELETE FROM trading_cards WHERE id = :id")
    suspend fun deleteTradingCardById(id: String)
    
    @Query("SELECT COUNT(*) FROM trading_cards")
    fun getTradingCardCount(): Flow<Int>
}
