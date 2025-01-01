package com.fadymarty.catopia.domain.use_case.cat_pictures

data class CatPicturesUseCases(
    val getCatPicturesUseCase: GetCatPicturesUseCase,
    val upsertCatPictureUseCase: UpsertCatPictureUseCase,
    val deleteCatPictureUseCase: DeleteCatPictureUseCase,
    val selectCatPicturesUseCase: SelectCatPicturesUseCase,
    val selectCatPictureUseCase: SelectCatPictureUseCase,
)