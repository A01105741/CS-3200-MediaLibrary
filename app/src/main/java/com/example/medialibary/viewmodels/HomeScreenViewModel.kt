package com.example.medialibary.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
    /*
    val _uiState = MutableStateFlow<HomeScreenUIState>(
        HomeScreenUIState()
    )
    */

    private val _uiState = MutableStateFlow(HomeScreenUIState())
    //val uiState:StateFlow<HomeScreenUIState> = _uiState
    val uiState: StateFlow<HomeScreenUIState> = _uiState.asStateFlow()
    //val movies by viewModel.movies.collectAsState()

    init {
        viewModelScope.launch {
            launch {
                booksRepository.books.collect() { books ->
                    _uiState.update { it.copy(bookCount = books.size) }
                    //_uiState.value = _uiState.value.copy(bookCount = it.size)
                    // _uiState.update { it.copy(bookCount = it.size) }
                }
            }
            launch {
                moviesRepository.movies.collect() { movies ->
                    _uiState.update { it.copy(movieCount = movies.size) }
                    //  _uiState.value = _uiState.value.copy(movieCount = it.size)
                  //  _uiState.update { it.copy(movieCount = it.size) }
                }
            }
            launch {
                videoGamesRepository.videoGames.collect() { videoGames ->
                    _uiState.update { it.copy(videoGameCount = videoGames.size) }
                   // _uiState.value = _uiState.value.copy(videoGameCount = it.size)
                   // _uiState.update { it.copy(videoGameCount = it.size) }
                }
            }
            launch {
                boardGameRepository.boardGames.collect() { boardGames ->
                    _uiState.update { it.copy(boardGameCount = boardGames.size) }
                    // _uiState.value = _uiState.value.copy(boardGameCount = it.size)
                    // _uiState.update { it.copy(boardGameCount = it.size) }

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