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
import androidx.compose.material3.OutlinedTextField
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
import com.example.medialibary.viewmodels.CreateMovieScreenViewModel
import com.example.medialibary.viewmodels.CreateVideoGameScreenViewModel
import com.example.medialibary.viewmodels.MoviesScreenViewModel

@Composable
fun CreateMovieScreen(
    goBack: () -> Unit,
    viewModel: CreateMovieScreenViewModel = viewModel(factory = CreateMovieScreenViewModel.Factory)
) {

    var title by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var runtime by remember { mutableStateOf("") }
    var format by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Surface(
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium
    ){
        Column(Modifier.padding(16.dp)) {
            Text("Create Movie", style = MaterialTheme.typography.headlineSmall)
            TextField(
                value=title,
                onValueChange = { title = it },
                label = { Text("Title") },
            )
            TextField(
                value=genre,
                onValueChange = { genre = it },
                label = { Text("Genre") },
            )
            TextField(
                value=rating,
                onValueChange = { rating = it },
                label = { Text("Rating") },
            )
            TextField(
                value=runtime,
                onValueChange = { runtime = it },
                label = { Text("Runtime") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value=format,
                onValueChange = { format = it },
                label = { Text("Format") },
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
                    viewModel.saveMovie(
                        title = title,
                        genre = genre,
                        rating = rating,
                        runtime = runtime.toInt(),
                        format = format,
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