package com.bongyang.ohmun.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bongyang.ohmun.util.Constants.MOVIE_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = MOVIE_REMOTE_KEY_DATABASE_TABLE)
data class MovieRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
