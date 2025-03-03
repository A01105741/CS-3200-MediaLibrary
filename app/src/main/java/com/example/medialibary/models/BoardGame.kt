package com.example.medialibary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BoardGame(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val minPlayers: Int,
    @ColumnInfo val maxPlayers: Int,
    @ColumnInfo val type: String,
    @ColumnInfo val notes: String
)