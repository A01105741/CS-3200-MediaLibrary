package com.example.medialibary

import android.app.Application
import androidx.room.Room
import com.example.medialibary.repositories.BoardGamesRepository
import com.example.medialibary.repositories.BooksRepository
import com.example.medialibary.repositories.MoviesRepository
import com.example.medialibary.repositories.VideoGamesRepository

class MediaLibraryApplication: Application() {
    private val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "media_library_database"
        ).build()
    }
    val moviesRepository by lazy {
        MoviesRepository(db.moviesDao)
    }

    val booksRepository by lazy {
        BooksRepository(db.booksDao)
    }

    val boardGamesRepository by lazy {
        BoardGamesRepository(db.boardGamesDao)
    }

    val videoGamesRepository by lazy {
        VideoGamesRepository(db.videoGamesDao)
    }
}