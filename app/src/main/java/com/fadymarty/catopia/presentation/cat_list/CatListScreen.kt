package com.fadymarty.catopia.presentation.cat_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fadymarty.catopia.domain.model.Cat
import com.fadymarty.catopia.presentation.components.CatItem
import com.fadymarty.catopia.presentation.components.ErrorImageState
import com.fadymarty.catopia.presentation.components.ErrorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    catListState: CatListState,
    errorImageState: ErrorImageState,
    selectDeleteCatPicture: (Cat) -> Unit,
    getCatPictures: () -> Unit,
    contentPadding: Dp = 0.dp,
) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            contentPadding = PaddingValues(top = contentPadding),
            modifier = Modifier.fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2)
        ) {
            items(catListState.catPictures) { catPicture ->
                CatItem(
                    cat = catPicture,
                    onBookmarkClick = {
                        selectDeleteCatPicture(catPicture)
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
                errorImage = errorImageState.errorImage,
                onClick = getCatPictures,
                buttonText = "Try Again"
            )
        }
    }
}