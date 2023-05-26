package com.danielefavaro.githubapiplayground.feature.repodetail.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.danielefavaro.githubapiplayground.core.ui.MyApplicationTheme
import com.danielefavaro.githubapiplayground.core.ui.defaultMargin
import com.danielefavaro.githubapiplayground.feature.repodetail.R

@Composable
internal fun Body(watchers: Int, stars: Int) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.padding(defaultMargin)) {
                Text(
                    text = stringResource(
                        id = R.string.watchers_count,
                        watchers
                    )
                )
            }
            Box(modifier = Modifier.padding(defaultMargin)) {
                Text(
                    text = stringResource(
                        id = R.string.stars_count,
                        stars
                    )
                )
            }
        }

        // TODO more details here
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        Body(watchers = 23, stars = 123)
    }
}
