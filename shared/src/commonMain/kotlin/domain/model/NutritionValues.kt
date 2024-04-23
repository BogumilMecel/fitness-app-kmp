package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionValues(
    @SerialName("calories")
    val calories: Int = 0,

    @SerialName("carbohydrates")
    val carbohydrates: Double = 0.0,

    @SerialName("protein")
    val protein: Double = 0.0,

    @SerialName("fat")
    val fat: Double = 0.0
)

fun NutritionValues.multiplyBy(number: Double): NutritionValues {
    return NutritionValues(
        calories = (calories.toDouble() * number).toInt(),
        carbohydrates = carbohydrates * number,
        protein = protein * number,
        fat = fat * number
    )
}