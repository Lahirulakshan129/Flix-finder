package com.androidlead.movietheater.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MovieThumbnail(
    modifier: Modifier = Modifier,
    @DrawableRes img: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(240.dp)
            .clickable { onClick() }
    )
}