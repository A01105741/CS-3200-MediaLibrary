package com.example.medialibary.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.medialibary.models.Book

@Dao
abstract class BooksDao {
    @Query("SELECT * FROM book")
    abstract suspend fun getAllBooks(): List<Book>
    @Query("SELECT * FROM book WHERE id = :id")
    abstract suspend fun getBookById(id: Long?): Book?
    @Insert
    abstract suspend fun insertBook(book: Book): Long  // returns the id of the new book
    @Update
    abstract suspend fun updateBook(book: Book)
}