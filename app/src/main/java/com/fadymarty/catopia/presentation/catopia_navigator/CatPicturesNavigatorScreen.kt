package com.fadymarty.catopia.presentation.catopia_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fadymarty.catopia.R
import com.fadymarty.catopia.presentation.cat_list.CatListScreen
import com.fadymarty.catopia.presentation.cat_list.CatListViewModel
import com.fadymarty.catopia.presentation.catopia_navigator.components.BottomNavigationItem
import com.fadymarty.catopia.presentation.catopia_navigator.components.CatPicturesBottomNavigation
import com.fadymarty.catopia.presentation.favorite.FavoriteScreen
import com.fadymarty.catopia.presentation.favorite.FavoriteViewModel
import com.fadymarty.catopia.presentation.nav_graph.Screen

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
        mutableIntStateOf(0)
    }

    selectedItem = remember(backstackState) {
        when (backstackState?.destination) {
            Screen.CatListScreen -> 0
            Screen.FavoriteScreen -> 1
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
                            route = Screen.CatListScreen
                        )

                        1 -> navigateToTap(
                            navController = navController,
                            route = Screen.FavoriteScreen
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(
                    bottom = innerPadding.calculateBottomPadding()
                ),
            navController = navController,
            startDestination = Screen.CatListScreen
        ) {
            composable<Screen.CatListScreen> {
                val viewModel: CatListViewModel = hiltViewModel()

                CatListScreen(
                    catListState = viewModel.catListState.value,
                    errorImageState = viewModel.errorImageState.value,
                    selectDeleteCatPicture = viewModel::selectDeleteCatPhoto,
                    getCatPictures = viewModel::getCatPictures,
                    contentPadding = innerPadding.calculateTopPadding()
                )
            }

            composable<Screen.FavoriteScreen> {
                val viewModel: FavoriteViewModel = hiltViewModel()

                FavoriteScreen(
                    favoriteState = viewModel.favoriteState.value,
                    errorImageState = viewModel.errorImageState.value,
                    onErrorButtonClick = {
                        navigateToTap(
                            navController = navController,
                            route = Screen.CatListScreen
                        )
                    },
                    selectDeleteCatPicture = viewModel::selectDeleteCatPhoto,
                    contentPadding = innerPadding.calculateTopPadding(),
                )
            }
        }
    }
}

private fun navigateToTap(navController: NavController, route: Screen) {
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