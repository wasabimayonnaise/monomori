package com.monomori

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class for Monomori (物守り - Guardian of Things)
 * 
 * This class serves as the entry point for the application and is responsible
 * for initializing Hilt dependency injection framework.
 */
@HiltAndroidApp
class MonomoriApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        // Application-level initialization can be added here
    }
}
