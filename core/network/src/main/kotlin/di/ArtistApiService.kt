package com.example.micromod.network.di

import com.example.micromod.core.model.AlbumTracksResponse
import com.example.micromod.core.model.ArtistAlbumsResponse
import com.example.micromod.core.model.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistApiService {
    @GET("search/artist")
    suspend fun getArtist(
        @Query("q") artistName: String,
        @Query("limit") limit: Int = 2000
    ): ArtistResponse

    @GET("artist/{id}/albums")
    suspend fun getArtistAlbums(@Path("id") artistId: Int): ArtistAlbumsResponse

    @GET("album/{id}/tracks")
    suspend fun getAlbumTracks(@Path("id") albumId: Int): AlbumTracksResponse
}