package com.bongyang.ohmun.domain.use_cases.get_all_movies

import androidx.paging.PagingData
import com.bongyang.ohmun.data.repository.MovieRepository
import com.bongyang.ohmun.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetAllMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return movieRepository.getAllMovies()
    }
}


