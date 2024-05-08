package domain.model.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
enum class NutritionValuesIn {
    @SerialName("hundred_grams")
    HUNDRED_GRAMS,

    @SerialName("container")
    CONTAINER,

    @SerialName("average")
    AVERAGE;
}