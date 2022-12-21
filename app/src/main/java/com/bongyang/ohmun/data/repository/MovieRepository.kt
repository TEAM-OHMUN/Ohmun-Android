package com.bongyang.ohmun.data.repository

import androidx.paging.PagingData
import com.bongyang.ohmun.domain.model.Movie
import com.bongyang.ohmun.domain.repository.DataStoreOperations
import com.bongyang.ohmun.domain.repository.LocalDataSource
import com.bongyang.ohmun.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllMovies(): Flow<PagingData<Movie>> {
        return remoteDataSource.getAllMovies()
    }

    fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return remoteDataSource.searchMovies(query = query)
    }

    suspend fun getSelectedMovie(movieId: Int): Movie {
        return localDataSource.getSelectedMovie(movieId = movieId)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}

