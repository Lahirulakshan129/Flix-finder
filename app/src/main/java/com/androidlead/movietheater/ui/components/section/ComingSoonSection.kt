package com.androidlead.movietheater.ui.components.section

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidlead.movietheater.ui.components.MovieThumbnail
import com.androidlead.movietheater.ui.components.SectionHeader
import androidx.compose.ui.platform.LocalContext
import com.androidlead.movietheater.data.Movie


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComingSoonSection(
    modifier: Modifier = Modifier,
    data: List<Movie>
) {

    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(
            text = "Coming Soon"
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            maxItemsInEachRow = 2
        ) {
            data.onEach { thumbnail ->
                MovieThumbnail(
                    img = thumbnail.img,
                    modifier = Modifier.weight(weight = 1f),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(thumbnail.trailerUrl))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }

}