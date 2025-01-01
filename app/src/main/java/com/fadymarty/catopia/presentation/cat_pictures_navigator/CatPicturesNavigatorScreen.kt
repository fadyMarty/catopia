package com.fadymarty.catopia.presentation.cat_pictures_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fadymarty.catopia.R
import com.fadymarty.catopia.presentation.cat_list.CatListScreen
import com.fadymarty.catopia.presentation.cat_pictures_navigator.components.BottomNavigationItem
import com.fadymarty.catopia.presentation.cat_pictures_navigator.components.CatPicturesBottomNavigation
import com.fadymarty.catopia.presentation.favorite.FavoriteScreen
import com.fadymarty.catopia.presentation.nav_graph.Route

@Composable
fun CatPicturesNavigatorScreen() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_cat_pictures, text = "Pictures"),
            BottomNavigationItem(icon = R.drawable.ic_favorite, text = "Favorites")
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember(backstackState) {
        when (backstackState?.destination?.route) {
            Route.CatListScreen.route -> 0
            Route.FavoriteScreen.route -> 1
            else -> 0
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CatPicturesBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTap(
                            navController = navController,
                            route = Route.CatListScreen.route
                        )

                        1 -> navigateToTap(
                            navController = navController,
                            route = Route.FavoriteScreen.route
                        )
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Route.CatListScreen.route,
            modifier = Modifier
                .padding(bottom = padding.calculateBottomPadding())
        ) {
            composable(route = Route.CatListScreen.route) {
                CatListScreen(contentPadding = padding.calculateTopPadding())
            }

            composable(route = Route.FavoriteScreen.route) {
                FavoriteScreen(
                    onErrorButtonClick = {
                        navigateToTap(
                            navController = navController,
                            route = Route.CatListScreen.route
                        )
                    },
                    contentPadding = padding.calculateTopPadding()
                )
            }
        }
    }
}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}