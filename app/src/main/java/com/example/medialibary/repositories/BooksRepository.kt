package com.example.medialibary.repositories

import com.example.medialibary.daos.BooksDao
import com.example.medialibary.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BooksRepository (
    private val booksDao: BooksDao
){
    private val _books = MutableStateFlow(emptyList<Book>())
    val books: StateFlow<List<Book>> = _books

    suspend fun loadBooks() {
        _books.value = booksDao.getAllBooks()
    }
    suspend fun getBookById(id: Long?): Book? = booksDao.getBookById(id)

    suspend fun addBook(title: String,  author: String,  pages: Int,  format: String,  notes: String){
        val newBook = Book(
            title = title,
            author = author,
            format = format,
            pages = pages,
            notes = notes
        )
        newBook.id = booksDao.insertBook(newBook)
        _books.value += newBook
    }
}