package com.fadymarty.catopia.presentation.nav_graph

sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen(route = "onBoardingScreen")
    object CatListScreen : Screen(route = "catListScreen")
    object FavoriteScreen : Screen(route = "bookmarkScreen")
    object AppStartNavigation : Screen(route = "appStartNavigation")
    object CatPicturesNavigation : Screen(route = "catPicturesNavigation")
    object CatPicturesNavigatorScreen : Screen(route = "catPicturesNavigatorScreen")
}