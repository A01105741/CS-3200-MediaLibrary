package com.example.medialibary.models


data class Movie(
    val id: Long,
    val title: String,
    val genre: String,
    val rating: String,
    val runtime: Int,
    val format: String,
    val notes: String
)