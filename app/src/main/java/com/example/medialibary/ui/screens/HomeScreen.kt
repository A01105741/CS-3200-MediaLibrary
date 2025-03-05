package com.example.medialibary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    onVideoGamesClick: () -> Unit,
    onMoviesClick: () -> Unit,
    onBoardGamesClick: () -> Unit,
    onBooksClick: () -> Unit,
    viewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory)
) {

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onVideoGamesClick
        ) {
            Text("Video Games (${uiState.videoGameCount})")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onMoviesClick
        ) {
            Text("Movies (${uiState.movieCount})")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBoardGamesClick
        ) {
            Text("Board Games (${uiState.boardGameCount})")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBooksClick
        ) {
            Text("Books (${uiState.bookCount})")
        }
    }
}