package com.danielefavaro.githubapiplayground.core.ui.component.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ToolbarActionModel(
    open val icon: ImageVector,
    open val caption: String,
    open val onClick: () -> Unit
) {
    data class Back(
        override val caption: String,
        override val onClick: () -> Unit
    ) : ToolbarActionModel(
        icon = Icons.Default.ArrowBack,
        caption = caption,
        onClick = onClick
    )
}
