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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class VideoGameScreenUIState(
    val videoGame: VideoGame? = null
)

class VideoGameScreenViewModel(
    private val videoGameId: Long?,
    private val videoGamesRepository: VideoGamesRepository
): ViewModel() {
    private val _uiVideoGameState = MutableStateFlow(VideoGameScreenUIState())
    val uiVideoGame: StateFlow<VideoGameScreenUIState> = _uiVideoGameState.asStateFlow()

    fun getVideoGameById(videoGameId: Long) {
        viewModelScope.launch {
            val videoGame = videoGamesRepository.getVideoGameById(videoGameId)
            _uiVideoGameState.update { it.copy(videoGame = videoGame) }
        }
    }
    
    companion object {
        val VIDEOGAME_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val videoGameId = this[VIDEOGAME_ID_KEY] as Long
                VideoGameScreenViewModel(
                    videoGameId,
                    application.videoGamesRepository
                )
            }
        }
    }
}