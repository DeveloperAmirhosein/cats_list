package com.nocompany.catslist.domain

import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun bookmarkCat(cat: Cat)

    suspend fun unBookmarkCat(cat: Cat)

    fun getBookmarkedCats(): Flow<List<Cat>>
}