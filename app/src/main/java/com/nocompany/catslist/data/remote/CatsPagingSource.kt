package com.nocompany.catslist.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nocompany.catslist.data.remote.dto.CatDto
import com.nocompany.catslist.data.remote.dto.asExternalModel
import com.nocompany.catslist.domain.model.Cat
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatsPagingSource @Inject constructor(private val dataSource: CatsRemoteDataSource) :
    PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(1)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        return try {
            val currentPage = params.key ?: 1
            val cats = dataSource.getCatsList(currentPage, 10)
            val prevKey = if (currentPage == 1) null else currentPage - 1
            val nextKey = if (cats.isEmpty()) null else currentPage + 1
            LoadResult.Page(
                data = cats.map(CatDto::asExternalModel),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}