package com.example.medialibary.ui.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.viewmodels.MovieScreenViewModel

@Composable
fun createMovieScreenViewModel(movieId: Long) = viewModel<MovieScreenViewModel>(
    factory = MovieScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[MovieScreenViewModel.MOVIE_ID_KEY] = movieId
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)

@Composable
fun MovieScreen(
    id: Long,
    viewModel: MovieScreenViewModel = createMovieScreenViewModel(id)
) {
    val movie by viewModel.movie.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text("${movie?.title}", style = MaterialTheme.typography.headlineLarge)
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(8.dp))
        if (movie == null) {
            Text("Loading...")
        } else {
            Text("Genre", style = MaterialTheme.typography.headlineSmall)
            Text("${movie?.genre}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Rating", style = MaterialTheme.typography.headlineSmall)
            Text("${movie?.rating}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Runtime", style = MaterialTheme.typography.headlineSmall)
            Text("${movie?.runtime}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Format", style = MaterialTheme.typography.headlineSmall)
            Text("${movie?.format}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Notes", style = MaterialTheme.typography.headlineSmall)
            Text("${movie?.notes}")
        }
    }
}