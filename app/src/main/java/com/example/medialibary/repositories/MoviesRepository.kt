package com.example.medialibary.repositories

import com.example.medialibary.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object MoviesRepository {
    private var ID_COUNTER = 0L
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    suspend fun addMovie(
        title: String,
        genre: String,
        rating: String,
        runtime: Int,
        format: String,
        notes: String
    ) {
        _movies.value += Movie(
            id = ID_COUNTER++,
            title = title,
            genre = genre,
            rating = rating,
            runtime = runtime,
            format = format,
            notes = notes
        )
    }
}