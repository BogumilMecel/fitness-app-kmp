package models

import kotlinx.serialization.Serializable

@Serializable
enum class DiaryEntryType {
    PRODUCT, RECIPE
} 