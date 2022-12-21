package com.bongyang.ohmun.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bongyang.ohmun.util.Constants.MOVIE_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = MOVIE_DATABASE_TABLE)
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val image: String,
    val openingDate: String,
    val filmRating: String,
    val genre: List<String>,
    val platform: List<String>,
    val actors: List<Actor> = emptyList(),
    val reviews: List<Review> = emptyList()
)
