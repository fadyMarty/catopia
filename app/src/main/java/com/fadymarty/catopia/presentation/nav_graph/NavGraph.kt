package com.fadymarty.catopia.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.fadymarty.catopia.presentation.catopia_navigator.CatPicturesNavigatorScreen
import com.fadymarty.catopia.presentation.onboarding.OnBoardingScreen

@Composable
fun NavGraph(
    startDestination: Screen
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation<Screen.AppStartNavigation>(
            startDestination = Screen.OnBoardingScreen
        ) {
            composable<Screen.OnBoardingScreen> {
                OnBoardingScreen()
            }
        }

        navigation<Screen.CatPicturesNavigation>(
            startDestination = Screen.CatPicturesNavigatorScreen
        ) {
            composable<Screen.CatPicturesNavigatorScreen> {
                CatPicturesNavigatorScreen()
            }
        }
    }
}