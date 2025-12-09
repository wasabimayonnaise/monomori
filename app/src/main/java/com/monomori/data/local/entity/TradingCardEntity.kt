package com.monomori.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monomori.data.local.database.Converters
import com.monomori.data.model.CardSubcategory
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.ItemCondition
import java.util.Date
import java.util.UUID

/**
 * Room entity for Trading Cards collection
 * Includes Pokémon, Magic: The Gathering, Yu-Gi-Oh!, Sports Cards, Other TCG
 */
@Entity(tableName = "trading_cards")
@TypeConverters(Converters::class)
data class TradingCardEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Base fields
    val category: CollectionCategory = CollectionCategory.TRADING_CARDS,
    val subcategory: CardSubcategory? = null,
    val primaryImage: String? = null,
    val additionalImages: List<String> = emptyList(),
    val dateAdded: Date = Date(),
    val lastModified: Date = Date(),
    val tags: List<String> = emptyList(),
    val barcode: String? = null,
    val customFields: CustomFieldsData = CustomFieldsData(),
    
    // Trading card-specific fields
    val cardName: String,
    val setExpansion: String? = null,
    val cardNumber: String? = null,
    val rarity: String? = null,
    val condition: ItemCondition? = null,
    val gradingService: String? = null, // PSA, BGS, CGC
    val grade: String? = null,
    val language: String? = null,
    val edition: String? = null, // 1st edition, unlimited, etc.
    val purchaseDate: Date? = null,
    val purchasePrice: Double? = null,
    val estimatedValue: Double? = null,
    val quantityOwned: Int = 1,
    val notes: String? = null
)
