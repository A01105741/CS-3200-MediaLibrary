package com.example.medialibary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medialibary.daos.MoviesDao
import com.example.medialibary.daos.BooksDao
import com.example.medialibary.daos.BoardGamesDao
import com.example.medialibary.daos.VideoGamesDao
import com.example.medialibary.models.Movie
import com.example.medialibary.models.Book
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.VideoGame

@Database(entities = [Movie::class, Book::class, BoardGame::class, VideoGame::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val moviesDao: MoviesDao //  abstract fun moviesDao(): MoviesDao
    abstract val booksDao: BooksDao
    abstract val videoGamesDao: VideoGamesDao
    abstract val boardGamesDao: BoardGamesDao
}


