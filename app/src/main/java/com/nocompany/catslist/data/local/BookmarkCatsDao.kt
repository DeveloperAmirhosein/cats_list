package com.nocompany.catslist.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nocompany.catslist.data.local.entities.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkCatsDao {

    @Upsert
    suspend fun upsert(cat: CatEntity)

    @Query("SELECT * FROM CatEntity")
    fun getBookmarkedCats(): Flow<List<CatEntity>>

    @Query("Delete FROM CatEntity WHERE id =:id")
    suspend fun deleteFromBookmarks(id: String)

}