package com.fadymarty.catopia.presentation.cat_list

import com.fadymarty.catopia.domain.model.Cat

data class CatListState(
    val catPictures: List<Cat> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)