package com.danielefavaro.githubapiplayground.feature.repodetail.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.danielefavaro.githubapiplayground.core.ui.MyApplicationTheme
import com.danielefavaro.githubapiplayground.core.ui.defaultMargin
import com.danielefavaro.githubapiplayground.feature.repodetail.R

@Composable
internal fun Footer(ctaUrl: String) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = defaultMargin)
            .padding(bottom = defaultMargin),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            uriHandler.openUri(ctaUrl)
        }) {
            Text(
                text = stringResource(id = R.string.visit_repo_cta_label)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        Footer(ctaUrl = "www.github.com")
    }
}
