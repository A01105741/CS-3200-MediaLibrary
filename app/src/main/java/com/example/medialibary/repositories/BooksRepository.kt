package com.example.medialibary.repositories

import com.example.medialibary.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BooksRepository {
    private var ID_COUNTER = 0L
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    // add a book to the list
    suspend fun addBook(
        title: String,
        author: String,
        format: String,
        pages: Int,
        notes: String,
    ) {
        _books.value += Book(
            id = ID_COUNTER++,
            title = title,
            author = author,
            format = format,
            pages = pages,
            notes = notes
        )
    }
}