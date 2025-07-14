package presentation.product

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import models.MeasurementUnit
import models.Product
import models.ProductDiaryEntry
import presentation.base.BaseModel

class ProductScreenModel(
    private val entryMode: EntryMode,
) : BaseModel() {

    val state = MutableStateFlow(
        ProductState(
            productName = entryMode.productName,
            productMeasurementUnit = entryMode.productMeasurementUnit,
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

    @Serializable
    sealed class EntryMode(
        val productName: String,
        val productMeasurementUnit: MeasurementUnit,
    ) {
        @Serializable
        data class Add(
            val product: Product,
        ) : EntryMode(
            productName = product.name,
            productMeasurementUnit = product.measurementUnit,
        )

        @Serializable
        data class Edit(
            val productDiaryEntry: ProductDiaryEntry,
        ) : EntryMode(
            productName = productDiaryEntry.productName,
            productMeasurementUnit = productDiaryEntry.productMeasurementUnit,
        )
    }
} 