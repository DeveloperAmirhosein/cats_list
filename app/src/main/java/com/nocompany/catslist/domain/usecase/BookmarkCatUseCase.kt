package com.nocompany.catslist.domain.usecase

import com.nocompany.catslist.domain.repository.BookmarkRepository
import com.nocompany.catslist.domain.model.Cat
import javax.inject.Inject

class BookmarkCatUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    suspend operator fun invoke(cat: Cat, bookmark: Boolean) {
        if (bookmark) bookmarkRepository.bookmarkCat(cat)
        else bookmarkRepository.unBookmarkCat(cat)
    }
}