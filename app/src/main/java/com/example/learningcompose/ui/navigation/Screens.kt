package com.example.learningcompose.ui.navigation

sealed class Screens(val route: String) {
    object ProgressBarScreen: Screens("progress_bar")
}
