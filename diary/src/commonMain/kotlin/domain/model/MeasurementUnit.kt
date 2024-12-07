package domain.model

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_gram
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_milliliter
import domain.services.ResourcesService
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import string.SPACE

@Serializable
enum class MeasurementUnit {
    @SerialName("grams")
    GRAMS,

    @SerialName("milliliters")
    MILLILITERS;

    @Composable
    fun getName() = stringResource(getResource())

    suspend fun formatWithValue(
        resourcesService: ResourcesService,
        value: Int,
    ) = "$value" + String.SPACE + resourcesService.getString(getResource())

    private fun getResource() = when(this) {
        GRAMS -> Res.string.measurement_unit_gram
        MILLILITERS -> Res.string.measurement_unit_milliliter
    }
}