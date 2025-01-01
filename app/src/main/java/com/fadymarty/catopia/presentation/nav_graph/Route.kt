package com.fadymarty.catopia.presentation.nav_graph

sealed class Route(val route: String) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object CatListScreen : Route(route = "catListScreen")
    object FavoriteScreen : Route(route = "bookmarkScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object CatPicturesNavigation : Route(route = "catPicturesNavigation")
    object CatPicturesNavigatorScreen : Route(route = "catPicturesNavigatorScreen")
}