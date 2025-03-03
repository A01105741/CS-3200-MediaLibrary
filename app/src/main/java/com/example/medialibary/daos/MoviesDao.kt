package com.example.medialibary.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.medialibary.models.Movie

@Dao
abstract class MoviesDao {
    @Query("SELECT * FROM movie")
    abstract suspend fun getAllMovies(): List<Movie>
    @Query("SELECT * FROM movie WHERE id = :id")
    abstract suspend fun getMovieById(id: Long?): Movie?
    @Insert
    abstract suspend fun insertMovie(movie: Movie): Long  // returns the id of the new movie
    @Update
    abstract suspend fun updateMovie(movie: Movie)
}