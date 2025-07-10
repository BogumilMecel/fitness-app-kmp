package domain.model

import androidx.room.TypeConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class NutritionValues(
    @SerialName("calories")
    val calories: Int = 0,

    @SerialName("carbohydrates")
    val carbohydrates: Double = 0.0,

    @SerialName("protein")
    val protein: Double = 0.0,

    @SerialName("fat")
    val fat: Double = 0.0,
)

class NutritionValuesTypeConverter {

    @TypeConverter
    fun fromNutritionValues(value: NutritionValues): String = Json.encodeToString(value)

    @TypeConverter
    fun toNutritionValues(value: String): NutritionValues = Json.decodeFromString(value)
}

fun NutritionValues.multiplyBy(number: Double): NutritionValues {
    return NutritionValues(
        calories = (calories.toDouble() * number).toInt(),
        carbohydrates = carbohydrates * number,
        protein = protein * number,
        fat = fat * number
    )
} 