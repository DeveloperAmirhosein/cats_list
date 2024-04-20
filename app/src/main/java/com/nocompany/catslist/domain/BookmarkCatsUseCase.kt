package com.nocompany.catslist.domain

import com.nocompany.catslist.domain.model.Cat
import javax.inject.Inject

class BookmarkCatsUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    suspend operator fun invoke(cat: Cat, bookmark: Boolean) {
        if (bookmark) bookmarkRepository.bookmarkCat(cat)
        else bookmarkRepository.unBookmarkCat(cat)
    }
}