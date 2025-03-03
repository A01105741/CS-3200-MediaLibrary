package com.example.medialibary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val genre: String,
    @ColumnInfo val rating: String,
    @ColumnInfo val runtime: Int,
    @ColumnInfo val format: String,
    @ColumnInfo val notes: String
)