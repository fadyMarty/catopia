package com.fadymarty.catopia.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.fadymarty.catopia.data.local.entity.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Upsert
    suspend fun upsertCatPicture(cat: CatEntity)

    @Delete
    suspend fun deleteCatPicture(cat: CatEntity)

    @Query("SELECT * FROM cat_photos")
    fun selectCatPictures(): Flow<List<CatEntity>>

    @Query("SELECT * FROM cat_photos WHERE id=:id")
    suspend fun selectCatPicture(id: String): CatEntity?
}