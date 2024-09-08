package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionValues(
    @SerialName("calories")
    val calories: Int,

    @SerialName("carbohydrates")
    val carbohydrates: Double,

    @SerialName("protein")
    val protein: Double,

    @SerialName("fat")
    val fat: Double,
)

fun NutritionValues.multiplyBy(number: Double): NutritionValues {
    return NutritionValues(
        calories = (calories.toDouble() * number).toInt(),
        carbohydrates = carbohydrates * number,
        protein = protein * number,
        fat = fat * number
    )
}