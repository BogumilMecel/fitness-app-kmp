import domain.model.NutritionValues
import models.DiaryItem


data class Meal(
    val diaryEntries: List<DiaryItem>,
    val nutritionValues: NutritionValues
) {
    companion object {
        fun createEmpty() = Meal(
            diaryEntries = emptyList(),
            nutritionValues = NutritionValues(
                calories = 0,
                carbohydrates = 0.0,
                protein = 0.0,
                fat = 0.0
            )
        )
    }
}