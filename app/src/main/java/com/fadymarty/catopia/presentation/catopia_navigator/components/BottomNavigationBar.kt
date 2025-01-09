package com.fadymarty.catopia.presentation.catopia_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (index == selected) item.selectedIcon
                            else item.unselectedIcon
                        ),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.label
                    )
                }
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val label: String,
)