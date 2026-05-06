package com.example.micromod.core.model

import androidx.compose.runtime.Stable

@Stable
data class ListItem(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val coordinates: Coordinates = Coordinates(0, 0)
)

data class Coordinates(
    val x: Int = 0,
    val y: Int = 0,
)