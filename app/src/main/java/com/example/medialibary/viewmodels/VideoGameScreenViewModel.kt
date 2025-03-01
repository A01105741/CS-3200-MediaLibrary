package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.VideoGame
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoGameScreenViewModel(
    videoGamesRepository: VideoGamesRepository,
    videoGameId: Long
): ViewModel() {
    private val _videoGame = MutableStateFlow<VideoGame?>(null)
    val videoGame: StateFlow<VideoGame?> = _videoGame

    init {
        viewModelScope.launch {
            videoGamesRepository.videoGames.collect { videoGames ->
                _videoGame.value = videoGames.find { it.id == videoGameId }
            }
        }
    }

    companion object {
        val VIDEO_GAME_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val videoGameId = this[VIDEO_GAME_ID_KEY] as Long
                VideoGameScreenViewModel(
                    application.videoGamesRepository,
                    videoGameId
                )
            }
        }
    }
}