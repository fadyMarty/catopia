package com.fadymarty.catopia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fadymarty.catopia.data.local.entity.CatEntity

@Database(
    entities = [CatEntity::class],
    version = 1
)
abstract class CatDatabase : RoomDatabase() {
    abstract val catDao: CatDao
}