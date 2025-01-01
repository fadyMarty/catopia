package com.fadymarty.catopia.domain.use_case.cat_pictures

import com.fadymarty.catopia.data.mappers.toCat
import com.fadymarty.catopia.domain.model.Cat
import com.fadymarty.catopia.domain.repository.CatRepository
import com.fadymarty.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCatPicturesUseCase @Inject constructor(
    val catRepository: CatRepository,
) {

    operator fun invoke(): Flow<Resource<List<Cat>>> = flow {
        try {
            emit(Resource.Loading())
            val catPictures = catRepository.getCatPictures().map { it.toCat() }
            emit(Resource.Success(catPictures))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}