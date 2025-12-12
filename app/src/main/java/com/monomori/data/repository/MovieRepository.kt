package com.monomori.data.repository

import com.monomori.BuildConfig
import com.monomori.data.local.dao.MovieDao
import com.monomori.data.local.entity.MovieEntity
import com.monomori.data.remote.api.TmdbApi
import com.monomori.data.remote.dto.TmdbMovie
import com.monomori.data.remote.dto.TmdbMovieDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Movie/TV data
 * Handles both local database operations and TMDB API calls
 */
@Singleton
class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val tmdbApi: TmdbApi
) {
    
    // Local database operations
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getMovieById(id: String): Flow<MovieEntity?> = movieDao.getMovieById(id)
    
    fun searchMovies(query: String): Flow<List<MovieEntity>> = movieDao.searchMovies(query)
    
    suspend fun insertMovie(movie: MovieEntity) = movieDao.insertMovie(movie)
    
    suspend fun updateMovie(movie: MovieEntity) = movieDao.updateMovie(movie)
    
    suspend fun deleteMovie(movie: MovieEntity) = movieDao.deleteMovie(movie)

    fun getMoviesCount(): Flow<Int> = movieDao.getMovieCount()
    
    // API operations
    /**
     * Search for movies using TMDB API
     * @param query Search query (movie title)
     * @param page Page number for pagination
     * @return List of TmdbMovie from API or empty list if API key not configured
     */
    suspend fun searchMoviesOnline(query: String, page: Int = 1): List<TmdbMovie> {
        return try {
            if (BuildConfig.TMDB_API_KEY.isEmpty()) {
                emptyList()
            } else {
                val response = tmdbApi.searchMovies(
                    apiKey = BuildConfig.TMDB_API_KEY,
                    query = query,
                    page = page
                )
                response.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    /**
     * Search for TV shows using TMDB API
     * @param query Search query (TV show title)
     * @param page Page number for pagination
     * @return List of TmdbMovie from API or empty list if API key not configured
     */
    suspend fun searchTvShowsOnline(query: String, page: Int = 1): List<TmdbMovie> {
        return try {
            if (BuildConfig.TMDB_API_KEY.isEmpty()) {
                emptyList()
            } else {
                val response = tmdbApi.searchTvShows(
                    apiKey = BuildConfig.TMDB_API_KEY,
                    query = query,
                    page = page
                )
                response.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    /**
     * Search for both movies and TV shows using TMDB API
     * @param query Search query
     * @param page Page number for pagination
     * @return List of TmdbMovie from API or empty list if API key not configured
     */
    suspend fun searchMultiOnline(query: String, page: Int = 1): List<TmdbMovie> {
        return try {
            if (BuildConfig.TMDB_API_KEY.isEmpty()) {
                emptyList()
            } else {
                val response = tmdbApi.searchMulti(
                    apiKey = BuildConfig.TMDB_API_KEY,
                    query = query,
                    page = page
                )
                response.results
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    /**
     * Get detailed movie information from TMDB API
     * @param movieId TMDB movie ID
     * @return TmdbMovieDetails or null if error
     */
    suspend fun getMovieDetails(movieId: Int): TmdbMovieDetails? {
        return try {
            if (BuildConfig.TMDB_API_KEY.isEmpty()) {
                null
            } else {
                tmdbApi.getMovieDetails(
                    movieId = movieId,
                    apiKey = BuildConfig.TMDB_API_KEY
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    /**
     * Convert TmdbMovie from search results to MovieEntity
     */
    fun tmdbMovieToEntity(movie: TmdbMovie): MovieEntity {
        val title = movie.title ?: movie.name ?: "Unknown"
        val releaseDate = movie.releaseDate ?: movie.firstAirDate
        
        return MovieEntity(
            title = title,
            overview = movie.overview,
            posterImageUrl = TmdbApi.getImageUrl(movie.posterPath),
            backdropImageUrl = TmdbApi.getImageUrl(movie.backdropPath, TmdbApi.POSTER_SIZE_LARGE),
            tmdbId = movie.id.toString()
        )
    }
    
    /**
     * Convert TmdbMovieDetails to MovieEntity with complete information
     */
    fun tmdbDetailsToEntity(details: TmdbMovieDetails): MovieEntity {
        val title = details.title ?: details.name ?: "Unknown"
        val releaseDate = details.releaseDate ?: details.firstAirDate
        
        // Extract director from crew
        val director = details.credits?.crew?.firstOrNull { it.job == "Director" }?.name
        
        // Extract cast (top 5)
        val cast = details.credits?.cast
            ?.sortedBy { it.order }
            ?.take(5)
            ?.map { it.name }
            ?: emptyList()
        
        // Extract genre names
        val genres = details.genres?.map { it.name } ?: emptyList()
        
        return MovieEntity(
            title = title,
            director = director,
            runtime = details.runtime,
            overview = details.overview,
            posterImageUrl = TmdbApi.getImageUrl(details.posterPath),
            backdropImageUrl = TmdbApi.getImageUrl(details.backdropPath, TmdbApi.POSTER_SIZE_LARGE),
            cast = cast,
            genres = genres,
            tmdbId = details.id.toString(),
            imdbId = details.imdbId
        )
    }
}
