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
import com.example.medialibary.models.Movie
import com.example.medialibary.repositories.BoardGamesRepository
import com.example.medialibary.repositories.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BoardGameScreenUIState(
    val boardGame: BoardGame? = null
)

class BoardGameScreenViewModel(
    private val boardGameId: Long?,
    private val boardGamesRepository: BoardGamesRepository
): ViewModel() {
    private val _uiBoardGameState = MutableStateFlow(BoardGameScreenUIState())
    val uiBoardGame: StateFlow<BoardGameScreenUIState> = _uiBoardGameState.asStateFlow()

    fun getBoardGameById(boardGameId: Long) {
        viewModelScope.launch {
            val boardGame = boardGamesRepository.getBoardGameById(boardGameId)
            _uiBoardGameState.update { it.copy(boardGame = boardGame) }
        }
    }

    companion object {
        val BOARDGAME_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val boardGameId = this[BOARDGAME_ID_KEY] //as Long
                BoardGameScreenViewModel(
                    boardGameId,
                    application.boardGamesRepository
                )
            }
        }
    }
}