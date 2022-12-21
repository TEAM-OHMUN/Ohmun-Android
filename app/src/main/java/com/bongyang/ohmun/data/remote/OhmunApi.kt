package com.bongyang.ohmun.data.remote

import com.bongyang.ohmun.domain.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OhmunApi {

    @GET("/ohmun/movies")
    suspend fun getAllMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("/ohmun/movies/search")
    suspend fun searchMovies(
        @Query("name") name: String
    ): MovieResponse
}

