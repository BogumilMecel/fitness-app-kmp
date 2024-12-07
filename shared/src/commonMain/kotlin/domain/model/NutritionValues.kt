package domain.model

import androidx.room.TypeConverter
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.kcal
import domain.services.ResourcesService
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import string.SPACE

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
) {
    suspend fun getCaloriesFormatted(resourcesService: ResourcesService) =
        calories.toString() + String.SPACE + resourcesService.getString(Res.string.kcal)
}

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