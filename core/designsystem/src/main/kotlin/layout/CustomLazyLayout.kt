package com.example.micromod.core.designsystem.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.micromod.core.designsystem.util.lazyLayoutPointerInput
import com.example.micromod.core.designsystem.util.placeItem

@Composable
fun CustomLazyLayout(
    modifier: Modifier = Modifier,
    state: LazyLayoutState,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(0.dp),
    content: CustomLazyListScope.() -> Unit
) {
    val itemProvider = rememberItemProvider(content)
    val density = LocalDensity.current

    val horizontal = with(density) { horizontalArrangement.spacing.roundToPx() }
    val vertical = with(density) { verticalArrangement.spacing.roundToPx() }

    LazyLayout(
        modifier = modifier
            .clipToBounds()
            .lazyLayoutPointerInput(state)
            .background(Color.Black),
        itemProvider = { itemProvider },
    ) { constraints ->
        val width = constraints.maxWidth / 3
        val height = (width * 1.85f).toInt()

        val gridStepXPx = width + horizontal
        val gridStepYPx = height + vertical

        val boundaries = state.getBoundaries(constraints)
        val indexes = itemProvider.getItemIndexesInRange(boundaries, gridStepXPx, gridStepYPx)

        val placeables = indexes.associateWith { index ->
            compose(index).map { it.measure(Constraints.fixed(width, height)) }
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { (index, measuredPlaceables) ->
                val item = itemProvider.getItem(index)
                item?.let {
                    placeItem(
                        state = state,
                        listItem = it,
                        placeables = measuredPlaceables,
                        gridStepX = gridStepXPx,
                        gridStepY = gridStepYPx
                    )
                }
            }
        }
    }
}
