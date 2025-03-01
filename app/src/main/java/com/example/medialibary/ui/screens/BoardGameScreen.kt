package com.example.medialibary.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.viewmodels.BoardGameScreenViewModel

@Composable
fun createBoardGameScreenViewModel(boardGameId: Long) = viewModel<BoardGameScreenViewModel>(
    factory = BoardGameScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[BoardGameScreenViewModel.BOARD_GAME_ID_KEY] = boardGameId
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)

@Composable
fun BoardGameScreen(
    id: Long,
    viewModel: BoardGameScreenViewModel = createBoardGameScreenViewModel(id)
) {
    val boardGame by viewModel.boardGame.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text("${boardGame?.title}", style = MaterialTheme.typography.headlineLarge)
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(8.dp))
        if (boardGame == null) {
            Text("Loading...")
        } else {
            Text("Type", style = MaterialTheme.typography.headlineSmall)
            Text("${boardGame?.type}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Players", style = MaterialTheme.typography.headlineSmall)
            Text("${boardGame?.minPlayers} - ${boardGame?.maxPlayers}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Notes", style = MaterialTheme.typography.headlineSmall)
            Text("${boardGame?.notes}")
        }
    }
}