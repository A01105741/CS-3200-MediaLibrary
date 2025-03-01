package com.example.medialibary

import android.app.Application
import com.example.medialibary.repositories.BoardGamesRepository
import com.example.medialibary.repositories.BooksRepository
import com.example.medialibary.repositories.MoviesRepository
import com.example.medialibary.repositories.VideoGamesRepository

class MediaLibraryApplication: Application() {
    val booksRepository = BooksRepository
    val moviesRepository = MoviesRepository
    val videoGamesRepository = VideoGamesRepository
    val boardGamesRepository = BoardGamesRepository
}