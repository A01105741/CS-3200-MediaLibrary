package com.example.medialibary.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.BoardGame
import com.example.medialibary.viewmodels.CreateBoardGameScreenViewModel
import com.example.medialibary.viewmodels.CreateMovieScreenViewModel

@Composable
fun CreateBoardGameScreen(
    goBack: () -> Unit,
    viewModel: CreateBoardGameScreenViewModel = viewModel(factory = CreateBoardGameScreenViewModel.Factory)
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var minPlayers by remember { mutableStateOf("") }
    var maxPlayers by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Surface(
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium
    ){
        Column(Modifier.padding(16.dp)) {
            Text("Create Board Game", style = MaterialTheme.typography.headlineSmall)
            TextField(
                value=title,
                onValueChange = { title = it },
                label = { Text("Title") },
            )
            TextField(
                value=minPlayers,
                onValueChange = { minPlayers = it },
                label = { Text("Min Players") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value=maxPlayers,
                onValueChange = { maxPlayers = it },
                label = { Text("Max Players") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value=type,
                onValueChange = { type = it },
                label = { Text("Type") },
            )
            TextField(
                value=notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier.weight(1f)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                TextButton(
                    onClick = goBack,
                ) {
                    Text("Cancel")
                }
                Button(onClick = {
                    if (title.isBlank()) {
                        Toast.makeText(context, "Title cannot be empty - please fix the error", Toast.LENGTH_SHORT).show()
                    } else if (minPlayers.isBlank() || minPlayers.toIntOrNull() == null) {
                        Toast.makeText(context, "Minimum Players has to be a number", Toast.LENGTH_SHORT).show()
                    }
                    else if (maxPlayers.isBlank() || maxPlayers.toIntOrNull() == null) {
                        Toast.makeText(context, "Maximum Players has to be a number", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        viewModel.saveBoardGame(
                            title = title,
                            minPlayers = minPlayers.toInt(),
                            maxPlayers = maxPlayers.toInt(),
                            type=type,
                            notes = notes
                        )
                        Toast.makeText(context, "Board Game added", Toast.LENGTH_SHORT).show()
                        goBack()
                    }
                }) {
                    Text("Save")
                }
            }
        }
    }
}