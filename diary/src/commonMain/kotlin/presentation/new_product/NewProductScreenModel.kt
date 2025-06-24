package presentation.new_product

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import presentation.base.BaseModel
import string.normalizeDecimalSeparator

class NewProductScreenModel : BaseModel() {

    val state = MutableStateFlow(
        value = NewProductState()
    )

    fun onEvent(event: NewProductEvent) {
        when (event) {
            NewProductEvent.BackPressed -> {
                onBackPressed()
            }
            is NewProductEvent.ProductNameChanged -> {
                state.update { it.copy(productName = event.value) }
            }
            is NewProductEvent.BarcodeChanged -> {
                state.update { it.copy(barcode = event.value) }
            }
            is NewProductEvent.ContainerWeightChanged -> {
                state.update { it.copy(containerWeight = event.value.normalizeDecimalSeparator()) }
            }
            is NewProductEvent.CaloriesChanged -> {
                state.update { it.copy(calories = event.value.normalizeDecimalSeparator()) }
            }
            is NewProductEvent.CarbohydratesChanged -> {
                state.update { it.copy(carbohydrates = event.value.normalizeDecimalSeparator()) }
            }
            is NewProductEvent.ProteinChanged -> {
                state.update { it.copy(protein = event.value.normalizeDecimalSeparator()) }
            }
            is NewProductEvent.FatChanged -> {
                state.update { it.copy(fat = event.value.normalizeDecimalSeparator()) }
            }
            is NewProductEvent.NutritionValuesChoiceChanged -> {
                state.update { it.copy(selectedNutritionValuesChoice = event.choice) }
            }
        }
    }
} 