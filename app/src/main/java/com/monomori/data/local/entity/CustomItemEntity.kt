package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import java.util.Date
import java.util.UUID

/**
 * Room entity for Custom collections
 * 
 * This entity allows users to create their own collection categories
 * with fully customizable fields using the customFields system
 */
@Entity(tableName = "custom_items")
@TypeConverters(Converters::class)
data class CustomItemEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.CUSTOM,
    val categoryName: String, // User-defined category name
    val subcategory: String? = null, // User-defined subcategory
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    
    // All fields for custom items are stored in customFields
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Basic required fields
    val name: String, // Primary name/title of the item
    val notes: String? = null
)
