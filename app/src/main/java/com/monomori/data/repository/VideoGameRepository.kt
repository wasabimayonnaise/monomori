package com.monomori.data.repository

import com.monomori.data.local.dao.VideoGameDao
import com.monomori.data.local.entity.VideoGameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Video Game data
 * Handles local database operations
 * Note: Video game API integrations can be added later (e.g., IGDB, GiantBomb)
 */
@Singleton
class VideoGameRepository @Inject constructor(
    private val videoGameDao: VideoGameDao
) {
    
    fun getAllVideoGames(): Flow<List<VideoGameEntity>> = videoGameDao.getAllVideoGames()

    fun getVideoGameById(id: String): Flow<VideoGameEntity?> = videoGameDao.getVideoGameById(id)
    
    fun searchVideoGames(query: String): Flow<List<VideoGameEntity>> = videoGameDao.searchVideoGames(query)
    
    //fun getVideoGamesByPlatform(platform: String): Flow<List<VideoGameEntity>> =
    //    videoGameDao.getVideoGamesByPlatform(platform)
    
    suspend fun insertVideoGame(game: VideoGameEntity) = videoGameDao.insertVideoGame(game)
    
    suspend fun updateVideoGame(game: VideoGameEntity) = videoGameDao.updateVideoGame(game)
    
    suspend fun deleteVideoGame(game: VideoGameEntity) = videoGameDao.deleteVideoGame(game)

    fun getVideoGamesCount(): Flow<Int> = videoGameDao.getVideoGameCount()
}
