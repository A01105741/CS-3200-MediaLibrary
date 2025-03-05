package com.example.medialibary.repositories

import com.example.medialibary.daos.VideoGamesDao
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.VideoGame

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoGamesRepository (private val videoGamesDao: VideoGamesDao
){
    private val _videoGames = MutableStateFlow(emptyList<VideoGame>())
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    suspend fun loadVideoGames() {
        _videoGames.value = videoGamesDao.getAllVideoGames()
    }
    suspend fun getVideoGameById(id: Long?): VideoGame? = videoGamesDao.getVideoGameById(id)

    suspend fun addVideoGame(title: String,
                             developer: String,
                             genre: String,
                             rating: String,
                             platform: String,
                             notes: String) {
        val newVideoGame = VideoGame(title = title,
        developer = developer,
        genre = genre,
        rating = rating,
        platform = platform,
        notes = notes)
        newVideoGame.id = videoGamesDao.insertVideoGame(newVideoGame)
        _videoGames.value += newVideoGame
    }

}