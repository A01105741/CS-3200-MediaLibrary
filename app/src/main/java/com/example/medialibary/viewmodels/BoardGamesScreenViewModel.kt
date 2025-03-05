package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.Movie
import com.example.medialibary.repositories.BoardGamesRepository
import com.example.medialibary.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardGamesScreenViewModel(
    val boardGamesRepository: BoardGamesRepository
): ViewModel() {
    private val _boardGames = MutableStateFlow<List<BoardGame>>(emptyList())
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    fun loadBoardGames() {
        viewModelScope.launch {
            boardGamesRepository.loadBoardGames()
        }
    }

    init {
        viewModelScope.launch {
            boardGamesRepository.boardGames.collect { boardGames ->
                _boardGames.value = boardGames
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                BoardGamesScreenViewModel(
                    application.boardGamesRepository
                )
            }
        }
    }
}