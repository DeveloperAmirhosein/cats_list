package com.nocompany.catslist.data.remote

import com.nocompany.catslist.data.remote.dto.CatDto
import javax.inject.Inject

class CatsRetrofitDataSource @Inject constructor(private val api: CatsApi) : CatsRemoteDataSource {
    override fun getCatsList(page: Int, limit: Int): List<CatDto> {
        return api.getCatsList(page, limit)
    }
}