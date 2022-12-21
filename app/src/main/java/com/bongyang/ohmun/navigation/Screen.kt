package com.bongyang.ohmun.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object Details: Screen("details_screen/{movieId}") {
        fun passMovieId(movieId: Int): String {
            return "details_screen/$movieId"
        }
    }
    object Search: Screen("search_screen")
}
