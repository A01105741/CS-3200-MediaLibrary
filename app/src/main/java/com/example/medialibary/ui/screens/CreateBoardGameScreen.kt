package com.example.medialibary.ui.screens

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.viewmodels.CreateBoardGameScreenViewModel

@Composable
fun CreateBoardGameScreen(
    goBack: () -> Unit,
    viewModel: CreateBoardGameScreenViewModel = viewModel(factory = CreateBoardGameScreenViewModel.Factory)
) {

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
                    viewModel.saveBoardGame(
                        title = title,
                        minPlayers = minPlayers.toInt(),
                        maxPlayers = maxPlayers.toInt(),
                        type=type,
                        notes = notes
                    )
                    goBack()
                }) {
                    Text("Save")
                }
            }
        }
    }
}