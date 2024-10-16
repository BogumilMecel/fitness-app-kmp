package domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class DiaryEntryType {
    PRODUCT, RECIPE
}