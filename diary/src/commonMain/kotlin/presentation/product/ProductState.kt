package presentation.product

import models.MeasurementUnit
import utils.string.EMPTY

data class ProductState(
    val weight: String = String.EMPTY,
    val productName: String,
    val productMeasurementUnit: MeasurementUnit,
    val headerPrimaryText: String,
    val headerSecondaryText: String,
)