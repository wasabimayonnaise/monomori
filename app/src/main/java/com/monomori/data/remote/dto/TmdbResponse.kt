package com.monomori.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Response model for TMDB (The Movie Database) API
 * Documentation: https://developers.themoviedb.org/3
 */
data class TmdbSearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TmdbMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class TmdbMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("name")
    val name: String?, // For TV shows
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("original_name")
    val originalName: String?, // For TV shows
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?, // For TV shows
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("media_type")
    val mediaType: String? // "movie" or "tv"
)

data class TmdbMovieDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("name")
    val name: String?, // For TV shows
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>?,
    @SerializedName("genres")
    val genres: List<TmdbGenre>?,
    @SerializedName("credits")
    val credits: TmdbCredits?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("status")
    val status: String?
)

data class TmdbGenre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class TmdbCredits(
    @SerializedName("cast")
    val cast: List<TmdbCastMember>?,
    @SerializedName("crew")
    val crew: List<TmdbCrewMember>?
)

data class TmdbCastMember(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("character")
    val character: String?,
    @SerializedName("order")
    val order: Int?
)

data class TmdbCrewMember(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("department")
    val department: String
)
