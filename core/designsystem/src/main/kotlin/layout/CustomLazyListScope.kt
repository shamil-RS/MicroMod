package com.example.micromod.core.designsystem.layout

import com.example.micromod.core.model.ListItem

interface CustomLazyListScope {
    fun items(items: List<ListItem>, itemContent: ComposableItemContent)
}

class CustomLazyListScopeImpl : CustomLazyListScope {

    private val _items = mutableListOf<LazyLayoutItemContent>()
    val items: List<LazyLayoutItemContent> = _items
    override fun items(items: List<ListItem>, itemContent: ComposableItemContent) {
        items.forEach { data ->
            _items.add(
                LazyLayoutItemContent(
                    item = data,
                    itemContent = { listItem -> itemContent(listItem) }
                )
            )
        }
    }
}