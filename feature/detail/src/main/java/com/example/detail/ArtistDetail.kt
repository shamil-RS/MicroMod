package com.example.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil.compose.AsyncImage
import com.example.micromod.core.designsystem.util.toMMSS
import com.example.micromod.core.model.AlbumItem
import com.example.micromod.core.model.AlbumTrackItem

@Composable
fun ArtistDetail(
    artistName: String,
    albums: List<AlbumItem>,
    tracks: List<AlbumTrackItem>,
    navBackStack: NavBackStack<NavKey>
) {
    val albumCover = albums.firstOrNull()?.image ?: ""

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                AsyncImage(
                    model = albumCover,
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .clickable { navBackStack.removeLastOrNull() },
                    contentDescription = "",
                    tint = Color.White
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
                        )
                )
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.BottomStart),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = artistName,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                    Text(
                        text = albums.firstOrNull()?.title ?: "",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                    )
                }
            }
        }

        items(tracks) { track ->
            TrackRow(
                title = track.title,
                imageUrl = albumCover,
                duration = track.duration
            )
        }
    }
}

@Composable
fun TrackRow(
    title: String,
    imageUrl: String,
    duration: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(text = duration.toMMSS(), color = Color.Gray, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "", tint = Color.White)
    }
}