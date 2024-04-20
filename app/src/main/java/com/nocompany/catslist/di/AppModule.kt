package com.nocompany.catslist.di

import android.content.Context
import androidx.room.Room
import com.nocompany.catslist.data.local.CatsDataBase
import com.nocompany.catslist.data.remote.SearchCatsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideCatsApi(client: OkHttpClient): SearchCatsApi = Retrofit
        .Builder()
        .client(client)
        .baseUrl(SearchCatsApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(SearchCatsApi::class.java)



    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CatsDataBase {
        return Room
            .databaseBuilder(
                context,
                CatsDataBase::class.java,
                "cats.db"
            )
            .build()
    }
}