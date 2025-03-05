package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.Movie

import com.example.medialibary.repositories.MoviesRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesScreenViewModel(
    private val moviesRepository: MoviesRepository
): ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun loadMovies() {
        viewModelScope.launch {
            moviesRepository.loadMovies()
        }
    }

    init {
        viewModelScope.launch {
            moviesRepository.movies.collect {movies ->
                _movies.value = movies
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                MoviesScreenViewModel(
                    application.moviesRepository
                )
            }
        }
    }
}