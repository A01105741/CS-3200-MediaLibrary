package com.example.medialibary.ui.screens

import android.window.BackEvent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.viewmodels.CreateVideoGameScreenViewModel

@Composable
fun CreateVideoGameScreen(
    goBack: () -> Unit,
    viewModel: CreateVideoGameScreenViewModel = viewModel(factory = CreateVideoGameScreenViewModel.Factory)
) {
    var title by remember { mutableStateOf("") }
    var platform by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var developer by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Surface(
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium
    ){
        Column(Modifier.padding(16.dp)) {
            Text("Create Video Game", style = MaterialTheme.typography.headlineSmall)
            TextField(
                value=title,
                onValueChange = { title = it },
                label = { Text("Title") },
            )
            TextField(
                value=platform,
                onValueChange = { platform = it },
                label = { Text("Platform") },
            )
            TextField(
                value=rating,
                onValueChange = { rating = it },
                label = { Text("Rating") },
            )
            TextField(
                value=developer,
                onValueChange = { developer = it },
                label = { Text("Developer") },
            )
            TextField(
                value=genre,
                onValueChange = { genre = it },
                label = { Text("Genre") },
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
                    viewModel.saveVideoGame(
                        title = title,
                        platform = platform,
                        rating = rating,
                        developer = developer,
                        genre = genre,
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