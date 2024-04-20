package com.nocompany.catslist.domain.usecase

import androidx.paging.PagingData
import com.nocompany.catslist.domain.repository.SearchCatsRepository
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val searchCatsRepository: SearchCatsRepository,
) {
    operator fun invoke(): Flow<PagingData<Cat>> =
        searchCatsRepository.getCatsList()

}