package com.bongyang.ohmun.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bongyang.ohmun.domain.model.MovieRemoteKeys

@Dao
interface MovieRemoteKeysDao {

    @Query("SELECT * FROM movie_remote_keys_table WHERE id = :movieId")
    suspend fun getRemoteKeys(movieId: Int): MovieRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<MovieRemoteKeys>)

    @Query("DELETE FROM movie_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}