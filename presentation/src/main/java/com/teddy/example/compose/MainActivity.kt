package com.teddy.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.teddy.example.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

//Android hilt EntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {}

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "mainScreen"
                ) {
                    composable(
                        route = "${com.teddy.example.compose.navigation.MovieDetailRoute.route}/{imdbID}",
                        arguments = listOf(navArgument("imdbID") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val imdbID =
                            backStackEntry.arguments?.getString("imdbID").toString()
                        MovieDetailScreen(imdbID = imdbID)
                    }

                    composable(route = com.teddy.example.compose.navigation.MainScreenRoute.route) {
                        MainScreen(navController)
                    }
                }
            }
        }
    }
}