package com.teddy.example.compose.navigation

import androidx.navigation.NavController

fun NavController.navigateMainScreen() {
    navigate(MainScreenRoute.route)
}

object MainScreenRoute {
    const val route = "mainScreen"
}