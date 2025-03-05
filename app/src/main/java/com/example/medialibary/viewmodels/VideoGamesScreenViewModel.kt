package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.VideoGame
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoGamesScreenViewModel(
    val videoGamesRepository: VideoGamesRepository
): ViewModel() {
    private val _videoGames = MutableStateFlow<List<VideoGame>>(emptyList())
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    fun loadVideoGames() {
        viewModelScope.launch {
            videoGamesRepository.loadVideoGames()
        }
    }

    init {
        viewModelScope.launch {
            videoGamesRepository.videoGames.collect { videoGames ->
                _videoGames.value = videoGames
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                VideoGamesScreenViewModel(
                    application.videoGamesRepository
                )
            }
        }
    }
}