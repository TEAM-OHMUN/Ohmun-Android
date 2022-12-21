package com.bongyang.ohmun.data.repository

import com.bongyang.ohmun.data.local.OhmunDatabase
import com.bongyang.ohmun.domain.model.Movie
import com.bongyang.ohmun.domain.repository.LocalDataSource


class LocalDataSourceImpl(ohmunDatabase: OhmunDatabase): LocalDataSource {

    private val movieDao = ohmunDatabase.movieDao()

    override suspend fun getSelectedMovie(movieId: Int): Movie {
        return movieDao.getSelectedMovie(movieId = movieId)
    }
}