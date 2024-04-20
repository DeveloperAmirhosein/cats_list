package com.nocompany.catslist.di

import com.nocompany.catslist.data.local.BookmarkRepositoryImplementation
import com.nocompany.catslist.data.remote.SearchCatsRemoteDataSource
import com.nocompany.catslist.domain.SearchCatsRepository
import com.nocompany.catslist.data.remote.SearchCatsRepositoryImplementation
import com.nocompany.catslist.data.remote.SearchCatsRetrofitDataSource
import com.nocompany.catslist.domain.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindSearchCatsRepository(
        repository: SearchCatsRepositoryImplementation
    ): SearchCatsRepository

    @Binds
    abstract fun bindDataSource(
        dataSource: SearchCatsRetrofitDataSource
    ): SearchCatsRemoteDataSource

    @Binds
    abstract fun bindBookmarkCatsRepository(
        repository: BookmarkRepositoryImplementation
    ): BookmarkRepository
}