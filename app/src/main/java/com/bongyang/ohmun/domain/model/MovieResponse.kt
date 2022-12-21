package com.bongyang.ohmun.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val movies: List<Movie> = emptyList(),
    val lastUpdated: Long? = null
)