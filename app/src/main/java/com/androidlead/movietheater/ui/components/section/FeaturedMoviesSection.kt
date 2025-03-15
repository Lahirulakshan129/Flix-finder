package com.androidlead.movietheater.ui.components.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.androidlead.movietheater.R
import com.androidlead.movietheater.data.Movie
import com.androidlead.movietheater.ui.components.SectionHeader

@Composable
fun FeaturedMoviesSection(
    modifier: Modifier = Modifier,
    data: List<Movie>
) {
    Column(
        modifier = modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SectionHeader(
            text = "Featured Movies"
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = data,
                key = { it.id }
            ) { movie ->
                FeaturedMovie(
                    item = movie
                )
            }
        }
    }
}
@Composable
private fun FeaturedMovie(
    modifier: Modifier = Modifier,
    item: Movie
) {
    val context = LocalContext.current
    var showPopup by remember { mutableStateOf(false) } // Correct usage of mutableStateOf
    var selectedMovie by remember { mutableStateOf<Movie?>(null) } // Initialize as nullable

    Column(
        modifier = modifier.width(224.dp)
    ) {
        // Load image from URL using Coil
        val imageUrl = item.posterPath // posterPath is the full URL (e.g., "https://image.tmdb.org/t/p/w500/abc123.jpg")
        if (!imageUrl.isNullOrEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(324.dp)
                    .clickable {
                        selectedMovie = item // Set the selected movie
                        showPopup = true // Show the popup
                    }
            )
        } else {
            // Show a placeholder if the image URL is null
            Image(
                painter = painterResource(id = R.drawable.placeholder), // Add a placeholder drawable
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(324.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Display movie title
        Text(
            text = item.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Show the popup if a movie is selected
        if (showPopup && selectedMovie != null) {
            MoviePopup(
                movie = selectedMovie!!, // Safe to use !! here because of the null check above
                onDismiss = { showPopup = false }
            )
        }
    }
}
