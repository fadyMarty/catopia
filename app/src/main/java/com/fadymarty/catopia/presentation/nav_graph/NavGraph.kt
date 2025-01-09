package com.fadymarty.catopia.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.fadymarty.catopia.presentation.catopia_navigator.CatopiaNavigatorScreen
import com.fadymarty.catopia.presentation.onboarding.OnBoardingScreen

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Screen.AppStartNavigation.route,
            startDestination = Screen.OnBoardingScreen.route
        ) {
            composable(route = Screen.OnBoardingScreen.route) {
                OnBoardingScreen()
            }
        }

        navigation(
            route = Screen.CatPicturesNavigation.route,
            startDestination = Screen.CatPicturesNavigatorScreen.route
        ) {
            composable(route = Screen.CatPicturesNavigatorScreen.route) {
                CatopiaNavigatorScreen()
            }
        }
    }
}