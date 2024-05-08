package domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

interface DiaryItem {
    val id: String
    val nutritionValues: NutritionValues
    val userId: String
    val date: LocalDate
    val mealName: MealName
    val creationDateTime: LocalDateTime
    val changeDateTime: LocalDateTime
}