package models

import androidx.compose.runtime.Composable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MeasurementUnit {
    @SerialName("grams")
    GRAMS,

    @SerialName("milliliters")
    MILLILITERS;

    @Composable
    fun getName() = when(this) {
        GRAMS -> "g"
        MILLILITERS -> "ml"
    }
} 