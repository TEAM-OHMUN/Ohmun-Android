package com.bongyang.ohmun.presentation.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bongyang.ohmun.domain.model.Movie
import com.bongyang.ohmun.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val searchedMovies = _searchedMovies

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchMoviesUseCase(query = query).cachedIn(viewModelScope).collect {
                _searchedMovies.value = it
            }
        }
    }
}