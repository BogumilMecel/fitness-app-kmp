package models

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.`diary-models`.composeResources.Res
import com.gmail.bogumilmecel2.`diary-models`.composeResources.measurement_unit_grams
import com.gmail.bogumilmecel2.`diary-models`.composeResources.measurement_unit_milliliters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

@Serializable
enum class MeasurementUnit {
    @SerialName("grams")
    GRAMS,

    @SerialName("milliliters")
    MILLILITERS;

    @Composable
    fun getName() = when (this) {
        GRAMS -> "g"
        MILLILITERS -> "ml"
    }

    val longDisplayName
        @Composable
        get() = when (this) {
            GRAMS -> stringResource(Res.string.measurement_unit_grams)
            MILLILITERS -> stringResource(Res.string.measurement_unit_milliliters)
        }
} 