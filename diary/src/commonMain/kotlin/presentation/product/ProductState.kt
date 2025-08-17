package presentation.product

import kotlinx.datetime.LocalDate
import models.MealName
import models.MeasurementUnit
import models.NutritionValue
import models.NutritionValues

data class ProductState(
    val weight: String,
    val productName: String,
    val productMeasurementUnit: MeasurementUnit,
    val mealName: MealName,
    val date: LocalDate,
    val nutritionValuesPercentages: Map<NutritionValue, Float>,
    val currentNutritionValues: NutritionValues,
)