package com.bongyang.ohmun.domain.use_cases

import com.bongyang.ohmun.domain.use_cases.get_all_movies.GetAllMoviesUseCase
import com.bongyang.ohmun.domain.use_cases.get_selected_movie.GetSelectedMovieUseCase
import com.bongyang.ohmun.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.bongyang.ohmun.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.bongyang.ohmun.domain.use_cases.search_movies.SearchMoviesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllMoviesUseCase: GetAllMoviesUseCase,
    val searchMoviesUseCase: SearchMoviesUseCase,
    val getSelectedMovieUseCase: GetSelectedMovieUseCase
)


