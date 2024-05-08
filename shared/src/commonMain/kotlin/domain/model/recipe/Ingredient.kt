package domain.model.recipe

import domain.model.MeasurementUnit
import domain.model.NutritionValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    @SerialName("weight")
    val weight: Int,

    @SerialName("product_name")
    val productName: String,

    @SerialName("nutrition_values")
    val nutritionValues: NutritionValues,

    @SerialName("product_id")
    val productId: String,

    @SerialName("measurement_unit")
    val measurementUnit: MeasurementUnit,
)