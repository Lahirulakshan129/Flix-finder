package com.androidlead.movietheater.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
        if (movies.isNotEmpty()) {
            FeaturedMoviesSection(
                data = movies
            )
        } else {
            Text("No featured movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        if (upcoming.isNotEmpty()) {
            ComingSoonSection(
                data = upcoming,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        } else {
            Text("No upcoming movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        if (action.isNotEmpty()) {
            HorizontalSection(
                data = action,
                name = "Action Movies"
            )
        } else {
            Text("No action movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        if (scifi.isNotEmpty()) {
            HorizontalSection(
                data = scifi,
                name = "Sci-Fi Movies"
            )
        } else {
            Text("No sci-fi movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        if (animation.isNotEmpty()) {
            HorizontalSection(
                data = animation,
                name = "Animation"
            )
        } else {
            Text("No animation movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        if (mystery.isNotEmpty()) {
            HorizontalSection(
                data = mystery,
                name = "Mystery"
            )
        } else {
            Text("No mystery movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        if (drama.isNotEmpty()) {
            HorizontalSection(
                data = drama,
                name = "Drama"
            )
        } else {
            Text("No drama movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        if (thriller.isNotEmpty()) {
            HorizontalSection(
                data = thriller,
                name = "Thriller"
            )
        } else {
            Text("No thriller movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }
    }
}