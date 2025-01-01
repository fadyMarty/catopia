package com.fadymarty.catopia.domain.use_case.cat_pictures

import com.fadymarty.catopia.data.mappers.toCat
import com.fadymarty.catopia.domain.model.Cat
import com.fadymarty.catopia.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SelectCatPicturesUseCase(
    private val catRepository: CatRepository,
) {

    operator fun invoke(): Flow<List<Cat>> {
        return catRepository.selectCatPictures().map { it.map { it.toCat() } }
    }
}