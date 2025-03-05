package com.example.medialibary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.medialibary.viewmodels.MovieScreenViewModel

@Composable
fun createBoardGameScreenViewModel(boardGameId: Long?) = viewModel<BoardGameScreenViewModel>(
    factory = BoardGameScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[BoardGameScreenViewModel.BOARDGAME_ID_KEY] = boardGameId?: 0L
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)

@Composable
fun BoardGameScreen(
    goBack: () -> Unit,
    id: Long,
    viewModel: BoardGameScreenViewModel = viewModel(factory = BoardGameScreenViewModel.Factory)
) {
    val uiBoardGameState by viewModel.uiBoardGame.collectAsState()
    viewModel.getBoardGameById(id)

    Column(modifier = Modifier.padding(16.dp)) {
        uiBoardGameState.boardGame?.let { boardGame ->
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    goBack()
                }) {
                    Text("Back")
                }
            }
        }
    }
}