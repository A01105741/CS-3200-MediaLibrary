package com.example.medialibary.repositories

import com.example.medialibary.daos.VideoGamesDao
import com.example.medialibary.models.VideoGame

import kotlinx.coroutines.flow.MutableStateFlow

class VideoGamesRepository (private val videoGamesDao: VideoGamesDao
){
    val videoGames: MutableStateFlow<List<VideoGame>> = MutableStateFlow(emptyList()) // Copilot

    suspend fun addVideoGame(videoGame: VideoGame): Long {
        return videoGamesDao.insertVideoGame(videoGame)
    }
    suspend fun getVideoGames(): List<VideoGame> {
        return videoGamesDao.getAllVideoGames()
    }
}