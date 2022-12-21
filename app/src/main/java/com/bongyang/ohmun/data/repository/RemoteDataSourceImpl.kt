package com.bongyang.ohmun.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bongyang.ohmun.data.local.OhmunDatabase
import com.bongyang.ohmun.data.paging_source.MovieRemoteMediator
import com.bongyang.ohmun.data.paging_source.SearchMoviesSource
import com.bongyang.ohmun.data.remote.OhmunApi
import com.bongyang.ohmun.domain.model.Movie
import com.bongyang.ohmun.domain.repository.RemoteDataSource
import com.bongyang.ohmun.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val ohmunApi: OhmunApi,
    private val ohmunDatabase: OhmunDatabase
) : RemoteDataSource {

    private val movieDao = ohmunDatabase.movieDao()

    override fun getAllMovies(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { movieDao.getAllMovies() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = MovieRemoteMediator(
                ohmunApi = ohmunApi,
                ohmunDatabase = ohmunDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchMoviesSource(ohmunApi = ohmunApi, query = query)
            }
        ).flow
    }
}