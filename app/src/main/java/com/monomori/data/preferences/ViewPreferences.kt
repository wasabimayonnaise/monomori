package com.monomori.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.monomori.data.model.CollectionCategory
import com.monomori.data.model.ViewMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStore for view preferences
 * Stores user preferences for view mode per category
 */
class ViewPreferences(private val context: Context) {
    
    companion object {
        private val Context.dataStore by preferencesDataStore(name = "view_preferences")
        
        // Keys for view mode preferences (one per category)
        private fun viewModeKey(category: CollectionCategory) = 
            stringPreferencesKey("view_mode_${category.name}")
        
        private const val DEFAULT_VIEW_MODE = "CARD"
    }
    
    /**
     * Get view mode for a specific category
     */
    fun getViewMode(category: CollectionCategory): Flow<ViewMode> {
        return context.dataStore.data.map { preferences ->
            val modeString = preferences[viewModeKey(category)] ?: DEFAULT_VIEW_MODE
            try {
                ViewMode.valueOf(modeString)
            } catch (e: IllegalArgumentException) {
                ViewMode.CARD
            }
        }
    }
    
    /**
     * Save view mode for a specific category
     */
    suspend fun setViewMode(category: CollectionCategory, mode: ViewMode) {
        context.dataStore.edit { preferences ->
            preferences[viewModeKey(category)] = mode.name
        }
    }
    
    /**
     * Get view mode for all categories
     */
    fun getAllViewModes(): Flow<Map<CollectionCategory, ViewMode>> {
        return context.dataStore.data.map { preferences ->
            CollectionCategory.entries.associateWith { category ->
                val modeString = preferences[viewModeKey(category)] ?: DEFAULT_VIEW_MODE
                try {
                    ViewMode.valueOf(modeString)
                } catch (e: IllegalArgumentException) {
                    ViewMode.CARD
                }
            }
        }
    }
}
