package com.bongyang.ohmun.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.bongyang.ohmun.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllMovies = useCases.getAllMoviesUseCase()
}

