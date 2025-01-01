package com.fadymarty.catopia.domain.use_case.cat_pictures

import com.fadymarty.catopia.data.mappers.toCat
import com.fadymarty.catopia.domain.model.Cat
import com.fadymarty.catopia.domain.repository.CatRepository

class SelectCatPictureUseCase(
    private val catRepository: CatRepository,
) {

    suspend operator fun invoke(id: String): Cat? {
        return catRepository.selectCatPicture(id)?.toCat()
    }
}