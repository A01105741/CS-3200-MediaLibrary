package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.repositories.BoardGamesRepository
import com.example.medialibary.repositories.BooksRepository
import com.example.medialibary.repositories.MoviesRepository
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class HomeScreenUIState(
    val bookCount: Int = 0,
    val movieCount: Int = 0,
    val videoGameCount: Int = 0,
    val boardGameCount: Int = 0
)

class HomeScreenViewModel(
    booksRepository: BooksRepository,
    moviesRepository: MoviesRepository,
    videoGamesRepository: VideoGamesRepository,
    boardGameRepository: BoardGamesRepository,
): ViewModel() {
    val _uiState = MutableStateFlow<HomeScreenUIState>(
        HomeScreenUIState()
    )
    val uiState:StateFlow<HomeScreenUIState> = _uiState

    init {
        viewModelScope.launch {
            launch {
                booksRepository.books.collect() {
                    _uiState.value = _uiState.value.copy(bookCount = it.size)
                }
            }
            launch {
                moviesRepository.movies.collect() {
                    _uiState.value = _uiState.value.copy(movieCount = it.size)
                }
            }
            launch {
                videoGamesRepository.videoGames.collect() {
                    _uiState.value = _uiState.value.copy(videoGameCount = it.size)
                }
            }
            launch {
                boardGameRepository.boardGames.collect() {
                    _uiState.value = _uiState.value.copy(boardGameCount = it.size)
                }
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                HomeScreenViewModel(
                    application.booksRepository,
                    application.moviesRepository,
                    application.videoGamesRepository,
                    application.boardGamesRepository
                )
            }
        }
    }
}