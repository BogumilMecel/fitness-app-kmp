package domain.model

import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Ingredient(
    val weight: Int,
    val productName: String,
    val nutritionValues: NutritionValues,
    val productId: String,
    val measurementUnit: MeasurementUnit,
)

class IngredientsConverter {

    @TypeConverter
    fun fromIngredientsList(value: List<Ingredient>) = Json.encodeToString(value)

    @TypeConverter
    fun toIngredientsList(value: String): List<Ingredient> = Json.decodeFromString(value)
}