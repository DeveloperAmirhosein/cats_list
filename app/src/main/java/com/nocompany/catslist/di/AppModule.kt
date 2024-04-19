package com.nocompany.catslist.di

import com.nocompany.catslist.data.remote.CatsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val API_KEY ="WELDY"

    @Provides
    @Singleton
    fun provideClientWithCache(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor {
                it.run {
                    proceed(
                        request()
                            .newBuilder()
                            .addHeader("x-api-key", API_KEY)
                            .build()
                    )
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideCatsApi(client: OkHttpClient): CatsApi = Retrofit
        .Builder()
        .client(client)
        .baseUrl(CatsApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(CatsApi::class.java)

}