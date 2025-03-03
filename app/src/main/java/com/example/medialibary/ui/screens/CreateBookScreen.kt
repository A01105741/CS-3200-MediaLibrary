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
import com.example.medialibary.viewmodels.CreateBookScreenViewModel

@Composable
fun createBookScreenViewModel(bookId: Long?) = viewModel<CreateBookScreenViewModel>(
    factory = CreateBookScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[CreateBookScreenViewModel.BOOK_ID_KEY] = bookId
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)

@Composable
fun CreateBookScreen(
    goBack: () -> Unit,
    viewModel: CreateBookScreenViewModel = viewModel(factory = CreateBookScreenViewModel.Factory)
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var pages by remember { mutableStateOf("") }
    var format by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Surface(
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium
    ){
        Column(Modifier.padding(16.dp)) {
            Text("Create Book", style = MaterialTheme.typography.headlineSmall)
            TextField(
                value=title,
                onValueChange = { title = it },
                label = { Text("Title") },
            )
            TextField(
                value=author,
                onValueChange = { author = it },
                label = { Text("Author") },
            )
            TextField(
                value=pages,
                onValueChange = { pages = it },
                label = { Text("Pages") },
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
                    // Input validation
                    if (title.isBlank()) {
                        Toast.makeText(context, "Title cannot be empty - please fix the error", Toast.LENGTH_SHORT).show()
                    } else if (pages.isNotBlank() && pages.toIntOrNull() == null) {
                        Toast.makeText(context, "Pages has to be a number", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.saveBook(
                        title = title,
                        author = author,
                        format = format,
                        pages = pages.toInt(),
                        notes = notes
                    )
                        Toast.makeText(context, "Book added", Toast.LENGTH_SHORT).show()
                        goBack()
                    }
                }) {
                    Text("Save")
                }
            }
        }
    }
}
