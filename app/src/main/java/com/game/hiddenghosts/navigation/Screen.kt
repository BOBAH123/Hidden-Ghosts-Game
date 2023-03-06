package com.game.hiddenghosts.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object GameField : Screen("game_field_screen")
    object Results : Screen("results_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("?level=$arg")
            }
        }
    }
}