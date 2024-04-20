package com.nocompany.catslist.domain

import androidx.paging.PagingData
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val repository: SearchCatsRepository,
) {
    operator fun invoke(): Flow<PagingData<Cat>> {
        return repository.getCatsList()
    }
}