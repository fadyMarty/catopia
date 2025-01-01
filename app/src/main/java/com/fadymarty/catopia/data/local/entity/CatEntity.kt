package com.fadymarty.catopia.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cat_photos"
)
data class CatEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
)