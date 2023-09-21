package com.teddy.example.compose.navigation

import androidx.navigation.NavController

fun NavController.navigateMovieDetail(imdbID: String) {
    navigate("${MovieDetailRoute.route}/$imdbID")
}

object MovieDetailRoute {
    const val route = "movieDetail"
}