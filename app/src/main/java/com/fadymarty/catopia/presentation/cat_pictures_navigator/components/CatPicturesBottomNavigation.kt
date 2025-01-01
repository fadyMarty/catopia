package com.fadymarty.catopia.presentation.cat_pictures_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun CatPicturesBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.text
                    )
                }
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String,
)