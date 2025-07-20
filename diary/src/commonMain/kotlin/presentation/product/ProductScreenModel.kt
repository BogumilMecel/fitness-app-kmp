package presentation.product

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate
import models.MealName
import models.MeasurementUnit
import presentation.base.BaseModel

class ProductScreenModel(
    private val productId: String,
    private val date: LocalDate,
    private val mealName: MealName,
) : BaseModel() {

    val state = MutableStateFlow(
        ProductState(
            productName = "",
            productMeasurementUnit = MeasurementUnit.GRAMS,
            headerPrimaryText = "",
            headerSecondaryText = "",
        )
    )

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.WeightChanged -> {
                // TODO: handle
            }

            is ProductEvent.OnSaveClicked -> {
                // TODO: handle on click
            }
        }
    }
} 