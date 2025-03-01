package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.Book
import com.example.medialibary.repositories.BoardGamesRepository
import com.example.medialibary.repositories.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardGameScreenViewModel(
    boardGamesRepository: BoardGamesRepository,
    boardGameId: Long
): ViewModel() {
    private val _boardGame = MutableStateFlow<BoardGame?>(null)
    val boardGame: StateFlow<BoardGame?> = _boardGame

    init {
        viewModelScope.launch {
            boardGamesRepository.boardGames.collect { boardGames ->
                _boardGame.value = boardGames.find { it.id == boardGameId }
            }
        }
    }

    companion object {
        val BOARD_GAME_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val boardGameId = this[BOARD_GAME_ID_KEY] as Long
                BoardGameScreenViewModel(
                    application.boardGamesRepository,
                    boardGameId
                )
            }
        }
    }
}