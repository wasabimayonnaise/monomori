package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.ItemCondition
import com.monomori.data.model.MovieSubcategory
import java.util.Date
import java.util.UUID

/**
 * Room entity for Movies & TV collection (DVD, Blu-ray, 4K UHD, VHS)
 */
@Entity(tableName = "movies")
@TypeConverters(Converters::class)
data class MovieEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.MOVIES_TV,
    val subcategory: MovieSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Movie-specific fields
    val title: String,
    val director: String? = null,
    val studio: String? = null,
    val distributor: String? = null,
    val regionCode: String? = null,
    val upc: String? = null,
    val runtime: Int? = null, // in minutes
    val genre: String? = null,
    val theatricalReleaseDate: Date? = null,
    val homeVideoReleaseDate: Date? = null,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val condition: ItemCondition? = null,
    val tmdbId: String? = null,
    val imdbId: String? = null,
    val japaneseTitle: String? = null,
    val notes: String? = null,
    
    // Additional fields for API integration
    val overview: String? = null,
    val posterImageUrl: String? = null,
    val backdropImageUrl: String? = null,
    val cast: List<String> = emptyList(),
    val genres: List<String> = emptyList(),
    val rating: Float? = null, // User rating 0.0-5.0
    val watchStatus: com.monomori.data.model.WatchStatus? = null,
    val format: String? = null // DVD, Blu-ray, Digital, 4K
)
