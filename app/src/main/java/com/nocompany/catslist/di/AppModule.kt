package com.nocompany.catslist.di

import com.nocompany.catslist.data.remote.CatsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatsApi(): CatsApi = Retrofit
        .Builder()
        .baseUrl(CatsApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(CatsApi::class.java)


}