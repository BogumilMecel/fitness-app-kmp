package domain.model

import kotlinx.serialization.SerialName

enum class NutritionValuesIn {
    @SerialName("hundred_grams")
    HUNDRED_GRAMS,

    @SerialName("hundred_milliliters")
    HUNDRED_MILLILITERS,

    @SerialName("container")
    CONTAINER,

    @SerialName("average")
    AVERAGE;
}