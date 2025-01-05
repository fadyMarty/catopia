package com.fadymarty.catopia.presentation.nav_graph

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object OnBoardingScreen : Screen
    @Serializable
    data object CatListScreen : Screen
    @Serializable
    data object FavoriteScreen : Screen
    @Serializable
    data object AppStartNavigation : Screen
    @Serializable
    data object CatPicturesNavigation : Screen
    @Serializable
    data object CatPicturesNavigatorScreen : Screen
}