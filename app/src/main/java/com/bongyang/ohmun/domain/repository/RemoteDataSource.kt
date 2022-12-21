package com.bongyang.ohmun.domain.repository

import androidx.paging.PagingData
import com.bongyang.ohmun.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllMovies(): Flow<PagingData<Movie>>

    fun searchMovies(query: String): Flow<PagingData<Movie>>
}