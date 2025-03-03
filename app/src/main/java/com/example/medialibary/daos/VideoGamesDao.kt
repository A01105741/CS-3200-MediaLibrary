package com.example.medialibary.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.medialibary.models.VideoGame

@Dao
abstract class VideoGamesDao {
    @Query("SELECT * FROM videoGame")
    abstract suspend fun getAllVideoGames(): List<VideoGame>
    @Query("SELECT * FROM videoGame WHERE id = :id")
    abstract suspend fun getVideoGameById(id: Long): VideoGame?
    @Insert
    abstract suspend fun insertVideoGame(videoGame: VideoGame): Long  // returns the id of the new videoGame
    @Update
    abstract suspend fun updateVideoGame(videoGame: VideoGame)
}