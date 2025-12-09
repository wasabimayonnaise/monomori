package com.monomori.data.remote.api

import com.monomori.data.remote.dto.TmdbMovieDetails
import com.monomori.data.remote.dto.TmdbSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API interface for TMDB (The Movie Database) API
 * Documentation: https://developers.themoviedb.org/3
 */
interface TmdbApi {
    
    /**
     * Search for movies by title
     * @param apiKey API key from BuildConfig
     * @param query Search query (movie title)
     * @param page Page number for pagination (default: 1)
     * @param includeAdult Include adult content (default: false)
     * @return TmdbSearchResponse containing list of movies
     */
    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): TmdbSearchResponse
    
    /**
     * Search for TV shows by title
     * @param apiKey API key from BuildConfig
     * @param query Search query (TV show title)
     * @param page Page number for pagination (default: 1)
     * @param includeAdult Include adult content (default: false)
     * @return TmdbSearchResponse containing list of TV shows
     */
    @GET("3/search/tv")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): TmdbSearchResponse
    
    /**
     * Search for both movies and TV shows
     * @param apiKey API key from BuildConfig
     * @param query Search query
     * @param page Page number for pagination (default: 1)
     * @param includeAdult Include adult content (default: false)
     * @return TmdbSearchResponse containing list of movies and TV shows
     */
    @GET("3/search/multi")
    suspend fun searchMulti(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): TmdbSearchResponse
    
    /**
     * Get detailed information about a movie
     * @param movieId TMDB movie ID
     * @param apiKey API key from BuildConfig
     * @param appendToResponse Additional info to include (e.g., "credits,videos")
     * @return TmdbMovieDetails with complete movie information
     */
    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): TmdbMovieDetails
    
    /**
     * Get detailed information about a TV show
     * @param tvId TMDB TV show ID
     * @param apiKey API key from BuildConfig
     * @param appendToResponse Additional info to include (e.g., "credits,videos")
     * @return TmdbMovieDetails with complete TV show information
     */
    @GET("3/tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): TmdbMovieDetails
    
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        
        // Image size options
        const val POSTER_SIZE_SMALL = "w185"
        const val POSTER_SIZE_MEDIUM = "w342"
        const val POSTER_SIZE_LARGE = "w500"
        const val POSTER_SIZE_ORIGINAL = "original"
        
        /**
         * Helper function to construct full image URL
         * @param path Image path from API response (starts with /)
         * @param size Image size (default: medium)
         * @return Full image URL
         */
        fun getImageUrl(path: String?, size: String = POSTER_SIZE_MEDIUM): String? {
            return path?.let { "$IMAGE_BASE_URL$size$it" }
        }
    }
}
