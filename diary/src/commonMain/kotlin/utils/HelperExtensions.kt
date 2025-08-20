package utils

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.calories_short_name
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_gram
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_milliliter
import models.MeasurementUnit
import models.NutritionValues
import org.jetbrains.compose.resources.stringResource
import utils.string.SPACE

@Composable
fun MeasurementUnit.formatWithValue(value: Int) = "$value" + String.SPACE + when(this) {
    MeasurementUnit.GRAMS -> stringResource(resource = Res.string.measurement_unit_gram)
    MeasurementUnit.MILLILITERS -> stringResource(resource = Res.string.measurement_unit_milliliter)
}

@Composable
fun MeasurementUnit.formatWithValue(value: Double) = "$value" + String.SPACE + when(this) {
    MeasurementUnit.GRAMS -> stringResource(resource = Res.string.measurement_unit_gram)
    MeasurementUnit.MILLILITERS -> stringResource(resource = Res.string.measurement_unit_milliliter)
}

@Composable
fun NutritionValues.getCaloriesFormatted() =
    "$calories ${String.SPACE} ${stringResource(resource = Res.string.calories_short_name)}"