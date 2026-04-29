package com.example.micromod.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistResponse(
    val data: List<ArtistDetail>,
)

@Serializable
data class ArtistAlbumsResponse(
    val data: List<Album>
)

@Serializable
data class AlbumTracksResponse(
    val data: List<AlbumTrack>
)

@Serializable
data class Album(
    val id: Int,
    val title: String,
    @SerialName("cover_medium") val cover: String,
    @SerialName("cover_xl") val coverBig: String
)

@Serializable
data class AlbumTrack(
    val id: Int,
    val title: String,
    val duration: Int,
    @SerialName("album") val album: Album? = null
)

@Serializable
data class ArtistDetail(
    val id: Long,
    val name: String,
    val link: String,
    val picture: String,
    @SerialName("picture_small") val pictureSmall: String,
    @SerialName("picture_medium") val pictureMedium: String,
    @SerialName("picture_big") val pictureBig: String,
    @SerialName("picture_xl") val pictureXl: String,
    val tracklist: String,
    val type: String
)