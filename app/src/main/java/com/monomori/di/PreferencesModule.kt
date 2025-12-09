package com.monomori.di

import android.content.Context
import com.monomori.data.preferences.ViewPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing preferences-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    
    @Provides
    @Singleton
    fun provideViewPreferences(
        @ApplicationContext context: Context
    ): ViewPreferences {
        return ViewPreferences(context)
    }
}
