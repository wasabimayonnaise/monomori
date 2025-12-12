package com.monomori.data.repository

import com.monomori.BuildConfig
import com.monomori.data.local.dao.MusicDao
import com.monomori.data.local.entity.MusicEntity
import com.monomori.data.remote.api.DiscogsApi
import com.monomori.data.remote.dto.DiscogsRelease
import com.monomori.data.remote.dto.DiscogsReleaseDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Music data
 * Handles both local database operations and Discogs API calls
 */
@Singleton
class MusicRepository @Inject constructor(
    private val musicDao: MusicDao,
    private val discogsApi: DiscogsApi
) {
    
    // Local database operations
    fun getAllMusic(): Flow<List<MusicEntity>> = musicDao.getAllMusic()

    fun getMusicById(id:  String): Flow<MusicEntity?> = musicDao.getMusicById(id)
    
    fun searchMusic(query: String): Flow<List<MusicEntity>> = musicDao.searchMusic(query)
    
    suspend fun insertMusic(music: MusicEntity) = musicDao.insertMusic(music)
    
    suspend fun updateMusic(music: MusicEntity) = musicDao.updateMusic(music)
    
    suspend fun deleteMusic(music: MusicEntity) = musicDao.deleteMusic(music)

    fun getMusicCount(): Flow<Int> = musicDao.getMusicCount()
    
    // API operations
    /**
     * Search for music releases using Discogs API
     * @param query Search query (artist, album, or keywords)
     * @param perPage Results per page
     * @return List of DiscogsRelease from API or empty list if API credentials not configured
     */
    suspend fun searchMusicOnline(query: String, perPage: Int = 20): List<DiscogsRelease> {
        return try {
            if (BuildConfig.DISCOGS_API_KEY.isEmpty() || BuildConfig.DISCOGS_API_SECRET.isEmpty()) {
                emptyList()
            } else {
                val response = discogsApi.search(
                    query = query,
                    type = "release",
                    key = BuildConfig.DISCOGS_API_KEY,
                    secret = BuildConfig.DISCOGS_API_SECRET,
                    perPage = perPage
                )
                response.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    /**
     * Search for music by barcode using Discogs API
     * @param barcode Barcode number
     * @return List of DiscogsRelease from API or empty list if error
     */
    suspend fun searchByBarcode(barcode: String): List<DiscogsRelease> {
        return try {
            if (BuildConfig.DISCOGS_API_KEY.isEmpty() || BuildConfig.DISCOGS_API_SECRET.isEmpty()) {
                emptyList()
            } else {
                val response = discogsApi.searchByBarcode(
                    barcode = barcode,
                    key = BuildConfig.DISCOGS_API_KEY,
                    secret = BuildConfig.DISCOGS_API_SECRET
                )
                response.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    /**
     * Search for music by artist and album using Discogs API
     * @param artist Artist name
     * @param album Album/release name
     * @param perPage Results per page
     * @return List of DiscogsRelease from API or empty list if error
     */
    suspend fun searchByArtistAndAlbum(
        artist: String,
        album: String,
        perPage: Int = 20
    ): List<DiscogsRelease> {
        return try {
            if (BuildConfig.DISCOGS_API_KEY.isEmpty() || BuildConfig.DISCOGS_API_SECRET.isEmpty()) {
                emptyList()
            } else {
                val response = discogsApi.searchByArtistAndRelease(
                    artist = artist,
                    release = album,
                    key = BuildConfig.DISCOGS_API_KEY,
                    secret = BuildConfig.DISCOGS_API_SECRET,
                    perPage = perPage
                )
                response.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    /**
     * Get detailed release information from Discogs API
     * @param releaseId Discogs release ID
     * @return DiscogsReleaseDetails or null if error
     */
    suspend fun getReleaseDetails(releaseId: Int): DiscogsReleaseDetails? {
        return try {
            if (BuildConfig.DISCOGS_API_KEY.isEmpty() || BuildConfig.DISCOGS_API_SECRET.isEmpty()) {
                null
            } else {
                discogsApi.getReleaseDetails(
                    releaseId = releaseId,
                    key = BuildConfig.DISCOGS_API_KEY,
                    secret = BuildConfig.DISCOGS_API_SECRET
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    /**
     * Convert DiscogsRelease from search results to MusicEntity
     */
    fun discogsReleaseToEntity(release: DiscogsRelease): MusicEntity {
        return MusicEntity(
            albumTitle = release.title,
            label = release.label?.firstOrNull(),
            genre = release.genre?.firstOrNull(),
            genres = release.genre ?: emptyList(),
            year = release.year?.toIntOrNull(),
            coverImageUrl = release.coverImage ?: release.thumb,
            discogsUrl = release.uri,
            discogsId = release.id,
            upc = release.barcode?.firstOrNull(),
            catalogNumber = release.catno,
            countryOfRelease = release.country,
            formatDetails = release.format?.joinToString(", ")
        )
    }
    
    /**
     * Convert DiscogsReleaseDetails to MusicEntity with complete information
     */
    fun discogsDetailsToEntity(details: DiscogsReleaseDetails): MusicEntity {
        // Extract artist names
        val artists = details.artists?.map { it.name } ?: emptyList()
        
        // Extract tracklist
        val tracklist = details.tracklist?.map { track ->
            "${track.position}. ${track.title}${track.duration?.let { " ($it)" } ?: ""}"
        } ?: emptyList()
        
        // Get best quality image
        val coverImage = details.images?.firstOrNull { it.type == "primary" }?.uri
            ?: details.images?.firstOrNull()?.uri
        
        // Extract barcode
        val barcode = details.identifiers?.firstOrNull { it.type == "Barcode" }?.value
        
        // Extract label info
        val label = details.labels?.firstOrNull()?.name
        val catalogNumber = details.labels?.firstOrNull()?.catno
        
        return MusicEntity(
            albumTitle = details.title,
            artists = artists,
            label = label,
            catalogNumber = catalogNumber,
            upc = barcode,
            formatDetails = details.formats?.firstOrNull()?.name,
            genre = details.genres?.firstOrNull(),
            genres = details.genres ?: emptyList(),
            year = details.year,
            countryOfRelease = details.country,
            tracklist = tracklist,
            coverImageUrl = coverImage,
            discogsUrl = details.uri,
            discogsId = details.id,
            notes = details.notes,
            trackCount = details.tracklist?.size
        )
    }
}
