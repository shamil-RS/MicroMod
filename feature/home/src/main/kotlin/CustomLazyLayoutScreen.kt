package com.example.micromod.feature.home

import DetailCardArtist
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil.compose.AsyncImage
import com.example.micromod.core.designsystem.layout.CustomLazyLayout
import com.example.micromod.core.designsystem.layout.LocalConfig
import com.example.micromod.core.designsystem.layout.rememberLazyLayoutState
import com.example.micromod.core.model.ListItem

val LocalConfig = staticCompositionLocalOf { LocalConfig() }

@Composable
fun CustomLazyLayoutScreen(artists: List<ListItem>, navStack: NavBackStack<NavKey>) {
    val config = LocalConfig.current

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val initialOffset = remember {
        val screenWidthPx = with(density) { configuration.screenWidthDp.dp.roundToPx() }
        val screenHeightPx = with(density) { configuration.screenHeightDp.dp.roundToPx() }
        val itemWidthPx = with(density) { config.itemWidth.roundToPx() }
        val itemHeightPx = with(density) { config.itemHeight.roundToPx() }

        IntOffset(
            x = -(screenWidthPx / 2) + (itemWidthPx / 2),
            y = -(screenHeightPx / 2) + (itemHeightPx / 2)
        )
    }

    val state = rememberLazyLayoutState(initialOffset)

    Box(modifier = Modifier.fillMaxSize()) {
        CustomLazyLayout(
            state = state,
            horizontalArrangement = Arrangement.spacedBy(config.spacing),
            verticalArrangement = Arrangement.spacedBy(config.spacing),
        ) {
            items(artists) { artist ->
                ArtistCard(
                    item = artist,
                    onClick = { navStack.add(DetailCardArtist(artist.id, artist.name)) }
                )
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun ArtistCard(
    modifier: Modifier = Modifier,
    item: ListItem,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
    ) {

        AsyncImage(
            model = item.image,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 300f
                    )
                )
        )

        Text(
            text = item.name,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp, start = 4.dp, end = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}