package com.nocompany.catslist.domain.repository

import androidx.paging.PagingData
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface SearchCatsRepository {
    fun getCatsList() : Flow<PagingData<Cat>>
}