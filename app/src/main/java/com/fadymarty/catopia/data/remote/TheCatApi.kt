package com.fadymarty.catopia.data.remote

import com.fadymarty.catopia.data.remote.dto.CatDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCatApi {

    @GET("search")
    suspend fun getCatPictures(
        @Query("limit") limit: Int = 10,
    ): List<CatDto>
}