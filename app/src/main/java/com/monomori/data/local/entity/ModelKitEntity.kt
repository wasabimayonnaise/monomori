package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.BuildStatus
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.ModelSubcategory
import java.util.Date
import java.util.UUID

/**
 * Room entity for Model Kits collection
 * Includes Gunpla, Scale Models (cars, planes, tanks), Miniatures/Warhammer
 */
@Entity(tableName = "model_kits")
@TypeConverters(Converters::class)
data class ModelKitEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.MODEL_KITS,
    val subcategory: ModelSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Model kit-specific fields
    val kitName: String,
    val seriesSource: String? = null,
    val manufacturer: String? = null, // Bandai, Tamiya, etc.
    val scaleGrade: String? = null, // HG, MG, PG, RG for Gunpla or 1/35, 1/48 for scale models
    val releaseDate: Date? = null,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val buildStatus: BuildStatus? = null,
    val isPainted: Boolean = false,
    val notes: String? = null
)
