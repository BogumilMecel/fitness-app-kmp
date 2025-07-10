package models

import kotlinx.serialization.Serializable

@Serializable
data class DeleteDiaryEntryRequest(
    val diaryEntryId: String,
    val diaryEntryType: DiaryEntryType
)