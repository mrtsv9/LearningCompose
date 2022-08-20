package com.example.learningcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learningcompose.ui.progress_bar.CircularProgressBar

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.ProgressBarScreen.route) {
        composable(route = Screens.ProgressBarScreen.route) {
            CircularProgressBar(percentage = 1f)
        }
    }
}
