package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewProductRequest(
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
) 