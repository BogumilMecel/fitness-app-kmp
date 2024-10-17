package domain.model

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDiaryEntry(
    @SerialName("id")
    override val id: String,

    @SerialName("nutrition_values")
    override val nutritionValues: NutritionValues,

    @SerialName("date")
    override val date: LocalDate,

    @SerialName("user_id")
    override val userId: String,

    @SerialName("meal_name")
    override val mealName: MealName,

    @SerialName("product_measurement_unit")
    val productMeasurementUnit: MeasurementUnit,

    @SerialName("product_name")
    val productName: String,

    @SerialName("product_id")
    val productId: String,

    @SerialName("weight")
    val weight: Int,

    @SerialName("deleted")
    val deleted: Boolean = false,

    @SerialName("creation_date")
    override val creationDateTime: LocalDateTime,

    @SerialName("change_date")
    override val changeDateTime: LocalDateTime
) : DiaryItem {
    @Composable
    override fun getDisplayValue() = "$weight ${productMeasurementUnit.getName()}."

    override fun getDiaryEntryType() = DiaryEntryType.PRODUCT
}