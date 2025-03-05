package com.example.medialibary.repositories

import com.example.medialibary.daos.MoviesDao
import com.example.medialibary.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MoviesRepository (
    private val moviesDao: MoviesDao
){
    //private val _movies = MutableStateFlow(emptyList<Movie>())
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    // val movies: StateFlow<List<Movie>> = _movies
    //val movies: StateFlow<List<Movie>> = _movies.asStateFlow()
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    suspend fun loadMovies() {
        _movies.value = moviesDao.getAllMovies()
    }

    suspend fun getMovieById(id: Long?): Movie? = moviesDao.getMovieById(id)

    suspend fun addMovie(title: String,
                         genre: String,
                         rating: String,
                         runtime: Int,
                         format: String,
                         notes: String
                         ){
        val newMovie = Movie(
            title = title,
            genre = genre,
            rating = rating,
            runtime = runtime,
            format = format,
            notes = notes
        )
        newMovie.id = moviesDao.insertMovie(newMovie)
        _movies.value += newMovie
        }
    }