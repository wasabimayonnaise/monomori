package com.monomori.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.monomori.ui.screens.home.HomeScreen
import com.monomori.ui.screens.settings.SettingsScreen
import com.monomori.ui.screens.collection.CollectionScreen
import com.monomori.ui.screens.collection.BookDetailScreen
import com.monomori.ui.screens.additem.AddItemScreen

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
                onNavigateBack = { navController.popBackStack() },
                onAddItem = { navController.navigate(Screen.AddItem.createRoute(category)) },
                onBookClick = { bookId ->
                    navController.navigate(Screen.ItemDetail.createRoute(bookId))
                }
            )
        }

        composable(Screen.AddItem.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            AddItemScreen(
                category = category,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSaveItem = { name, notes ->
                    // For now just go back - we'll save to database later!
                    navController.popBackStack()
                }
            )
        }

        // ********* THIS IS THE NEW ROUTE FOR ITEM DETAIL *********
        composable(
            route = Screen.ItemDetail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getLong("itemId")
            if (itemId == null) return@composable // Prevent navigation if ID is missing

            BookDetailScreen(
                itemId = itemId,
                onNavigateBack = { navController.popBackStack() },
                onEdit = { /* implement edit as needed */ }
            )
        }
        // **********************************************************
    }
}