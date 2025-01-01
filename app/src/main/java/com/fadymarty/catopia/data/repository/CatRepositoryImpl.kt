package com.fadymarty.catopia.data.repository

import com.fadymarty.catopia.data.local.CatDao
import com.fadymarty.catopia.data.local.entity.CatEntity
import com.fadymarty.catopia.data.remote.TheCatApi
import com.fadymarty.catopia.data.remote.dto.CatDto
import com.fadymarty.catopia.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow

class CatRepositoryImpl(
    private val theCatApi: TheCatApi,
    private val catDao: CatDao,
) : CatRepository {

    override suspend fun getCatPictures(): List<CatDto> {
        return theCatApi.getCatPictures()
    }

    override suspend fun upsertCatPicture(cat: CatEntity) {
        catDao.upsertCatPicture(cat)
    }

    override suspend fun deleteCatPicture(cat: CatEntity) {
        catDao.deleteCatPicture(cat)
    }

    override fun selectCatPictures(): Flow<List<CatEntity>> {
        return catDao.selectCatPictures()
    }

    override suspend fun selectCatPicture(id: String): CatEntity? {
        return catDao.selectCatPicture(id)
    }
}