package presentation.new_product

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import models.MeasurementUnit
import models.NewProductRequest
import models.NutritionValues
import models.NutritionValuesIn
import presentation.base.BaseModel
import repository.DiaryRepository
import utils.handle
import utils.string.normalizeDecimalSeparator

class NewProductScreenModel(
    private val diaryRepository: DiaryRepository,
) : BaseModel() {

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
            NewProductEvent.CreateProductClicked -> {
                onCreateProductClicked()
            }
        }
    }

    private fun onCreateProductClicked() {
        val currentState = state.value
        
        if (currentState.productName.isBlank()) {
            return
        }
        
        viewModelScope.launch {
            val calories = currentState.calories.toIntOrNull() ?: 0
            val carbohydrates = currentState.carbohydrates.toDoubleOrNull() ?: 0.0
            val protein = currentState.protein.toDoubleOrNull() ?: 0.0
            val fat = currentState.fat.toDoubleOrNull() ?: 0.0
            val containerWeight = currentState.containerWeight.toIntOrNull()
            
            val nutritionValuesIn = when (currentState.selectedNutritionValuesChoice) {
                NutritionValuesChoice.IN_100_GRAMS -> NutritionValuesIn.HUNDRED_GRAMS
                NutritionValuesChoice.IN_CONTAINER -> NutritionValuesIn.CONTAINER
                NutritionValuesChoice.IN_AVERAGE -> NutritionValuesIn.AVERAGE
            }
            
            val newProductRequest = NewProductRequest(
                name = currentState.productName,
                containerWeight = containerWeight,
                nutritionValuesIn = nutritionValuesIn,
                measurementUnit = MeasurementUnit.GRAMS,
                nutritionValues = NutritionValues(
                    calories = calories,
                    carbohydrates = carbohydrates,
                    protein = protein,
                    fat = fat
                ),
                barcode = currentState.barcode.takeIf { it.isNotBlank() }
            )
            
            diaryRepository.saveNewProduct(newProductRequest).handle(
                onSuccess = { createdProduct ->
                    onBackPressed()
                },
                onError = { error ->
                    
                }
            )
        }
    }
} 