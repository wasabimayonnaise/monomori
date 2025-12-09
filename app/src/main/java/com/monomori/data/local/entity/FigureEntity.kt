package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.FigureSubcategory
import com.monomori.data.model.ItemCondition
import java.util.Date
import java.util.UUID

/**
 * Room entity for Figures & Statues collection
 * 
 * Includes various scale figures, Nendoroids, Prize figures, Figma, Action figures, Funko Pop
 */
@Entity(tableName = "figures")
@TypeConverters(Converters::class)
data class FigureEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.FIGURES,
    val subcategory: FigureSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Figure-specific fields
    val character: String,
    val seriesAnime: String? = null,
    val manufacturer: String? = null, // Good Smile, Kadokawa, Kotobukaya, etc.
    val scale: String? = null, // 1/4, 1/6, 1/7, 1/8, etc.
    val sculptor: String? = null,
    val releaseDate: Date? = null,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val retailer: String? = null, // AmiAmi, etc.
    val boxCondition: ItemCondition? = null,
    val figureCondition: ItemCondition? = null,
    val displayLocation: String? = null,
    val originalJapaneseName: String? = null,
    val mfcUrl: String? = null, // MyFigureCollection URL
    val notes: String? = null
)
