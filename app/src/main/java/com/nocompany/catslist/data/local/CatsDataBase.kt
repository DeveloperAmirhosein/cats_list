package com.nocompany.catslist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatEntity::class], version = 1)
abstract class CatsDataBase : RoomDatabase() {
    abstract val bookmarkCatsDao: BookmarkCatsDao
}