package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.ItemCondition
import com.monomori.data.model.MusicSubcategory
import java.util.Date
import java.util.UUID

/**
 * Room entity for Music collection (Vinyl, CD, Cassette)
 */
@Entity(tableName = "music")
@TypeConverters(Converters::class)
data class MusicEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.MUSIC,
    val subcategory: MusicSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Music-specific fields
    val albumTitle: String,
    val artists: List<String> = emptyList(),
    val label: String? = null,
    val catalogNumber: String? = null,
    val upc: String? = null,
    val formatDetails: String? = null, // LP, 2xLP, 7", etc.
    val genre: String? = null,
    val releaseDate: Date? = null,
    val countryOfRelease: String? = null,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val mediaCondition: ItemCondition? = null,
    val sleeveCondition: ItemCondition? = null,
    val discogsUrl: String? = null,
    val tracklist: List<String> = emptyList(),
    val notes: String? = null,
    
    // Additional fields for API integration
    val coverImageUrl: String? = null,
    val year: Int? = null,
    val genres: List<String> = emptyList(),
    val rating: Float? = null, // User rating 0.0-5.0
    val listenStatus: com.monomori.data.model.ListenStatus? = null,
    val trackCount: Int? = null,
    val discogsId: Int? = null
)
