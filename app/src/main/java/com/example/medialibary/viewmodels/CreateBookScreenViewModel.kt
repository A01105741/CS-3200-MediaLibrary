package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.repositories.BooksRepository
import kotlinx.coroutines.launch

class CreateBookScreenViewModel(
    val booksRepository: BooksRepository,
): ViewModel() {
    fun saveBook(
        title: String,
        author: String,
        format: String,
        pages: Int,
        notes: String
    ) {
        viewModelScope.launch {
            booksRepository.addBook(
                title = title,
                author = author,
                format = format,
                pages = pages,
                notes = notes
            )
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                CreateBookScreenViewModel(
                    booksRepository = application.booksRepository
                )
            }
        }
    }
}