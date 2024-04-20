package com.nocompany.catslist.data.remote

import com.nocompany.catslist.data.remote.dto.CatDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCatsApi {

    @GET(value = "images/search")
    suspend fun getCatsList(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<CatDto>

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/v1/"
    }
}