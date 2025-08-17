package presentation.product

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import models.MealName
import models.NewProductDiaryEntryRequest
import models.NutritionValues
import models.Product
import models.calculatePercentages
import models.forWeight
import presentation.base.BaseModel
import repository.DiaryRepository

class ProductScreenModel(
    private val product: Product,
    private val date: LocalDate,
    private val mealName: MealName,
    private val weight: Int,
    private val diaryRepository: DiaryRepository,
) : BaseModel() {

    val state = MutableStateFlow(
        value = ProductState(
            productName = product.name,
            date = date,
            mealName = mealName,
            weight = weight.toString(),
            productMeasurementUnit = product.measurementUnit,
            nutritionValuesPercentages = product.nutritionValues.calculatePercentages(),
            currentNutritionValues = product.nutritionValues.forWeight(weight = weight),
        )
    )

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.WeightChanged -> {
                state.update {
                    it.copy(weight = event.value)
                }
                updateNutritionValues()
            }

            is ProductEvent.OnQuickWeightButtonClicked -> {
                state.update {
                    it.copy(weight = event.value.toString())
                }
                updateNutritionValues()
            }

            is ProductEvent.OnSaveClicked -> {
                saveProductDiaryEntry()
            }

            is ProductEvent.OnBackPressed -> {
                onBackPressed()
            }
        }
    }

    private fun updateNutritionValues() {
        state.update {
            it.copy(
                currentNutritionValues = it.weight.toIntOrNull()?.let { weight ->
                    product.nutritionValues.forWeight(weight = weight)
                } ?: it.currentNutritionValues
            )
        }
    }

    private fun saveProductDiaryEntry() {
        viewModelScope.launch {
            runCatchingWithSnackbarOnFailure {
                val productWeight = state.value.weight.toIntOrNull()

                // TODO: Handle invalid weight
                if (productWeight == null) throw Exception()

                diaryRepository.insertProductDiaryEntry(
                    newProductDiaryEntryRequest = NewProductDiaryEntryRequest(
                        productId = product.id,
                        weight = productWeight,
                        date = date,
                        mealName = mealName,
                        nutritionValues = NutritionValues()
                    )
                )
            }.onSuccess {
                // TODO: Display toast on success
                onBackPressed()
            }
        }
    }
} 