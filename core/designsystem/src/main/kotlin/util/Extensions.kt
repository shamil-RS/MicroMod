package com.example.micromod.core.designsystem.util

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.IntOffset
import com.example.micromod.core.designsystem.layout.LazyLayoutState
import com.example.micromod.core.model.ListItem

fun Modifier.lazyLayoutPointerInput(state: LazyLayoutState): Modifier {
    return pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            state.onDrag(IntOffset(dragAmount.x.toInt(), dragAmount.y.toInt()))
        }
    }
}

fun Placeable.PlacementScope.placeItem(
    state: LazyLayoutState,
    listItem: ListItem,
    placeables: List<Placeable>,
    gridStepX: Int,
    gridStepY: Int
) {
    val xPosition = (listItem.coordinates.x * gridStepX) - state.offsetState.value.x
    val yPosition = (listItem.coordinates.y * gridStepY) - state.offsetState.value.y

    placeables.forEach { placeable ->
        placeable.placeRelative(xPosition, yPosition)
    }
}

fun Int.toMMSS(): String {
    val minutes = this / 60
    val seconds = this % 60
    return "%d:%02d".format(minutes, seconds)
}