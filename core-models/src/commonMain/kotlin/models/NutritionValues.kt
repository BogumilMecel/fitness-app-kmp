package models

import androidx.room.TypeConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import round

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
        carbohydrates = (carbohydrates * number).round(2),
        protein = (protein * number).round(2),
        fat = (fat * number).round(2)
    )
}

fun NutritionValues.forWeight(weight: Int): NutritionValues = multiplyBy(number = weight / 100.0)

fun NutritionValues.calculatePercentages(): Map<NutritionValue, Float>{
    val caloriesFromCarbohydrates = carbohydrates * 4.0
    val caloriesFromProtein = protein * 4.0
    val caloriesFromFat = fat * 9.0

    val sum = caloriesFromCarbohydrates + caloriesFromProtein + caloriesFromFat

    val carbohydratesValue = (((caloriesFromCarbohydrates / sum * 100.0)).round(1)).toFloat()
    val proteinValue = (((caloriesFromProtein / sum * 100.0)).round(1)).toFloat()
    val fatValue = (((caloriesFromFat / sum * 100.0)).round(1)).toFloat()

    return mapOf(
        NutritionValue.CARBOHYDRATES to carbohydratesValue,
        NutritionValue.PROTEIN to proteinValue,
        NutritionValue.FAT to fatValue
    )
}