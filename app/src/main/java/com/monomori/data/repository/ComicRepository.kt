package com.monomori.data.repository

import com.monomori.data.local.dao.ComicDao
import com.monomori.data.local.entity.ComicEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Comic data
 * Handles local database operations
 * Note: Comic API integrations can be added later (e.g., Marvel API, Comic Vine)
 */
@Singleton
class ComicRepository @Inject constructor(
    private val comicDao: ComicDao
) {
    
    fun getAllComics(): Flow<List<ComicEntity>> = comicDao.getAllComics()
    
    suspend fun getComicById(id: String): ComicEntity? = comicDao.getComicById(id)
    
    fun getComicsBySeries(series: String): Flow<List<ComicEntity>> = 
        comicDao.getComicsBySeries(series)
    
    fun getComicsByReadStatus(status: String): Flow<List<ComicEntity>> = 
        comicDao.getComicsByReadStatus(status)
    
    fun getAllSeries(): Flow<List<String>> = comicDao.getAllSeries()
    
    fun searchComics(query: String): Flow<List<ComicEntity>> = comicDao.searchComics(query)
    
    suspend fun insertComic(comic: ComicEntity) = comicDao.insertComic(comic)
    
    suspend fun updateComic(comic: ComicEntity) = comicDao.updateComic(comic)
    
    suspend fun deleteComic(comic: ComicEntity) = comicDao.deleteComic(comic)
    
    suspend fun getComicsCount(): Int = comicDao.getComicsCount()
}
