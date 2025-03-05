package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.VideoGame
import com.example.medialibary.repositories.VideoGamesRepository
import com.example.medialibary.viewmodels.CreateBoardGameScreenViewModel.Companion.BOARDGAME_ID_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateVideoGameScreenViewModel(
    val videoGamesRepository: VideoGamesRepository,
): ViewModel() {

    private val _videoGames = MutableStateFlow(emptyList<VideoGame>()) // Copilot
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    fun saveVideoGame(
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        viewModelScope.launch {
            val newVideoGame = VideoGame(
                title = title,
                developer = developer,
                genre = genre,
                rating = rating,
                platform = platform,
                notes = notes
            )
            videoGamesRepository.addVideoGame(
                newVideoGame.title,
                newVideoGame.developer,
                newVideoGame.genre,
                newVideoGame.rating,
                newVideoGame.platform,
                newVideoGame.notes)
        }
    }

    companion object {
        val VIDEOGAME_ID_KEY = object : CreationExtras.Key<Long?> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val videoGameId = this[VIDEOGAME_ID_KEY] //as Long?
                CreateVideoGameScreenViewModel(
                    videoGamesRepository = application.videoGamesRepository
                )
            }
        }
    }
}