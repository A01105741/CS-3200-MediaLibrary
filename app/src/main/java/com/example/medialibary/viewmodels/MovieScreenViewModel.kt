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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MovieScreenUIState(
    val movie: Movie? = null
)

class MovieScreenViewModel(
    private val movieId: Long?,
    private val moviesRepository: MoviesRepository
): ViewModel() {
 //   private val _movie = MutableStateFlow<Movie?>(null)
    private val _uiMovieState  = MutableStateFlow(MovieScreenUIState())
    //val movie: StateFlow<Movie?> = _movie
    val uiMovieState: StateFlow<MovieScreenUIState> = _uiMovieState.asStateFlow()
    //movie: StateFlow<MovieScreenUIState> = _movie.asStateFlow() //_uiState.asStateFlow()


    fun getMovieById(movieId: Long) {
        viewModelScope.launch {
            val movie = moviesRepository.getMovieById(movieId)
            _uiMovieState.update { it.copy(movie = movie) }
        }
    }

    companion object {
        val MOVIE_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val movieId = this[MOVIE_ID_KEY] //as Long
                MovieScreenViewModel(
                    movieId,
                    moviesRepository = application.moviesRepository // application.moviesRepository
                )
            }
        }
    }
}