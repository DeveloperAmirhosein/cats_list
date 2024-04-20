package com.nocompany.catslist.data.remote

import com.nocompany.catslist.data.remote.dto.CatDto
import javax.inject.Inject

class SearchCatsRetrofitDataSource @Inject constructor(
    private val api: SearchCatsApi
) : SearchCatsRemoteDataSource {
    override suspend fun getCatsList(page: Int, limit: Int): List<CatDto> {
        return api.getCatsList(page, limit)
    }
}