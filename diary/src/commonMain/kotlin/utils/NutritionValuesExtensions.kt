package utils

import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.calories_short_name
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_gram
import com.gmail.bogumilmecel2.diary.composeResources.measurement_unit_milliliter
import models.MeasurementUnit
import domain.model.NutritionValues
import domain.services.ResourcesService
import utils.string.SPACE

suspend fun NutritionValues.getCaloriesFormatted(resourcesService: ResourcesService) =
    calories.toString() + String.SPACE + resourcesService.getString(Res.string.calories_short_name)

suspend fun MeasurementUnit.formatWithValue(
    resourcesService: ResourcesService,
    value: Int,
) = "$value" + String.SPACE + when(this) {
    MeasurementUnit.GRAMS -> resourcesService.getString(Res.string.measurement_unit_gram)
    MeasurementUnit.MILLILITERS -> resourcesService.getString(Res.string.measurement_unit_milliliter)
}
