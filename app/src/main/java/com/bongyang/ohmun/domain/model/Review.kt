package com.bongyang.ohmun.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bongyang.ohmun.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.REVIEW_DATABASE_TABLE)
data class Review(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val rating: Double,
    val nickname: String,
    val image: String,
    val comment: String,
    val createdAt: String
)
