package com.monomori.ui.navigation

/**
 * Navigation routes for the app
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Collections : Screen("collections/{category}") {
        fun createRoute(category: String) = "collections/$category"
    }
    object ItemDetail : Screen("item/{itemId}") {
        fun createRoute(itemId: Long) = "item/$itemId"
    }
    object AddItem : Screen("add_item/{category}") {
        fun createRoute(category: String) = "add_item/$category"
    }
    object Settings : Screen("settings")
    object Search : Screen("search")
}
