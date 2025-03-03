package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.VideoGame
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateVideoGameScreenViewModel(
    val videoGamesRepository: VideoGamesRepository,
): ViewModel() {
    fun saveVideoGame(
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        viewModelScope.launch {
            val videoGame = VideoGame(
                title = title,
                developer = developer,
                genre = genre,
                rating = rating,
                platform = platform,
                notes = notes
            )
            videoGame.id = videoGamesRepository.addVideoGame(videoGame)
            var videoGames = videoGame
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                CreateVideoGameScreenViewModel(
                    videoGamesRepository = application.videoGamesRepository
                )
            }
        }
    }
}