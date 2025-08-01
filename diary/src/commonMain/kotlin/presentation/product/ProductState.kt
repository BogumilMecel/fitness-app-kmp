package presentation.product

import kotlinx.datetime.LocalDate
import models.MealName
import models.MeasurementUnit
import utils.string.EMPTY

data class ProductState(
    val weight: String = String.EMPTY,
    val productName: String = String.EMPTY,
    val productMeasurementUnit: MeasurementUnit = MeasurementUnit.GRAMS,
    val mealName: MealName,
    val date: LocalDate,
)