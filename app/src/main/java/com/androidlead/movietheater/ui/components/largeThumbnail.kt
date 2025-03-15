package com.androidlead.movietheater.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.androidlead.movietheater.R

@Composable
fun largeThumbnail(
    img: String?, // Nullable image URL
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val imagePainter = if (img != null) {
        rememberAsyncImagePainter(model = img)
    } else {
        painterResource(id = R.drawable.placeholder) // Add a placeholder drawable
    }

    Image(
        painter = imagePainter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(400.dp)
            .clip(RoundedCornerShape(8.dp))

    )
}