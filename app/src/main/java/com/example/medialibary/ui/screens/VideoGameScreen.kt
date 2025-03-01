package com.example.medialibary.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalRuler
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.viewmodels.CreateVideoGameScreenViewModel
import com.example.medialibary.viewmodels.VideoGameScreenViewModel
import com.example.medialibary.viewmodels.VideoGamesScreenViewModel

@Composable
fun createVideoGameScreenViewModel(videoGameId: Long) = viewModel<VideoGameScreenViewModel>(
    factory = VideoGameScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[VideoGameScreenViewModel.VIDEO_GAME_ID_KEY] = videoGameId
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)

@Composable
fun VideoGameScreen(
    id: Long,
    viewModel: VideoGameScreenViewModel = createVideoGameScreenViewModel(id)
) {
    val videoGame by viewModel.videoGame.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text("${videoGame?.title}", style = MaterialTheme.typography.headlineLarge)
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(8.dp))
        if (videoGame == null) {
            Text("Loading...")
        } else {
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Platform", style = MaterialTheme.typography.headlineSmall)
            Text("${videoGame?.platform}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Rating", style = MaterialTheme.typography.headlineSmall)
            Text("${videoGame?.rating}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Developer", style = MaterialTheme.typography.headlineSmall)
            Text("${videoGame?.developer}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Genre", style = MaterialTheme.typography.headlineSmall)
            Text("${videoGame?.genre}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Notes", style = MaterialTheme.typography.headlineSmall)
            Text("${videoGame?.notes}")
        }
    }
}