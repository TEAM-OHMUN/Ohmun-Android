package com.bongyang.ohmun.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bongyang.ohmun.data.local.dao.MovieDao
import com.bongyang.ohmun.data.local.dao.MovieRemoteKeysDao
import com.bongyang.ohmun.domain.model.Actor
import com.bongyang.ohmun.domain.model.Movie
import com.bongyang.ohmun.domain.model.MovieRemoteKeys
import com.bongyang.ohmun.domain.model.Review

@Database(
    entities = [
        Movie::class,
        MovieRemoteKeys::class,
        Actor::class,
        Review::class
    ],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class OhmunDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
}