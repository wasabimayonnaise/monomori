package com.monomori.di

import com.monomori.data.remote.RetrofitClient
import com.monomori.data.remote.api.DiscogsApi
import com.monomori.data.remote.api.GoogleBooksApi
import com.monomori.data.remote.api.TmdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing network-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    @Singleton
    fun provideGoogleBooksApi(): GoogleBooksApi {
        return RetrofitClient.googleBooksApi
    }
    
    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbApi {
        return RetrofitClient.tmdbApi
    }
    
    @Provides
    @Singleton
    fun provideDiscogsApi(): DiscogsApi {
        return RetrofitClient.discogsApi
    }
}
