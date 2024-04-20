package com.nocompany.catslist.domain.usecase

import com.nocompany.catslist.domain.repository.BookmarkRepository
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedCatsUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(): Flow<List<Cat>> = bookmarkRepository.getBookmarkedCats()

}