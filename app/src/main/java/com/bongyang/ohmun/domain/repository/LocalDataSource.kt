package com.bongyang.ohmun.domain.repository

import com.bongyang.ohmun.domain.model.Movie

interface LocalDataSource {

    suspend fun getSelectedMovie(movieId: Int): Movie
}