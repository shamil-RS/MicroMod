package com.example.micromod.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.micromod.core.model.AlbumItem
import com.example.micromod.core.model.AlbumTrackItem
import com.example.micromod.core.model.Coordinates
import com.example.micromod.core.model.ListItem
import com.example.micromod.network.di.ArtistApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.sqrt

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val api: ArtistApiService
) : ViewModel() {

    private val _state = MutableStateFlow(StateUi())
    val state = _state.asStateFlow()

    fun getArtist(name: String = "E") {
        viewModelScope.launch {
            try {
                val artistResponse = api.getArtist(artistName = name)

                val artists = artistResponse.data.mapIndexed { index, artist ->
                    val (x, y) = getSpiralCoordinates(index)

                    ListItem(
                        id = artist.id.toInt(),
                        name = artist.name,
                        image = artist.pictureMedium,
                        coordinates = Coordinates(x = x, y = y)
                    )
                }

                _state.update { it.copy(artistList = artists) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadArtistDetails(artistId: Int) {
        viewModelScope.launch {
            try {
                val albumsResponse = api.getArtistAlbums(artistId)
                val albums = albumsResponse.data.map {
                    AlbumItem(it.id, it.title, it.cover)
                }

                _state.update { it.copy(albumList = albums) }

                if (albums.isNotEmpty()) loadAlbumTracks(albums[0].id)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadAlbumTracks(albumId: Int) {
        viewModelScope.launch {
            try {
                val tracksResponse = api.getAlbumTracks(albumId)
                val tracks = tracksResponse.data.map {
                    AlbumTrackItem(it.id, it.title, it.duration)
                }
                _state.update { it.copy(albumTracksList = tracks) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getSpiralCoordinates(n: Int): Pair<Int, Int> {
        if (n == 0) return Pair(0, 0)
        val k = ceil((sqrt(n.toDouble() + 1) - 1) / 2).toInt()
        val t = 2 * k
        val m = (2 * k + 1) * (2 * k + 1)

        return when {
            n >= m - t -> Pair(k - (m - n), -k)
            n >= m - 2 * t -> Pair(-k, -k + (m - t - n))
            n >= m - 3 * t -> Pair(-k + (m - 2 * t - n), k)
            else -> Pair(k, k - (m - 3 * t - n))
        }
    }
}

data class StateUi(
    val artistList: List<ListItem> = emptyList(),
    val albumList: List<AlbumItem> = emptyList(),
    val albumTracksList: List<AlbumTrackItem> = emptyList(),
)