package com.fadymarty.catopia.presentation.cat_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fadymarty.catopia.presentation.components.CatItem
import com.fadymarty.catopia.presentation.components.ErrorScreen

@Composable
fun CatListScreen(
    viewModel: CatListViewModel = hiltViewModel(),
    contentPadding: Dp = 0.dp,
) {

    val catListState = viewModel.catListState.value
    val errorImageState = viewModel.errorImageState.value



    LazyVerticalStaggeredGrid(
        contentPadding = PaddingValues(top = contentPadding),
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2)
    ) {
        items(catListState.catPictures) { catPicture ->
            CatItem(
                cat = catPicture,
                onBookmarkClick = {
                    viewModel.selectDeleteCatPhoto(catPicture)
                }
            )
        }
    }

    if (catListState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (catListState.error.isNotBlank()) {
        ErrorScreen(
            title = "No Internet Connection",
            description = "It seems our cats are taking a break while waiting for the internet. Check your connection and try again!",
            errorImage = errorImageState,
            onClick = { viewModel.getCatPictures() },
            buttonText = "Try Again"
        )
    }
}