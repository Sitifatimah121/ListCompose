package com.jetpackproject.jetmovie.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profil: Screen("profil")
    object DetailMovie: Screen("home/{movieId}"){
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}