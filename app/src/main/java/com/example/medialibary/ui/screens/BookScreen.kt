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
import com.example.medialibary.viewmodels.BookScreenViewModel
import com.example.medialibary.viewmodels.MovieScreenViewModel

@Composable
fun bookScreenViewModel(bookId: Long?) = viewModel<BookScreenViewModel>(
    factory = BookScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[BookScreenViewModel.BOOK_ID_KEY] =  bookId ?: 0L
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)

@Composable
fun BookScreen(
    goBack: () -> Unit,
    id: Long,
    viewModel: BookScreenViewModel = viewModel(factory = BookScreenViewModel.Factory)
   // viewModel: BookScreenViewModel = createBookScreenViewModel(id)
) {
   // val book by viewModel.book.collectAsState()

    val uiState by viewModel.uiBookState.collectAsState()
    viewModel.getBookById(id)

    Column(modifier = Modifier.padding(16.dp)) {
        uiState.book?.let { book ->
            Text("${book?.title}", style = MaterialTheme.typography.headlineLarge)
            HorizontalDivider()
            Spacer(modifier = Modifier.padding(8.dp))
            if (book == null) {
                Text("Loading...")
            } else {
                Text("Author", style = MaterialTheme.typography.headlineSmall)
                Text("${book?.author}")
                Spacer(modifier = Modifier.padding(8.dp))
                Text("Pages", style = MaterialTheme.typography.headlineSmall)
                Text("${book?.pages}")
                Spacer(modifier = Modifier.padding(8.dp))
                Text("Format", style = MaterialTheme.typography.headlineSmall)
                Text("${book?.format}")
                Spacer(modifier = Modifier.padding(8.dp))
                Text("Notes", style = MaterialTheme.typography.headlineSmall)
                Text("${book?.notes}")
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