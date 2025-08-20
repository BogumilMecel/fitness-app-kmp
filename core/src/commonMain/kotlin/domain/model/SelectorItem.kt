package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SelectorItem(
    val label: String,
    val id: String,
)