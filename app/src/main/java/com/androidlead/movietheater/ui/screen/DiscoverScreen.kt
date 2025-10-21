package com.androidlead.movietheater.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import com.androidlead.movietheater.MovieViewModel
import com.androidlead.movietheater.ui.components.section.ComingSoonSection
import com.androidlead.movietheater.ui.components.section.FeaturedMoviesSection
import com.androidlead.movietheater.ui.components.section.HorizontalSection
import com.eygraber.compose.placeholder.material.placeholder
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.material.shimmer
import androidx.compose.foundation.horizontalScroll

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel
) {
    val scrollState = rememberScrollState()

    val movies by viewModel.movies.collectAsState(emptyList())
    val upcoming by viewModel.upcoming.collectAsState(emptyList())
    val action by viewModel.action.collectAsState(emptyList())
    val scifi by viewModel.scifi.collectAsState(emptyList())
    val animation by viewModel.animation.collectAsState(emptyList())
    val mystery by viewModel.mystery.collectAsState(emptyList())
    val drama by viewModel.drama.collectAsState(emptyList())
    val thriller by viewModel.thriller.collectAsState(emptyList())
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Featured Movies
        if (isLoading) {
            LoadingBannerShimmer(height = 180.dp)
        } else if (movies.isNotEmpty()) {
            FeaturedMoviesSection(data = movies)
        } else {
            Text("No featured movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        // Upcoming Movies
        if (isLoading) {
            LoadingBannerShimmer(height = 150.dp)
        } else if (upcoming.isNotEmpty()) {
            ComingSoonSection(data = upcoming, modifier = Modifier.padding(horizontal = 18.dp))
        } else {
            Text("No upcoming movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        // Action
        if (isLoading) {
            LoadingHorizontalShimmerSection(items = 5)
        } else if (action.isNotEmpty()) {
            HorizontalSection(data = action, name = "Action Movies")
        } else {
            Text("No action movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        // Sci-Fi
        if (isLoading) {
            LoadingHorizontalShimmerSection(items = 5)
        } else if (scifi.isNotEmpty()) {
            HorizontalSection(data = scifi, name = "Sci-Fi Movies")
        } else {
            Text("No sci-fi movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        // Animation
        if (isLoading) {
            LoadingHorizontalShimmerSection(items = 5)
        } else if (animation.isNotEmpty()) {
            HorizontalSection(data = animation, name = "Animation")
        } else {
            Text("No animation movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        // Mystery
        if (isLoading) {
            LoadingHorizontalShimmerSection(items = 5)
        } else if (mystery.isNotEmpty()) {
            HorizontalSection(data = mystery, name = "Mystery")
        } else {
            Text("No mystery movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        // Drama
        if (isLoading) {
            LoadingHorizontalShimmerSection(items = 5)
        } else if (drama.isNotEmpty()) {
            HorizontalSection(data = drama, name = "Drama")
        } else {
            Text("No drama movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }

        // Thriller
        if (isLoading) {
            LoadingHorizontalShimmerSection(items = 5)
        } else if (thriller.isNotEmpty()) {
            HorizontalSection(data = thriller, name = "Thriller")
        } else {
            Text("No thriller movies found", modifier = Modifier.padding(horizontal = 18.dp))
        }
    }
}

@Composable
fun LoadingBannerShimmer(height: Dp) {
    Box(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(12.dp))
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer(),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
    )
}

@Composable
fun LoadingHorizontalShimmerSection(items: Int) {
    Row(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(items) {
            Box(
                modifier = Modifier
                    .size(width = 120.dp, height = 180.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    )
            )
        }
    }
}
