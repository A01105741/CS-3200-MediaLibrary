package com.example.medialibary.repositories

import com.example.medialibary.daos.BoardGamesDao
import com.example.medialibary.models.BoardGame
import kotlinx.coroutines.flow.MutableStateFlow

// implement using StateFlow

class BoardGamesRepository (private val boardGamesDao: BoardGamesDao
){
    val boardGames: MutableStateFlow<List<BoardGame>> = MutableStateFlow(emptyList()) // Copilot

    suspend fun addBoardGame(boardGame: BoardGame): Long {
        return boardGamesDao.insertBoardGame(boardGame)
    }
    suspend fun getBoardGames(): List<BoardGame> {
        return boardGamesDao.getAllBoardGames()
    }
}