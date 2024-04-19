package com.nocompany.catslist.data.remote

import com.nocompany.catslist.data.remote.dto.CatDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {

    @GET(value = "images/search")
    fun getCatsList(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Call<List<CatDto>>

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/v1/"
    }
}