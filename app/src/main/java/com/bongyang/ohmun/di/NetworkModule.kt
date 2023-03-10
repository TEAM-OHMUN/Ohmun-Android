package com.bongyang.ohmun.di

import androidx.paging.ExperimentalPagingApi
import com.bongyang.ohmun.data.local.OhmunDatabase
import com.bongyang.ohmun.data.remote.OhmunApi
import com.bongyang.ohmun.data.repository.RemoteDataSourceImpl
import com.bongyang.ohmun.domain.repository.RemoteDataSource
import com.bongyang.ohmun.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideOhmunApi(retrofit: Retrofit): OhmunApi {
        return retrofit.create(OhmunApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        ohmunApi: OhmunApi,
        ohmunDatabase: OhmunDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            ohmunApi = ohmunApi,
            ohmunDatabase = ohmunDatabase
        )
    }
}