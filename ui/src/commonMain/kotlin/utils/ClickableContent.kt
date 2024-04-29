package utils

sealed class ClickableContent {
    data class Icon(
        val icon: components.Icon,
        val onClick: () -> Unit
    )
}