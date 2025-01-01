package com.fadymarty.catopia.domain.repository

import com.fadymarty.catopia.data.local.entity.CatEntity
import com.fadymarty.catopia.data.remote.dto.CatDto
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    suspend fun getCatPictures(): List<CatDto>

    suspend fun upsertCatPicture(cat: CatEntity)

    suspend fun deleteCatPicture(cat: CatEntity)

    fun selectCatPictures(): Flow<List<CatEntity>>

    suspend fun selectCatPicture(id: String): CatEntity?
}