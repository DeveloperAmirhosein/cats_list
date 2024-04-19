package com.nocompany.catslist.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nocompany.catslist.domain.CatsRepository
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatsRepositoryImplementation @Inject constructor(
    private val catsPagingSource: CatsPagingSource
) : CatsRepository {
    override fun getCatsList(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(pageSize = 60, prefetchDistance = 30),
            pagingSourceFactory = {
                catsPagingSource
            }
        ).flow
    }
}