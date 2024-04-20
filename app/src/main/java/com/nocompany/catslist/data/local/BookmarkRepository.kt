package com.nocompany.catslist.data.local

import com.nocompany.catslist.data.local.mapper.asEntity
import com.nocompany.catslist.data.local.mapper.asExternalModel
import com.nocompany.catslist.domain.repository.BookmarkRepository
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImplementation @Inject constructor(
    private val dao: BookmarkCatsDao
) :
    BookmarkRepository {
    override suspend fun bookmarkCat(cat: Cat) {
        dao.upsert(cat.asEntity())
    }

    override suspend fun unBookmarkCat(cat: Cat) {
        dao.deleteFromBookmarks(cat.id)
    }

    override fun getBookmarkedCats(): Flow<List<Cat>> =
        dao.getBookmarkedCats().map { it.map(CatEntity::asExternalModel) }

}