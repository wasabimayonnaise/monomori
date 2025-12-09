package com.monomori.data.remote.api

import com.monomori.data.remote.dto.DiscogsReleaseDetails
import com.monomori.data.remote.dto.DiscogsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API interface for Discogs API
 * Documentation: https://www.discogs.com/developers
 */
interface DiscogsApi {
    
    /**
     * Search for releases (albums, singles, etc.)
     * @param query Search query (artist, album, barcode, etc.)
     * @param type Filter by type: "release", "master", "artist", or "label"
     * @param key Consumer key from BuildConfig
     * @param secret Consumer secret from BuildConfig
     * @param perPage Results per page (default: 20, max: 100)
     * @param page Page number for pagination (default: 1)
     * @return DiscogsSearchResponse containing search results
     */
    @GET("database/search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String? = null,
        @Query("key") key: String,
        @Query("secret") secret: String,
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1
    ): DiscogsSearchResponse
    
    /**
     * Search for releases by barcode
     * @param barcode Barcode number
     * @param key Consumer key from BuildConfig
     * @param secret Consumer secret from BuildConfig
     * @return DiscogsSearchResponse containing matching releases
     */
    @GET("database/search")
    suspend fun searchByBarcode(
        @Query("barcode") barcode: String,
        @Query("type") type: String = "release",
        @Query("key") key: String,
        @Query("secret") secret: String
    ): DiscogsSearchResponse
    
    /**
     * Search for releases by artist and album
     * @param artist Artist name
     * @param release Album/release name
     * @param key Consumer key from BuildConfig
     * @param secret Consumer secret from BuildConfig
     * @param perPage Results per page
     * @return DiscogsSearchResponse containing matching releases
     */
    @GET("database/search")
    suspend fun searchByArtistAndRelease(
        @Query("artist") artist: String,
        @Query("release_title") release: String,
        @Query("type") type: String = "release",
        @Query("key") key: String,
        @Query("secret") secret: String,
        @Query("per_page") perPage: Int = 20
    ): DiscogsSearchResponse
    
    /**
     * Get detailed information about a specific release
     * @param releaseId Discogs release ID
     * @param key Consumer key from BuildConfig
     * @param secret Consumer secret from BuildConfig
     * @return DiscogsReleaseDetails with complete release information
     */
    @GET("releases/{release_id}")
    suspend fun getReleaseDetails(
        @Path("release_id") releaseId: Int,
        @Query("key") key: String,
        @Query("secret") secret: String
    ): DiscogsReleaseDetails
    
    /**
     * Get detailed information about a master release
     * @param masterId Discogs master release ID
     * @param key Consumer key from BuildConfig
     * @param secret Consumer secret from BuildConfig
     * @return DiscogsReleaseDetails with complete master release information
     */
    @GET("masters/{master_id}")
    suspend fun getMasterDetails(
        @Path("master_id") masterId: Int,
        @Query("key") key: String,
        @Query("secret") secret: String
    ): DiscogsReleaseDetails
    
    companion object {
        const val BASE_URL = "https://api.discogs.com/"
        
        // User-Agent is required by Discogs API
        const val USER_AGENT = "Monomori/1.0.0 +https://github.com/wasabimayonnaise/monomori"
    }
}
