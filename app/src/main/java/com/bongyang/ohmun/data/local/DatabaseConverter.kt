package com.bongyang.ohmun.data.local

import androidx.room.TypeConverter
import com.bongyang.ohmun.domain.model.Actor
import com.bongyang.ohmun.domain.model.Review
import com.google.gson.Gson

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
    fun actorListToJson(value: List<Actor>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToActorList(value: String): List<Actor>? {
        return Gson().fromJson(value, Array<Actor>::class.java)?.toList()
    }

    @TypeConverter
    fun reviewListToJson(value: List<Review>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToReviewList(value: String): List<Review>? {
        return Gson().fromJson(value, Array<Review>::class.java)?.toList()
    }
}