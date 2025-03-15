package com.androidlead.movietheater.ui.components.section

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.androidlead.movietheater.data.Movie
import com.androidlead.movietheater.ui.components.MovieThumbnail
import com.androidlead.movietheater.ui.components.SectionHeader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalSection(
    modifier: Modifier = Modifier,
    data: List<Movie>,
    name: String
) {
    val pagerState = rememberPagerState(pageCount = { data.size })
    LocalContext.current
    var showPopup by remember { mutableStateOf(false) } // Correct usage of mutableStateOf
    var selectedMovie by remember { mutableStateOf<Movie?>(null) } // Initialize as nullable

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(
            text = name,
            modifier = Modifier.padding(horizontal = 18.dp)
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 18.dp),
            pageSpacing = 18.dp,
            pageSize = TwoPagesPerViewport
        ) { pageIndex ->
            val movie = data[pageIndex]
            MovieThumbnail(
                img = movie.posterPath ?: "", // Pass the nullable posterPath
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    selectedMovie = movie // Set the selected movie
                    showPopup = true // Show the popup
                }
            )
        }
    }

    // Show the popup if a movie is selected
    if (showPopup && selectedMovie != null) {
        MoviePopup(
            movie = selectedMovie!!, // Safe to use !! here because of the null check above
            onDismiss = { showPopup = false }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private val TwoPagesPerViewport = object : PageSize {
    override fun Density.calculateMainAxisPageSize(availableSpace: Int, pageSpacing: Int): Int =
        (availableSpace - pageSpacing) / 2
}
@Composable
fun MoviePopup(
    movie: Movie,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false) // Make dialog full width
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth() // Make the popup full width
                .fillMaxHeight(0.9f) // Set height to 90% of the screen
                .padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()), // Enable scrolling
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Movie Title
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                // Movie Image (Larger)
                val imageUrl = movie.posterPath
                if (imageUrl != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp) // Adjust height as needed
                    )
                }

                // Movie Description
                Text(
                    text = movie.overview ?: "No description available",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Movie Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Rating:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    // Display stars based on the rating
                    for (i in 1..5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = if (i <= movie.rating / 2) Color.Yellow else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Watch Trailer Button
                if (movie.trailerUrl != null) {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.trailerUrl))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Watch Trailer")
                    }
                }else {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            //
                        },
                    ) {
                        Text(
                            text = "No trailer available",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
                // Close Button
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = Color.White
                    )
                ) {
                    Text("Close")
                }
            }
        }
    }
}