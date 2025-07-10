package models

import kotlinx.serialization.Serializable

@Serializable
data class DiaryEntriesResponse(
    val productDiaryEntries: List<ProductDiaryEntry> = emptyList(),
    val recipeDiaryEntries: List<RecipeDiaryEntry> = emptyList()
)