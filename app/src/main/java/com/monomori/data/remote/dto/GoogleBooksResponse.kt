package com.monomori.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Response model for Google Books API
 * Documentation: https://developers.google.com/books/docs/v1/reference/volumes
 */
data class GoogleBooksResponse(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("items")
    val items: List<BookVolume>?
)

data class BookVolume(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo,
    @SerializedName("saleInfo")
    val saleInfo: SaleInfo?
)

data class VolumeInfo(
    @SerializedName("title")
    val title: String,
    @SerializedName("authors")
    val authors: List<String>?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifier>?,
    @SerializedName("pageCount")
    val pageCount: Int?,
    @SerializedName("categories")
    val categories: List<String>?,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinks?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("previewLink")
    val previewLink: String?,
    @SerializedName("infoLink")
    val infoLink: String?
)

data class IndustryIdentifier(
    @SerializedName("type")
    val type: String, // "ISBN_10" or "ISBN_13"
    @SerializedName("identifier")
    val identifier: String
)

data class ImageLinks(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("large")
    val large: String?,
    @SerializedName("extraLarge")
    val extraLarge: String?
)

data class SaleInfo(
    @SerializedName("country")
    val country: String?,
    @SerializedName("saleability")
    val saleability: String?,
    @SerializedName("listPrice")
    val listPrice: Price?,
    @SerializedName("retailPrice")
    val retailPrice: Price?
)

data class Price(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("currencyCode")
    val currencyCode: String?
)
