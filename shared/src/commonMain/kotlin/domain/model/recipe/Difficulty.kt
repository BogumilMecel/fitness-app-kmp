package domain.model.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
enum class Difficulty {
    @SerialName("very_low")
    VERY_LOW,

    @SerialName("low")
    LOW,

    @SerialName("average")
    AVERAGE,

    @SerialName("high")
    HIGH,

    @SerialName("very_high")
    VERY_HIGH,
}