package com.nocompany.catslist.domain

import androidx.paging.PagingData
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val searchCatsRepository: SearchCatsRepository,
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(): Flow<PagingData<Cat>> {
        return searchCatsRepository.getCatsList()
    }
}