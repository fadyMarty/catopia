package com.fadymarty.catopia.domain.use_case.cat_pictures

import com.fadymarty.catopia.data.mappers.toCatEntity
import com.fadymarty.catopia.domain.model.Cat
import com.fadymarty.catopia.domain.repository.CatRepository

class DeleteCatPictureUseCase(
    private val catRepository: CatRepository,
) {

    suspend operator fun invoke(cat: Cat) {
        catRepository.deleteCatPicture(cat.toCatEntity())
    }
}