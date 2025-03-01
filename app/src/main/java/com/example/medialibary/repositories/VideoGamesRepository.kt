package com.example.medialibary.repositories

import com.example.medialibary.models.VideoGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

object VideoGamesRepository {
    private var ID_COUNTER = 0L
    private val _videoGames = MutableStateFlow<List<VideoGame>>(emptyList())
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    suspend fun addVideoGame(
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        withContext(Dispatchers.IO) {
            _videoGames.value += VideoGame(
                id = ID_COUNTER++,
                title = title,
                developer = developer,
                genre = genre,
                rating = rating,
                platform = platform,
                notes = notes
            )
        }
    }
}