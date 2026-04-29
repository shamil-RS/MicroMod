package com.example.micromod.core.designsystem.layout

import androidx.compose.runtime.Composable
import com.example.micromod.core.model.ListItem

typealias ComposableItemContent = @Composable (ListItem) -> Unit

data class LazyLayoutItemContent(
    val item: ListItem,
    val itemContent: ComposableItemContent
)