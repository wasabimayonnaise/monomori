package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.GameSubcategory
import com.monomori.data.model.ItemCondition
import java.util.Date
import java.util.UUID

/**
 * Room entity for Video Games collection
 * Includes games, consoles, accessories, and limited editions
 */
@Entity(tableName = "video_games")
@TypeConverters(Converters::class)
data class VideoGameEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.VIDEO_GAMES,
    val subcategory: GameSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Video game-specific fields
    val title: String,
    val platform: String? = null,
    val publisher: String? = null,
    val developer: String? = null,
    val upc: String? = null,
    val genre: String? = null,
    val releaseDate: Date? = null,
    val region: String? = null,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val discCartCondition: ItemCondition? = null,
    val caseCondition: ItemCondition? = null,
    val manualCondition: ItemCondition? = null,
    val isCompleteInBox: Boolean = false,
    val notes: String? = null,
    
    // Additional fields for API integration
    val coverImageUrl: String? = null,
    val genres: List<String> = emptyList(),
    val description: String? = null,
    val rating: Float? = null, // User rating 0.0-5.0
    val playStatus: com.monomori.data.model.PlayStatus? = null
)
