package com.example.medialibary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibary.MediaLibraryApplication
import com.example.medialibary.models.Book
import com.example.medialibary.models.Movie
import com.example.medialibary.models.VideoGame
import com.example.medialibary.repositories.BooksRepository
import com.example.medialibary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BookScreenUIState(
    val book: Book? = null
)

class BookScreenViewModel(
    private val bookId: Long?,
    private val booksRepository: BooksRepository,

): ViewModel() {
   // private val _book = MutableStateFlow<Book?>(null)
   private val _uiBookState  = MutableStateFlow(BookScreenUIState())
   // val book: StateFlow<Book?> = _book
   val uiBookState: StateFlow<BookScreenUIState> = _uiBookState.asStateFlow()

    fun getBookById(bookId: Long) {
        viewModelScope.launch {
            val book = booksRepository.getBookById(bookId)
            _uiBookState.update { it.copy(book = book) }
        }
    }

    /*
    init {
        viewModelScope.launch {
            booksRepository.books.collect { books ->
                _book.value = books.find { it.id == bookId }
            }
        }
    }
 */

    companion object {
        val BOOK_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val bookId = this[BOOK_ID_KEY] //as Long
                BookScreenViewModel(
                    bookId,
                    booksRepository = application.booksRepository)
            }
        }
    }
}