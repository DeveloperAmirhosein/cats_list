package com.nocompany.catslist.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nocompany.catslist.domain.SearchCatsRepository
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCatsRepositoryImplementation @Inject constructor(
    private val catsPagingSource: SearchCatsPagingSource
) : SearchCatsRepository {
    override fun getCatsList(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(pageSize = 60, prefetchDistance = 30),
            pagingSourceFactory = {
                catsPagingSource
            }
        ).flow
    }
}