package com.monomori.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monomori.ui.screens.home.HomeScreen
import com.monomori.ui.screens.settings.SettingsScreen
import com.monomori.ui.screens.collection.CollectionScreen

/**
 * Main navigation graph for Monomori
 */
@Composable
fun MonomoriNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },
                onNavigateToCategory = { category ->
                    navController.navigate(Screen.Collections.createRoute(category))
                }
            )
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Collections.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            CollectionScreen(
                category = category,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onAddItem = {
                    // Add item functionality coming soon
                }
            )
        }
    }
}
