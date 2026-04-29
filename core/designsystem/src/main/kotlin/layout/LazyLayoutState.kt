package com.example.micromod.core.designsystem.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import com.example.micromod.core.model.ViewBoundaries

@Composable
fun rememberLazyLayoutState(
    initialOffset: IntOffset = IntOffset.Zero
): LazyLayoutState {
    return remember { LazyLayoutState(initialOffset) }
}

@Stable
class LazyLayoutState(initialOffset: IntOffset = IntOffset.Zero) {

    private val _offsetState = mutableStateOf(initialOffset)
    val offsetState: State<IntOffset>
        get() = _offsetState

    fun onDrag(offset: IntOffset) {
        val x = _offsetState.value.x - offset.x
        val y = _offsetState.value.y - offset.y
        _offsetState.value = IntOffset(x, y)
    }

    fun getBoundaries(
        constraints: Constraints,
        threshold: Int = 900
    ): ViewBoundaries {
        return ViewBoundaries(
            fromX = offsetState.value.x - threshold,
            toX = constraints.maxWidth + offsetState.value.x + threshold,
            fromY = offsetState.value.y - threshold,
            toY = constraints.maxHeight + offsetState.value.y + threshold
        )
    }
}