package com.example.medialibary.repositories

import com.example.medialibary.daos.BoardGamesDao
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BoardGamesRepository (
    private val boardGamesDao: BoardGamesDao
){
    // private val _boardGames: MutableStateFlow<List<BoardGame>> = MutableStateFlow(emptyList()) // Copilot
    private val _boardGames = MutableStateFlow(emptyList<BoardGame>())
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    suspend fun loadBoardGames() {
        _boardGames.value = boardGamesDao.getAllBoardGames()
    }
    suspend fun getBoardGameById(id: Long?): BoardGame? = boardGamesDao.getBoardGameById(id)

    suspend fun addBoardGame(title: String,  minPlayers: Int,  maxPlayers: Int,  type: String,  notes: String){
        val newBoardGame = BoardGame(
            title = title,
            minPlayers = minPlayers,
            maxPlayers = maxPlayers,
            type = type,
            notes = notes
        )
        newBoardGame.id = boardGamesDao.insertBoardGame(newBoardGame)
        _boardGames.value += newBoardGame
    }
}