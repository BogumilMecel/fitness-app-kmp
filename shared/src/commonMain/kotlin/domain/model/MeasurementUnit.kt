package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
enum class MeasurementUnit {
    @SerialName("grams")
    GRAMS,

    @SerialName("milliliters")
    MILLILITERS;
}