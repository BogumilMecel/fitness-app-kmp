package utils

import androidx.compose.ui.graphics.vector.ImageVector

sealed class ClickableContent {
    data class Icon(
        val icon: ImageVector,
        val onClick: () -> Unit
    )
}