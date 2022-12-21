package com.bongyang.ohmun.domain.use_cases.read_onboarding

import com.bongyang.ohmun.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return movieRepository.readOnBoardingState()
    }
}