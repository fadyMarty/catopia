package com.fadymarty.catopia.presentation.cat_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.catopia.R
import com.fadymarty.catopia.domain.model.Cat
import com.fadymarty.catopia.domain.use_case.cat_pictures.CatPicturesUseCases
import com.fadymarty.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val catPicturesUseCases: CatPicturesUseCases,
) : ViewModel() {

    private val _catListState = mutableStateOf(CatListState())
    val catListState: State<CatListState> = _catListState

    private val _errorImageState = mutableStateOf(R.drawable.error_1)
    val errorImageState: State<Int> = _errorImageState

    val errorImages = listOf(
        R.drawable.error_1,
        R.drawable.error_2,
        R.drawable.error_3,
        R.drawable.error_4,
        R.drawable.error_5
    )

    init {
        getCatPictures()
        _errorImageState.value = errorImages.random()
    }

    fun getCatPictures() {
        catPicturesUseCases.getCatPicturesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _catListState.value = CatListState(
                        catPictures = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _catListState.value = CatListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _catListState.value = CatListState(isLoading = true)
                }
            }
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
