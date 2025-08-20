package presentation.product

import androidx.lifecycle.viewModelScope
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.product_measurement_unit
import domain.model.SelectorItem
import domain.services.ResourcesService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import models.MealName
import models.MeasurementUnit
import models.NewProductDiaryEntryRequest
import models.NutritionValues
import models.Product
import models.calculatePercentages
import models.forWeight
import navigation.presentation.Route
import presentation.base.BaseModel
import repository.DiaryRepository

class ProductScreenModel(
    private val product: Product,
    private val date: LocalDate,
    private val mealName: MealName,
    private val weight: Int,
    private val diaryRepository: DiaryRepository,
    private val resourcesService: ResourcesService,
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

    init {
        viewModelScope.launch {
            navigatorService.backResult.receiveAsFlow().collect { backResult ->
                when (backResult) {
                    is SelectorItem -> {
                        state.update { state ->
                            state.copy(
                                productMeasurementUnit = MeasurementUnit.entries.firstOrNull {
                                    it.toString() == backResult.id
                                } ?: state.productMeasurementUnit
                            )
                        }
                    }
                }
            }
        }
    }

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

            is ProductEvent.OnMeasurementUnitClicked -> {
                viewModelScope.launch {
                    navigateTo(
                        route = Route.Selector(
                            title = resourcesService.getString(Res.string.product_measurement_unit),
                            items = MeasurementUnit.entries.map {
                                SelectorItem(
                                    label = resourcesService.getString(it.longDisplayNameResource),
                                    id = it.toString(),
                                )
                            }
                        )
                    )
                }
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