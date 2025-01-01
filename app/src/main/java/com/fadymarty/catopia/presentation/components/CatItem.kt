package com.fadymarty.catopia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.fadymarty.catopia.R
import com.fadymarty.catopia.domain.model.Cat

@Composable
fun CatItem(
    cat: Cat,
    onBookmarkClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cat.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            success = {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    painter = it.painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = onBookmarkClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_favorite),
                        contentDescription = "Add image to bookmarks"
                    )
                }
            }
        )
    }
}