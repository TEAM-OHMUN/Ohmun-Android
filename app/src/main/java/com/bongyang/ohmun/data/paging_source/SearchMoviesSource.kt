package com.bongyang.ohmun.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bongyang.ohmun.data.remote.OhmunApi
import com.bongyang.ohmun.domain.model.Movie
import java.lang.Exception

class SearchMoviesSource(
    private val ohmunApi: OhmunApi,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val apiResponse = ohmunApi.searchMovies(name = query)
            val heroes = apiResponse.movies
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}