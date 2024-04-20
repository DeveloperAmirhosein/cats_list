package com.nocompany.catslist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nocompany.catslist.data.local.entities.CatEntity

@Database(entities = [CatEntity::class], version = 1)
abstract class CatsDataBase : RoomDatabase() {
    abstract val bookmarkCatsDao: BookmarkCatsDao
}