package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
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
            boardGamesRepository.addBoardGame(
                title = title,
                minPlayers = minPlayers,
                maxPlayers = maxPlayers,
                type = type,
                notes = notes
            )
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