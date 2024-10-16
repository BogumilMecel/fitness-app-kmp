package domain.model

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

interface DiaryItem {
    val id: String?
    val nutritionValues: NutritionValues?
    val userId: String?
    val date: LocalDate?
    val meal: Meal?
    val creationDateTime: LocalDateTime?
    val changeDateTime: LocalDateTime?

    @Composable
    fun getDisplayValue(): String?

    fun getDiaryEntryType(): DiaryEntryType
}