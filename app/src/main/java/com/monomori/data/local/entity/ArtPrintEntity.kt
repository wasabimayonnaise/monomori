package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.ArtSubcategory
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import java.util.Date
import java.util.UUID

/**
 * Room entity for Art & Prints collection
 * Includes Posters, Limited Edition Prints, Original Art, Doujinshi
 */
@Entity(tableName = "art_prints")
@TypeConverters(Converters::class)
data class ArtPrintEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.ART_PRINTS,
    val subcategory: ArtSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Art-specific fields
    val title: String,
    val artist: String? = null,
    val seriesSource: String? = null, // if fan art
    val dimensions: String? = null, // e.g., "24x36 inches"
    val printNumber: String? = null, // e.g., "45/500"
    val medium: String? = null,
    val isSigned: Boolean = false,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val purchaseSource: String? = null,
    val isFramed: Boolean = false,
    val displayLocation: String? = null,
    val notes: String? = null
)
