package domain.model

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_gram
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_milliliter
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
        GRAMS -> stringResource(Res.string.measurement_unit_gram)
        MILLILITERS -> stringResource(Res.string.measurement_unit_milliliter)
    }
}