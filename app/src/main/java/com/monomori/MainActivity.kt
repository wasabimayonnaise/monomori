package com.monomori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.monomori.ui.navigation.MonomoriNavigation
import com.monomori.ui.theme.MonomoriTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity for Monomori application
 * 
 * This is the single activity that hosts all Compose screens using Jetpack Navigation.
 * Implements MVVM architecture with Hilt for dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            MonomoriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MonomoriNavigation()
                }
            }
        }
    }
}
