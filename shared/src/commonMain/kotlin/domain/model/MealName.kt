package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
enum class MealName {
    @SerialName("breakfast")
    BREAKFAST,

    @SerialName("lunch")
    LUNCH,

    @SerialName("dinner")
    DINNER,

    @SerialName("supper")
    SUPPER;
}