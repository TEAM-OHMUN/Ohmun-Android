package com.bongyang.ohmun.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bongyang.ohmun.domain.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovies(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movie_table WHERE id=:movieId")
    fun getSelectedMovie(movieId: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()
}