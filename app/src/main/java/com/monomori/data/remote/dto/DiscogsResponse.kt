package com.monomori.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Response model for Discogs API
 * Documentation: https://www.discogs.com/developers
 */
data class DiscogsSearchResponse(
    @SerializedName("pagination")
    val pagination: DiscogsPagination,
    @SerializedName("results")
    val results: List<DiscogsRelease>
)

data class DiscogsPagination(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("items")
    val items: Int
)

data class DiscogsRelease(
    @SerializedName("id")
    val id: Int,
    @SerializedName("master_id")
    val masterId: Int?,
    @SerializedName("type")
    val type: String, // "release", "master", "artist", "label"
    @SerializedName("title")
    val title: String,
    @SerializedName("country")
    val country: String?,
    @SerializedName("year")
    val year: String?,
    @SerializedName("format")
    val format: List<String>?,
    @SerializedName("label")
    val label: List<String>?,
    @SerializedName("genre")
    val genre: List<String>?,
    @SerializedName("style")
    val style: List<String>?,
    @SerializedName("barcode")
    val barcode: List<String>?,
    @SerializedName("catno")
    val catno: String?,
    @SerializedName("thumb")
    val thumb: String?,
    @SerializedName("cover_image")
    val coverImage: String?,
    @SerializedName("resource_url")
    val resourceUrl: String?,
    @SerializedName("uri")
    val uri: String?
)

data class DiscogsReleaseDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("artists")
    val artists: List<DiscogsArtist>?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("styles")
    val styles: List<String>?,
    @SerializedName("labels")
    val labels: List<DiscogsLabel>?,
    @SerializedName("formats")
    val formats: List<DiscogsFormat>?,
    @SerializedName("tracklist")
    val tracklist: List<DiscogsTrack>?,
    @SerializedName("images")
    val images: List<DiscogsImage>?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("identifiers")
    val identifiers: List<DiscogsIdentifier>?,
    @SerializedName("uri")
    val uri: String?
)

data class DiscogsArtist(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String,
    @SerializedName("anv")
    val anv: String?,
    @SerializedName("join")
    val join: String?,
    @SerializedName("role")
    val role: String?
)

data class DiscogsLabel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String,
    @SerializedName("catno")
    val catno: String?
)

data class DiscogsFormat(
    @SerializedName("name")
    val name: String,
    @SerializedName("qty")
    val qty: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("descriptions")
    val descriptions: List<String>?
)

data class DiscogsTrack(
    @SerializedName("position")
    val position: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("duration")
    val duration: String?
)

data class DiscogsImage(
    @SerializedName("type")
    val type: String, // "primary" or "secondary"
    @SerializedName("uri")
    val uri: String,
    @SerializedName("uri150")
    val uri150: String?,
    @SerializedName("width")
    val width: Int?,
    @SerializedName("height")
    val height: Int?
)

data class DiscogsIdentifier(
    @SerializedName("type")
    val type: String, // "Barcode", "Matrix / Runout", etc.
    @SerializedName("value")
    val value: String,
    @SerializedName("description")
    val description: String?
)
