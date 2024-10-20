package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val weight: Int,
    val productName: String,
    val nutritionValues: NutritionValues,
    val productId: String,
    val measurementUnit: MeasurementUnit,
)