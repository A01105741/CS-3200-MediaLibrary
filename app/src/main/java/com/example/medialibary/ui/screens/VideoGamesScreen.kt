package com.example.medialibary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.ui.components.Card
import com.example.medialibary.viewmodels.VideoGamesScreenViewModel

@Composable
fun VideoGamesScreen(
    onVideoGameClick: (id: Long?) -> Unit,
    onAddVideoGameClick: () -> Unit,
    viewModel: VideoGamesScreenViewModel = viewModel(factory = VideoGamesScreenViewModel.Factory)
) {

    LaunchedEffect(Unit) {
        viewModel.loadVideoGames()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val videoGames by viewModel.videoGames.collectAsState(initial = emptyList())

        Text("Video Games", style = MaterialTheme.typography.headlineMedium)
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = GridCells.Adaptive(minSize = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(videoGames) { videoGame ->
                Card(
                    videoGame.title,
                    onClick = { onVideoGameClick(videoGame.id) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Button(
            onClick = onAddVideoGameClick
        ) {
            Text("Add Video Game")
        }
    }
}