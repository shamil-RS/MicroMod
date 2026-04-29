package com.example.micromod

import ArtistScreenNav
import DetailCardArtist
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.detail.ArtistDetail
import com.example.micromod.feature.home.ArtistViewModel
import com.example.micromod.feature.home.CustomLazyLayoutScreen

@Composable
fun NavStacks() {
    val navBackStack = rememberNavBackStack(ArtistScreenNav)
    val viewModel: ArtistViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    NavDisplay(
        backStack = navBackStack,
        entryProvider = entryProvider {
            entry<ArtistScreenNav> {
                LaunchedEffect(Unit) {
                    if (state.artistList.isEmpty()) viewModel.getArtist()
                }
                CustomLazyLayoutScreen(artists = state.artistList, navBackStack)
            }

            entry<DetailCardArtist> { args ->
                LaunchedEffect(args.id) {
                    viewModel.loadArtistDetails(args.id)
                }

                ArtistDetail(
                    artistName = args.name,
                    albums = state.albumList,
                    tracks = state.albumTracksList,
                    navBackStack = navBackStack
                )
            }
        }
    )
}