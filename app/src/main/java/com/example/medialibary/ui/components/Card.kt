package com.example.medialibary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Card(title: String, onClick: () -> Unit) {
    Surface (
        modifier = Modifier
            .size(120.dp)
            .clickable(onClick = onClick),
        shadowElevation = 1.dp,
        tonalElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
    ){
        Box (
            modifier = Modifier
//                .background(color = Color(64, 64, 64, 255))
            ,
            contentAlignment = Alignment.Center
        ){
            Text(
                title,
//                color = Color.White
            )
        }
    }
}