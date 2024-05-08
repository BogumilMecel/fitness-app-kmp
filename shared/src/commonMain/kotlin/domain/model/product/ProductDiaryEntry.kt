package domain.model.product

import domain.model.DiaryItem
import domain.model.MealName
import domain.model.MeasurementUnit
import domain.model.NutritionValues
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

    // TODO: remove when deleting is handled with device id
    @SerialName("deleted")
    val deleted: Boolean,

    @SerialName("creation_date")
    override val creationDateTime: LocalDateTime,

    @SerialName("change_date")
    override val changeDateTime: LocalDateTime,
) : DiaryItem