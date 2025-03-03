package com.example.medialibary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.viewmodels.CreateMovieScreenViewModel
import com.example.medialibary.viewmodels.MovieScreenViewModel

@Composable
fun movieScreenViewModel(movieId: Long?) = viewModel<MovieScreenViewModel>(
    factory = MovieScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        //this[MovieScreenViewModel.MOVIE_ID_KEY] = movieId
        this[MovieScreenViewModel.MOVIE_ID_KEY] = movieId ?: 0L
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)

@Composable
fun MovieScreen(
    goBack: () -> Unit,
    id: Long,
    viewModel: MovieScreenViewModel = viewModel(factory = MovieScreenViewModel.Factory)
) {
    /*
    LaunchedEffect(Unit) {
        viewModel.getMovieById(id)
    }
*/
    val uiState by viewModel.uiMovieState.collectAsState()
    viewModel.getMovieById(id)


    //var movie by viewModel.movie.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        uiState.movie?.let { movie ->
            Text("${movie?.title}", style = MaterialTheme.typography.headlineLarge)
            HorizontalDivider()
            Spacer(modifier = Modifier.padding(8.dp))

            if (movie == null) {
                Text("No Data was found...")
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
                Spacer(modifier = Modifier.padding(16.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
  /*              TextButton(
                    onClick = goBack,
                ) {
                    Text("Back")
                }*/
                Button(onClick = {
                    goBack()
                }) {
                    Text("Back")
                }
            }
        }
    }
}