package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.Movie
import com.example.medialibary.repositories.MoviesRepository
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CreateMovieScreenViewModel(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    private val _movies = MutableStateFlow(emptyList<Movie>()) // Copilot
    val movies: StateFlow<List<Movie>> = _movies

    fun saveMovie(
        title: String,
        genre: String,
        rating: String,
        runtime: Int,
        format: String,
        notes: String
    ) {
        viewModelScope.launch {
            val newMovie = Movie(
                title = title,
                genre = genre,
                rating = rating,
                runtime = runtime,
                format = format,
                notes = notes)
            moviesRepository.addMovie(newMovie.title,
                newMovie.genre,
                newMovie.rating,
                newMovie.runtime,
                newMovie.format,
                newMovie.notes)
        }
    }

    companion object {
        val MOVIE_ID_KEY = object : CreationExtras.Key<Long?> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val movieId = this[MOVIE_ID_KEY] //as Long?

                CreateMovieScreenViewModel(
                    moviesRepository = application.moviesRepository
                )
            }
        }
    }
}