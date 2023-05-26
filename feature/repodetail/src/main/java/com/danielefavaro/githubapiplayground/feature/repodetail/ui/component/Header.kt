package com.danielefavaro.githubapiplayground.feature.repodetail.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.danielefavaro.githubapiplayground.core.ui.MyApplicationTheme
import com.danielefavaro.githubapiplayground.core.ui.component.NetworkImage
import com.danielefavaro.githubapiplayground.core.ui.defaultMargin
import com.danielefavaro.githubapiplayground.core.ui.defaultMarginSmall
import com.danielefavaro.githubapiplayground.feature.repodetail.R
import com.danielefavaro.githubapiplayground.feature.repodetail.ui.avatarImageSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Header(
    avatarUrl: String?,
    ownerName: String,
    isPrivate: Boolean,
    isArchived: Boolean
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier
                .padding(horizontal = defaultMargin)
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(defaultMarginSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            avatarUrl?.let {
                NetworkImage(
                    imageUrl = avatarUrl,
                    contentDescription = stringResource(id = R.string.avatar_image_caption),
                    imageWidth = avatarImageSize
                )
            }
            Text(
                text = stringResource(id = R.string.owned_by, ownerName)
            )
        }
        Row(
            Modifier.padding(end = defaultMargin),
            horizontalArrangement = Arrangement.spacedBy(defaultMarginSmall)
        ) {
            Badge {
                Text(
                    text = if (isPrivate) {
                        stringResource(id = R.string.private_repo_label)
                    } else {
                        stringResource(id = R.string.public_repo_label)
                    }
                )
            }
            if (isArchived) {
                Badge {
                    Text(
                        text = stringResource(id = R.string.archived_repo_label)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        Header(
            avatarUrl = null,
            ownerName = "Owner name",
            isPrivate = true,
            isArchived = true
        )
    }
}
