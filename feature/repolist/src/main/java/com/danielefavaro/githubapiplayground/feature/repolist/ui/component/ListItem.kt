package com.danielefavaro.githubapiplayground.feature.repolist.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.danielefavaro.githubapiplayground.core.ui.MyApplicationTheme
import com.danielefavaro.githubapiplayground.core.ui.defaultMargin
import com.danielefavaro.githubapiplayground.feature.repolist.R

@Composable
fun ListItem(
    title: String,
    starsCount: Int,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(defaultMargin),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Row {
            Text(text = "$starsCount")
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(id = R.string.repo_stars_count)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        ListItem(title = "title", starsCount = 3, onClick = {})
    }
}
