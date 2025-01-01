package com.fadymarty.catopia.presentation.favorite

import com.fadymarty.catopia.domain.model.Cat

data class FavoriteState(
    val catPictures: List<Cat> = emptyList(),
)