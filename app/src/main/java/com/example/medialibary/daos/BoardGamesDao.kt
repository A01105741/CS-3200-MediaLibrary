package com.example.medialibary.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.medialibary.models.BoardGame

@Dao
abstract class BoardGamesDao {
    @Query("SELECT * FROM boardGame")
    abstract suspend fun getAllBoardGames(): List<BoardGame>
    @Query("SELECT * FROM boardGame WHERE id = :id")
    abstract suspend fun getBoardGameById(id: Long): BoardGame?
    @Insert
    abstract suspend fun insertBoardGame(boardGame: BoardGame): Long  // returns the id of the new boardGame
    @Update
    abstract suspend fun updateBoardGame(boardGame: BoardGame)
}