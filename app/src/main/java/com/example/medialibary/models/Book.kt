package com.example.medialibary.models

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val format: String,
    val pages: Int,
    val notes: String
)