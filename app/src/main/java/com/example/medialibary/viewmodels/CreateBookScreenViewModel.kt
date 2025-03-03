package com.example.medialibary.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.Book

import com.example.medialibary.repositories.BooksRepository
import com.example.medialibary.viewmodels.CreateMovieScreenViewModel.Companion.MOVIE_ID_KEY

import kotlinx.coroutines.launch

class CreateBookScreenViewModel(
   private val booksRepository: BooksRepository,
): ViewModel() {
   // var title by remember { mutableStateOf("") }
   // var author by remember { mutableStateOf("") }
   // var pages by remember { mutableStateOf("") }
   // var format by remember { mutableStateOf("") }
   // var notes by remember { mutableStateOf("") }

   // private val _books = MutableStateFlow(emptyList<Book>()) // Copilot
   // val books: StateFlow<List<Book>> = _books

    fun saveBook(
        title: String,
        author: String,
        format: String,
        pages: Int,
        notes: String
    ) {
        viewModelScope.launch {
            val newBook = Book(
                title = title,
                author = author,
                format = format,
                pages = pages,
                notes = notes
            )
           // book.id =  booksRepository.addBook(book)
           // var books = book
            booksRepository.addBook(newBook.title,
                newBook.author,
                newBook.pages,
                newBook.format,
                newBook.notes)
        }
    }

    companion object {
        val BOOK_ID_KEY = object : CreationExtras.Key<Long?> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val bookId = this[BOOK_ID_KEY] //as Long?
                CreateBookScreenViewModel(
                    booksRepository = application.booksRepository
                )
            }
        }
    }
}