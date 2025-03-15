package com.androidlead.movietheater.ui.components.section

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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

    Column(
        modifier = modifier.width(224.dp)
    ) {
        Image(
            painter = painterResource(item.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(324.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.trailerUrl))
                    context.startActivity(intent)
                }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = item.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

    }
}
