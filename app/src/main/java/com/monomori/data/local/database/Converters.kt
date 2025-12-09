package com.monomori.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.monomori.data.model.ArtSubcategory
import com.monomori.data.model.BookSubcategory
import com.monomori.data.model.BuildStatus
import com.monomori.data.model.CardSubcategory
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.CustomFieldsData
import com.monomori.data.model.FigureSubcategory
import com.monomori.data.model.GameSubcategory
import com.monomori.data.model.ItemCondition
import com.monomori.data.model.ListenStatus
import com.monomori.data.model.ModelSubcategory
import com.monomori.data.model.MovieSubcategory
import com.monomori.data.model.MusicSubcategory
import com.monomori.data.model.PlayStatus
import com.monomori.data.model.ReadStatus
import com.monomori.data.model.WatchStatus
import java.util.Date

/**
 * Room TypeConverters for complex data types
 * 
 * Converts complex objects to/from primitive types that Room can store
 */
class Converters {
    
    private val gson = Gson()
    
    // Date converters
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
    
    // String List converters
    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value ?: emptyList<String>())
    }
    
    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }
    
    // CustomFieldsData converters
    @TypeConverter
    fun fromCustomFieldsData(value: CustomFieldsData?): String {
        return gson.toJson(value ?: CustomFieldsData())
    }
    
    @TypeConverter
    fun toCustomFieldsData(value: String): CustomFieldsData {
        return try {
            gson.fromJson(value, CustomFieldsData::class.java) ?: CustomFieldsData()
        } catch (e: Exception) {
            CustomFieldsData()
        }
    }
    
    // CollectionCategory converters
    @TypeConverter
    fun fromCollectionCategory(value: CollectionCategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toCollectionCategory(value: String?): CollectionCategory? {
        return value?.let { 
            try {
                CollectionCategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // BookSubcategory converters
    @TypeConverter
    fun fromBookSubcategory(value: BookSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toBookSubcategory(value: String?): BookSubcategory? {
        return value?.let {
            try {
                BookSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // FigureSubcategory converters
    @TypeConverter
    fun fromFigureSubcategory(value: FigureSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toFigureSubcategory(value: String?): FigureSubcategory? {
        return value?.let {
            try {
                FigureSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // MusicSubcategory converters
    @TypeConverter
    fun fromMusicSubcategory(value: MusicSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toMusicSubcategory(value: String?): MusicSubcategory? {
        return value?.let {
            try {
                MusicSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // MovieSubcategory converters
    @TypeConverter
    fun fromMovieSubcategory(value: MovieSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toMovieSubcategory(value: String?): MovieSubcategory? {
        return value?.let {
            try {
                MovieSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // GameSubcategory converters
    @TypeConverter
    fun fromGameSubcategory(value: GameSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toGameSubcategory(value: String?): GameSubcategory? {
        return value?.let {
            try {
                GameSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // CardSubcategory converters
    @TypeConverter
    fun fromCardSubcategory(value: CardSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toCardSubcategory(value: String?): CardSubcategory? {
        return value?.let {
            try {
                CardSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // ModelSubcategory converters
    @TypeConverter
    fun fromModelSubcategory(value: ModelSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toModelSubcategory(value: String?): ModelSubcategory? {
        return value?.let {
            try {
                ModelSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // ArtSubcategory converters
    @TypeConverter
    fun fromArtSubcategory(value: ArtSubcategory?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toArtSubcategory(value: String?): ArtSubcategory? {
        return value?.let {
            try {
                ArtSubcategory.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // ItemCondition converters
    @TypeConverter
    fun fromItemCondition(value: ItemCondition?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toItemCondition(value: String?): ItemCondition? {
        return value?.let {
            try {
                ItemCondition.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // BuildStatus converters
    @TypeConverter
    fun fromBuildStatus(value: BuildStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toBuildStatus(value: String?): BuildStatus? {
        return value?.let {
            try {
                BuildStatus.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // ReadStatus converters
    @TypeConverter
    fun fromReadStatus(value: ReadStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toReadStatus(value: String?): ReadStatus? {
        return value?.let {
            try {
                ReadStatus.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // WatchStatus converters
    @TypeConverter
    fun fromWatchStatus(value: WatchStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toWatchStatus(value: String?): WatchStatus? {
        return value?.let {
            try {
                WatchStatus.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // ListenStatus converters
    @TypeConverter
    fun fromListenStatus(value: ListenStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toListenStatus(value: String?): ListenStatus? {
        return value?.let {
            try {
                ListenStatus.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
    
    // PlayStatus converters
    @TypeConverter
    fun fromPlayStatus(value: PlayStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toPlayStatus(value: String?): PlayStatus? {
        return value?.let {
            try {
                PlayStatus.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
}
