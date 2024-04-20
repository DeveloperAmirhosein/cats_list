package com.nocompany.catslist.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.nocompany.catslist.domain.model.BookmarkableCat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val searchCatsRepository: SearchCatsRepository,
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(): Flow<PagingData<BookmarkableCat>> =
        combine(
            searchCatsRepository.getCatsList(),
            bookmarkRepository.getBookmarkedCats()
        ) { pagingCats, bookmarkedCats ->
            pagingCats.map { cat ->
                BookmarkableCat(
                    cat,
                    isBookmarked = cat.id in bookmarkedCats.map {
                        it.id
                    })
            }
        }
}