package com.nocompany.catslist.data.remote

import androidx.paging.PagingData
import com.nocompany.catslist.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCatsList() : Flow<PagingData<Cat>>
}