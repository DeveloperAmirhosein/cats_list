package com.nocompany.catslist.data.remote

import com.nocompany.catslist.data.remote.dto.CatDto

interface CatsRemoteDataSource {
    suspend fun getCatsList(page: Int, limit: Int): List<CatDto>
}