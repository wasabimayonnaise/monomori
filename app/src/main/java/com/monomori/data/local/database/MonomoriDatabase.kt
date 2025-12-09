package com.monomori.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.monomori.data.local.dao.*
import com.monomori.data.local.entity.*

/**
 * Room Database for Monomori application
 * 
 * Contains all collection entities and provides DAOs for accessing them.
 * Database version 1 - initial schema.
 * 
 * TODO: When schema changes are needed, increment version and implement Migration
 */
@Database(
    entities = [
        BookEntity::class,
        FigureEntity::class,
        MusicEntity::class,
        MovieEntity::class,
        VideoGameEntity::class,
        TradingCardEntity::class,
        ModelKitEntity::class,
        MagazineEntity::class,
        ArtPrintEntity::class,
        CustomItemEntity::class,
        ComicEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class MonomoriDatabase : RoomDatabase() {
    
    // DAOs
    abstract fun bookDao(): BookDao
    abstract fun figureDao(): FigureDao
    abstract fun musicDao(): MusicDao
    abstract fun movieDao(): MovieDao
    abstract fun videoGameDao(): VideoGameDao
    abstract fun tradingCardDao(): TradingCardDao
    abstract fun modelKitDao(): ModelKitDao
    abstract fun magazineDao(): MagazineDao
    abstract fun artPrintDao(): ArtPrintDao
    abstract fun customItemDao(): CustomItemDao
    abstract fun comicDao(): ComicDao
    
    companion object {
        const val DATABASE_NAME = "monomori_database"
    }
}
