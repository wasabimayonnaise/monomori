package com.monomori.di

import android.content.Context
import androidx.room.Room
import com.monomori.data.local.dao.*
import com.monomori.data.local.database.MonomoriDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing database-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideMonomoriDatabase(
        @ApplicationContext context: Context
    ): MonomoriDatabase {
        return Room.databaseBuilder(
            context,
            MonomoriDatabase::class.java,
            MonomoriDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // TODO: Replace with proper migrations in production
            .build()
    }
    
    @Provides
    @Singleton
    fun provideBookDao(database: MonomoriDatabase): BookDao {
        return database.bookDao()
    }
    
    @Provides
    @Singleton
    fun provideFigureDao(database: MonomoriDatabase): FigureDao {
        return database.figureDao()
    }
    
    @Provides
    @Singleton
    fun provideMusicDao(database: MonomoriDatabase): MusicDao {
        return database.musicDao()
    }
    
    @Provides
    @Singleton
    fun provideMovieDao(database: MonomoriDatabase): MovieDao {
        return database.movieDao()
    }
    
    @Provides
    @Singleton
    fun provideVideoGameDao(database: MonomoriDatabase): VideoGameDao {
        return database.videoGameDao()
    }
    
    @Provides
    @Singleton
    fun provideTradingCardDao(database: MonomoriDatabase): TradingCardDao {
        return database.tradingCardDao()
    }
    
    @Provides
    @Singleton
    fun provideModelKitDao(database: MonomoriDatabase): ModelKitDao {
        return database.modelKitDao()
    }
    
    @Provides
    @Singleton
    fun provideMagazineDao(database: MonomoriDatabase): MagazineDao {
        return database.magazineDao()
    }
    
    @Provides
    @Singleton
    fun provideArtPrintDao(database: MonomoriDatabase): ArtPrintDao {
        return database.artPrintDao()
    }
    
    @Provides
    @Singleton
    fun provideCustomItemDao(database: MonomoriDatabase): CustomItemDao {
        return database.customItemDao()
    }
}
