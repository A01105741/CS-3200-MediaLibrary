package com.example.medialibary

import kotlinx.serialization.Serializable

object Destinations {
    @Serializable object Home
    @Serializable class VideoGame(val id: Long)
    @Serializable object VideoGames
    @Serializable object CreateVideoGame

    @Serializable class BoardGame(val id: Long)
    @Serializable object BoardGames
    @Serializable object CreateBoardGame

    @Serializable class Book(val id: Long)
    @Serializable object Books
    @Serializable object CreateBook

    @Serializable class Movie(val id: Long)
    @Serializable object Movies
    @Serializable object CreateMovie
}