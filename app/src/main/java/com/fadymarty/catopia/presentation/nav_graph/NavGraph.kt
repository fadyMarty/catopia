package com.fadymarty.catopia.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.fadymarty.catopia.presentation.cat_pictures_navigator.CatPicturesNavigatorScreen
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
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                OnBoardingScreen()
            }
        }

        navigation(
            route = Route.CatPicturesNavigation.route,
            startDestination = Route.CatPicturesNavigatorScreen.route
        ) {
            composable(route = Route.CatPicturesNavigatorScreen.route) {
                CatPicturesNavigatorScreen()
            }
        }
    }
}