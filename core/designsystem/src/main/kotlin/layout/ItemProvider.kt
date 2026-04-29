package com.example.micromod.core.designsystem.layout

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.micromod.core.model.ListItem
import com.example.micromod.core.model.ViewBoundaries

@Composable
fun rememberItemProvider(customLazyListScope: CustomLazyListScope.() -> Unit): ItemProvider {
    val customLazyListScopeState = remember { mutableStateOf(customLazyListScope) }.apply {
        value = customLazyListScope
    }

    return remember {
        ItemProvider(
            itemsState = derivedStateOf {
                val layoutScope = CustomLazyListScopeImpl().apply(customLazyListScopeState.value)
                layoutScope.items
            }
        )
    }
}

class ItemProvider(
    private val itemsState: State<List<LazyLayoutItemContent>>
) : LazyLayoutItemProvider {

    override val itemCount
        get() = itemsState.value.size

    @Composable
    override fun Item(index: Int, key: Any) {
        val item = itemsState.value.getOrNull(index)
        item?.itemContent?.invoke(item.item)
    }

    fun getItemIndexesInRange(boundaries: ViewBoundaries, stepX: Int, stepY: Int): List<Int> {
        val result = mutableListOf<Int>()
        itemsState.value.forEachIndexed { index, itemContent ->
            val item = itemContent.item

            val realX = item.coordinates.x * stepX
            val realY = item.coordinates.y * stepY

            if (realX in boundaries.fromX..boundaries.toX &&
                realY in boundaries.fromY..boundaries.toY
            ) result.add(index)
        }
        return result
    }

    fun getItem(index: Int): ListItem? {
        return itemsState.value.getOrNull(index)?.item
    }
}