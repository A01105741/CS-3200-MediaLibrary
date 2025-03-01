package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.repositories.MoviesRepository
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.launch

class CreateMovieScreenViewModel(
    val moviesRepository: MoviesRepository,
): ViewModel() {
    fun saveMovie(
        title: String,
        genre: String,
        rating: String,
        runtime: Int,
        format: String,
        notes: String
    ) {
        viewModelScope.launch {
            moviesRepository.addMovie(
                title = title,
                genre = genre,
                rating = rating,
                runtime = runtime,
                format = format,
                notes = notes
            )
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                CreateMovieScreenViewModel(
                    moviesRepository = application.moviesRepository
                )
            }
        }
    }
}