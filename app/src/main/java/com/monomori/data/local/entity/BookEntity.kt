package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.BookSubcategory
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.ItemCondition
import java.util.Date
import java.util.UUID

/**
 * Room entity for Books collection
 * 
 * Includes fields specific to books: Fiction, Non-Fiction, Manga, Art Books, Comics, Graphic Novels
 */
@Entity(tableName = "books")
@TypeConverters(Converters::class)
data class BookEntity(
    @PrimaryKey
    val id: Long = 0L,
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.BOOKS,
    val subcategory: BookSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Book-specific fields
    val title: String,
    val authors: List<String> = emptyList(),
    val publisher: String? = null,
    val isbn: String? = null,
    val pageCount: Int? = null,
    val genre: String? = null,
    val series: String? = null,
    val volumeNumber: Int? = null,
    val language: String? = null,
    val originalLanguage: String? = null,
    val japaneseTitle: String? = null,
    val releaseDate: Date? = null,
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val condition: ItemCondition? = null,
    val synopsis: String? = null,
    val notes: String? = null,
    
    // Additional fields for API integration
    val description: String? = null,
    val coverImageUrl: String? = null,
    val rating: Float? = null, // User rating 0.0-5.0
    val readStatus: com.monomori.data.model.ReadStatus? = null
)
