package com.androidlead.movietheater.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidlead.movietheater.MovieViewModel
import com.androidlead.movietheater.ui.components.section.ComingSoonSection
import com.androidlead.movietheater.ui.components.section.FeaturedMoviesSection
import com.androidlead.movietheater.ui.components.section.HorizontalSection

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel
) {
    val scrollState = rememberScrollState()

    // Collect movies using StateFlow
    val movies by viewModel.movies.collectAsState(emptyList())
    val upcoming by viewModel.upcoming.collectAsState(emptyList())
    val action by viewModel.action.collectAsState(emptyList())
    val scifi by viewModel.scifi.collectAsState(emptyList())
    val animation by viewModel.animation.collectAsState(emptyList())
    val mystery by viewModel.mystery.collectAsState(emptyList())
    val drama by viewModel.drama.collectAsState(emptyList())
    val thriller by viewModel.thriller.collectAsState(emptyList())

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        FeaturedMoviesSection(
            data = movies
        )
        ComingSoonSection(
            data = upcoming,
            modifier = Modifier.padding(horizontal = 18.dp)
        )
        HorizontalSection(
            data = action,
            name = "Action Movies"
        )
        HorizontalSection(
            data = scifi,
            name = "Sci-Fi Movies"
        )
        HorizontalSection(
            data = animation,
            name = "Animation"
        )
        HorizontalSection(
            data = mystery,
            name = "Mystery"
        )
        HorizontalSection(
            data = drama,
            name = "Drama"
        )
        HorizontalSection(
            data = thriller,
            name = "Thriller"
        )
    }
}
