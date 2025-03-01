package com.example.medialibary.models

data class BoardGame(
    val id: Long,
    val title: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val type: String,
    val notes: String
)