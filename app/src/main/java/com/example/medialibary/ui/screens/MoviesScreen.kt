package com.example.medialibary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.ui.components.Card
import com.example.medialibary.viewmodels.MoviesScreenViewModel

@Composable
fun MoviesScreen(
    onMovieClick: (id: Long?) -> Unit,
    onAddMovieClick: () -> Unit,
    viewModel: MoviesScreenViewModel = viewModel(factory = MoviesScreenViewModel.Factory)
) {

    //val movies by viewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val movies by viewModel.movies.collectAsState()

        Text("Movies", style = MaterialTheme.typography.headlineMedium)
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = GridCells.Adaptive(minSize = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(movies) { movie ->
                Card(
                    movie.title,
                    onClick = { onMovieClick(movie.id) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        /*
        TextButton(onClick = onAddMovieClick) {
            Text("+ Add Movie")
        }*/
        Button(
            onClick = onAddMovieClick
        ) {
            Text("Add Movie")
        }
    }
}