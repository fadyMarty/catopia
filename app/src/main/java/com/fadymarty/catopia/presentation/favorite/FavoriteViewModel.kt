package com.fadymarty.catopia.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.catopia.R
import com.fadymarty.catopia.domain.model.Cat
import com.fadymarty.catopia.domain.use_case.cat_pictures.CatPicturesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val catPicturesUseCases: CatPicturesUseCases,
) : ViewModel() {

    private val _favoriteState = mutableStateOf(FavoriteState())
    val favoriteState: State<FavoriteState> = _favoriteState

    private val _errorImageState = mutableStateOf(R.drawable.error_1)
    val errorImageState: State<Int> = _errorImageState

    val errorImages = listOf(
        R.drawable.error_6,
        R.drawable.error_7,
        R.drawable.error_8,
        R.drawable.error_9,
        R.drawable.error_10
    )

    init {
        getCatPictures()
        _errorImageState.value = errorImages.random()
    }

    private fun getCatPictures() {
        catPicturesUseCases.selectCatPicturesUseCase().onEach {
            _favoriteState.value = _favoriteState.value.copy(catPictures = it)
        }.launchIn(viewModelScope)
    }

    fun selectDeleteCatPhoto(cat: Cat) {
        viewModelScope.launch {
            val catPicture = catPicturesUseCases.selectCatPictureUseCase(cat.id)
            if (catPicture == null) {
                catPicturesUseCases.upsertCatPictureUseCase(cat)
            } else {
                catPicturesUseCases.deleteCatPictureUseCase(cat)
            }
        }
    }
}