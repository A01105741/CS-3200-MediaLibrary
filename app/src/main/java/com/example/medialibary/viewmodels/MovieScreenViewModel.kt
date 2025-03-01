package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.Movie
import com.example.medialibary.models.VideoGame
import com.example.medialibary.repositories.MoviesRepository
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieScreenViewModel(
    moviesRepository: MoviesRepository,
    movieId: Long
): ViewModel() {
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    init {
        viewModelScope.launch {
            moviesRepository.movies.collect { movies ->
                _movie.value = movies.find { it.id == movieId }
            }
        }
    }

    companion object {
        val MOVIE_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val movieId = this[MOVIE_ID_KEY] as Long
                MovieScreenViewModel(
                    application.moviesRepository,
                    movieId
                )
            }
        }
    }
}