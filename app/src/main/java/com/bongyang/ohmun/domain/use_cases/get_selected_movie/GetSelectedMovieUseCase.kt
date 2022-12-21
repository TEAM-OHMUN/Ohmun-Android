package com.bongyang.ohmun.domain.use_cases.get_selected_movie

import com.bongyang.ohmun.data.repository.MovieRepository
import com.bongyang.ohmun.domain.model.Movie

class GetSelectedMovieUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Movie {
        return movieRepository.getSelectedMovie(movieId = movieId)
    }
}