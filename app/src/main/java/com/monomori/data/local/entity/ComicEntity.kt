package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.ItemCondition
import com.monomori.data.model.ReadStatus
import java.util.Date
import java.util.UUID

/**
 * Room entity for Comics collection
 * Specialized entity for comic books with issue numbers, series tracking, and artist information
 */
@Entity(tableName = "comics")
@TypeConverters(Converters::class)
data class ComicEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.BOOKS,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Comic-specific fields
    val title: String,
    val issueNumber: String? = null, // Can be "#1", "Annual #1", etc.
    val series: String? = null,
    val volumeNumber: Int? = null,
    val authors: List<String> = emptyList(), // Writers
    val artists: List<String> = emptyList(), // Pencillers, inkers, colorists
    val publisher: String? = null,
    val publishDate: Date? = null,
    val coverImageUrl: String? = null,
    val isbn: String? = null,
    val upc: String? = null,
    val pageCount: Int? = null,
    val description: String? = null,
    val genres: List<String> = emptyList(),
    val characters: List<String> = emptyList(),
    val storyArcs: List<String> = emptyList(),
    val condition: ItemCondition? = null,
    val graded: Boolean = false,
    val gradingService: String? = null, // CGC, CBCS, etc.
    val grade: String? = null, // "9.8", "NM+", etc.
    val variant: String? = null, // "Variant A", "1:25 Incentive", etc.
    val firstAppearance: String? = null, // Notable first appearances
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val estimatedValue: Double? = null,
    val notes: String? = null,
    val rating: Float? = null, // User rating 0.0-5.0
    val readStatus: ReadStatus? = null
)
