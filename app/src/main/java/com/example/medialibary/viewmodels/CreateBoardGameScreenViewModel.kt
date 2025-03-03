package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.BoardGame
import com.example.medialibary.repositories.BoardGamesRepository
import kotlinx.coroutines.launch

class CreateBoardGameScreenViewModel(
    val boardGamesRepository: BoardGamesRepository,
): ViewModel() {
    fun saveBoardGame(
        title: String,
        minPlayers: Int,
        maxPlayers: Int,
        type: String,
        notes: String
    ) {
        viewModelScope.launch {
          val boardGame = BoardGame(
                title = title,
                minPlayers = minPlayers,
                maxPlayers = maxPlayers,
                type = type,
                notes = notes
            )
            boardGame.id = boardGamesRepository.addBoardGame(boardGame)
            var boardGames = boardGame
            // content = ""
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                CreateBoardGameScreenViewModel(
                    boardGamesRepository = application.boardGamesRepository
                )
            }
        }
    }
}