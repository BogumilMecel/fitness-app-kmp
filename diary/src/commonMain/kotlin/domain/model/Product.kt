package domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("container_weight")
    val containerWeight: Int? = null,

    @SerialName("nutrition_values_in")
    val nutritionValuesIn: NutritionValuesIn,

    @SerialName("measurement_unit")
    val measurementUnit: MeasurementUnit,

    @SerialName("nutrition_values")
    val nutritionValues: NutritionValues,

    @SerialName("barcode")
    val barcode: String? = null,

    @SerialName("username")
    val username: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("creation_date")
    val creationDateTime: LocalDateTime
)