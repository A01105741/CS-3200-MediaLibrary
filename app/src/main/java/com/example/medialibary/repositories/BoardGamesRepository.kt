package com.example.medialibary.repositories

import com.example.medialibary.models.BoardGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// implement using StateFlow

object BoardGamesRepository {
    private var ID_COUNTER = 0L
    private val _boardGames = MutableStateFlow<List<BoardGame>>(emptyList())
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    suspend fun addBoardGame(
        title: String,
        minPlayers: Int,
        maxPlayers: Int,
        type: String,
        notes: String
    ) {
        _boardGames.value += BoardGame(
            id = ID_COUNTER++,
            title = title,
            minPlayers = minPlayers,
            maxPlayers = maxPlayers,
            type = type,
            notes = notes
        )
    }
}