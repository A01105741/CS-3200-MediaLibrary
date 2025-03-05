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
    private val booksRepository: BooksRepository,
    private val moviesRepository: MoviesRepository,
    private val videoGamesRepository: VideoGamesRepository,
    private val boardGamesRepository: BoardGamesRepository,
): ViewModel() {
    fun loadData() {
        viewModelScope.launch {
            moviesRepository.loadMovies()
            booksRepository.loadBooks()
            boardGamesRepository.loadBoardGames()
            videoGamesRepository.loadVideoGames()
        }
    }

    private val _uiState = MutableStateFlow(HomeScreenUIState())
    val uiState: StateFlow<HomeScreenUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                booksRepository.books.collect() { books ->
                    _uiState.update { it.copy(bookCount = books.size) }
                }
            }
            launch {
                moviesRepository.movies.collect() { movies ->
                    _uiState.update { it.copy(movieCount = movies.size) }
                }
            }
            launch {
                videoGamesRepository.videoGames.collect() { videoGames ->
                    _uiState.update { it.copy(videoGameCount = videoGames.size) }
                }
            }
            launch {
                boardGamesRepository.boardGames.collect() { boardGames ->
                    _uiState.update { it.copy(boardGameCount = boardGames.size) }

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