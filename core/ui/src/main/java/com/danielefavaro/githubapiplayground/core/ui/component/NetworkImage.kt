package com.danielefavaro.githubapiplayground.core.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    imageUrl: String,
    contentDescription: String,
    imageWidth: Dp
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = contentDescription,
        modifier = Modifier.width(imageWidth),
        contentScale = ContentScale.FillWidth
    )
}
