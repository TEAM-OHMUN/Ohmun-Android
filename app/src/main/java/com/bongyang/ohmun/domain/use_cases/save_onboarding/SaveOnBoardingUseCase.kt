package com.bongyang.ohmun.domain.use_cases.save_onboarding

import com.bongyang.ohmun.data.repository.MovieRepository

class SaveOnBoardingUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        movieRepository.saveOnBoardingState(completed = completed)
    }
}