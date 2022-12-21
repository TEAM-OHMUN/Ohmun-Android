package com.bongyang.ohmun.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bongyang.ohmun.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.ACTOR_DATABASE_TABLE)
data class Actor(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val age: Int,
    val birthDate: String
)
