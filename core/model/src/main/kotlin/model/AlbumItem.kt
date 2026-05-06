package com.example.micromod.core.model

import androidx.compose.runtime.Stable

@Stable
data class AlbumItem(
    val id: Int = 0,
    val title: String,
    val image: String
)