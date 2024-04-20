package com.nocompany.catslist.di

import com.nocompany.catslist.data.remote.SearchCatsRemoteDataSource
import com.nocompany.catslist.domain.SearchCatsRepository
import com.nocompany.catslist.data.remote.SearchCatsRepositoryImplementation
import com.nocompany.catslist.data.remote.SearchCatsRetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(repository: SearchCatsRepositoryImplementation): SearchCatsRepository

    @Binds
    abstract fun bindDataSource(dataSource: SearchCatsRetrofitDataSource): SearchCatsRemoteDataSource
}