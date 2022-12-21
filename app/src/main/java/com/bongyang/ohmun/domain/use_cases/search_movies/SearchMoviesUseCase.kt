package com.bongyang.ohmun.domain.use_cases.search_movies

import androidx.paging.PagingData
import com.bongyang.ohmun.data.repository.MovieRepository
import com.bongyang.ohmun.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Movie>> {
        return movieRepository.searchMovies(query = query)
    }
}