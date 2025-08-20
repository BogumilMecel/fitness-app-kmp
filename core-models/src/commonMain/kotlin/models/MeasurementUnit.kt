package models

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.`core-models`.composeResources.Res
import com.gmail.bogumilmecel2.`core-models`.composeResources.measurement_unit_grams
import com.gmail.bogumilmecel2.`core-models`.composeResources.measurement_unit_milliliters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

@Serializable
enum class MeasurementUnit {
    @SerialName("grams")
    GRAMS,

    @SerialName("milliliters")
    MILLILITERS;

    val shortDisplayName
        @Composable
        get() = when (this) {
            GRAMS -> "g"
            MILLILITERS -> "ml"
        }

    val longDisplayName
        @Composable
        get() = when (this) {
            GRAMS -> stringResource(Res.string.measurement_unit_grams)
            MILLILITERS -> stringResource(Res.string.measurement_unit_milliliters)
        }

    val longDisplayNameResource
        get() = when (this) {
            GRAMS -> Res.string.measurement_unit_grams
            MILLILITERS -> Res.string.measurement_unit_milliliters
        }
} 