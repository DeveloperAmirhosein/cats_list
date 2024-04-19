package com.nocompany.catslist.di

import com.nocompany.catslist.data.remote.CatsRemoteDataSource
import com.nocompany.catslist.data.remote.CatsRepository
import com.nocompany.catslist.data.remote.CatsRepositoryImplementation
import com.nocompany.catslist.data.remote.CatsRetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(repository: CatsRepositoryImplementation): CatsRepository

    @Binds
    abstract fun bindDataSource(dataSource: CatsRetrofitDataSource): CatsRemoteDataSource
}