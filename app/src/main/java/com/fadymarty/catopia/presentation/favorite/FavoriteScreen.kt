package com.fadymarty.catopia.presentation.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fadymarty.catopia.presentation.components.CatItem
import com.fadymarty.catopia.presentation.components.ErrorScreen

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onErrorButtonClick: () -> Unit,
    contentPadding: Dp = 0.dp,
) {

    val favoriteState = viewModel.favoriteState.value
    val errorImageState = viewModel.errorImageState.value

    LazyVerticalStaggeredGrid(
        contentPadding = PaddingValues(top = contentPadding),
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2)
    ) {
        items(favoriteState.catPictures) { catPicture ->
            CatItem(
                cat = catPicture,
                onBookmarkClick = {
                    viewModel.selectDeleteCatPhoto(catPicture)
                }
            )
        }
    }

    if (favoriteState.catPictures.isEmpty()) {
        ErrorScreen(
            title = "No Favorites Yet",
            description = "Your favorite cats will appear here. Start exploring and tap the heart icon to save the ones you love!",
            errorImage = errorImageState,
            onClick = onErrorButtonClick,
            buttonText = "Discover Cats"
        )
    }
}