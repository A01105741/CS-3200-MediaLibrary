package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.Movie
import com.example.medialibary.repositories.BoardGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateBoardGameScreenViewModel(
    val boardGamesRepository: BoardGamesRepository,
): ViewModel() {

    private val _boardGames = MutableStateFlow(emptyList<BoardGame>()) // Copilot
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    fun saveBoardGame(
        title: String,
        minPlayers: Int,
        maxPlayers: Int,
        type: String,
        notes: String
    ) {
        viewModelScope.launch {
          val newBoardGame = BoardGame(
                title = title,
                minPlayers = minPlayers,
                maxPlayers = maxPlayers,
                type = type,
                notes = notes
            )
            boardGamesRepository.addBoardGame(newBoardGame.title,
                    newBoardGame.minPlayers,
                    newBoardGame.maxPlayers,
                    newBoardGame.type,
                    newBoardGame.notes)
        }
    }

    companion object {
        val BOARDGAME_ID_KEY = object : CreationExtras.Key<Long?> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val boardGameId = this[BOARDGAME_ID_KEY] //as Long?
                CreateBoardGameScreenViewModel(
                    boardGamesRepository = application.boardGamesRepository
                )
            }
        }
    }
}