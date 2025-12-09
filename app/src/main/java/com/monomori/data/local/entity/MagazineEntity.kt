package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.ItemCondition
import java.util.Date
import java.util.UUID

/**
 * Room entity for Magazines collection
 */
@Entity(tableName = "magazines")
@TypeConverters(Converters::class)
data class MagazineEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.MAGAZINES,
    val subcategory: String? = null, // Magazines don't have predefined subcategories
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Magazine-specific fields
    val title: String,
    val issueNumber: String? = null,
    val publisher: String? = null,
    val issn: String? = null,
    val language: String? = null,
    val coverFeature: String? = null,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val condition: ItemCondition? = null,
    val notes: String? = null
)
