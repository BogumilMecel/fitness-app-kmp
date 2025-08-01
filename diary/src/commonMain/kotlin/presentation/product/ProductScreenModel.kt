package presentation.product

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import models.MealName
import models.NewProductDiaryEntryRequest
import models.Product
import presentation.base.BaseModel
import repository.DiaryRepository

class ProductScreenModel(
    private val productId: String,
    private val date: LocalDate,
    private val mealName: MealName,
    private val diaryRepository: DiaryRepository,
) : BaseModel() {

    private var product: Product? = null

    val state = MutableStateFlow(
        value = ProductState(
            date = date,
            mealName = mealName,
        )
    )

    init {
        requestProduct()
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.WeightChanged -> {
                state.update {
                    it.copy(weight = event.value)
                }
            }

            is ProductEvent.OnQuickWeightButtonClicked -> {
                state.update {
                    it.copy(weight = event.value.toString())
                }
            }

            is ProductEvent.OnSaveClicked -> {
                saveProductDiaryEntry()
            }

            is ProductEvent.OnBackPressed -> {
                onBackPressed()
            }
        }
    }

    private fun requestProduct() {
        viewModelScope.launch {
            runCatchingWithSnackbarOnFailure {
                diaryRepository.getProduct(productId = productId)
            }.onSuccess { result ->
                result?.let { product ->
                    this@ProductScreenModel.product = product
                    state.update {
                        it.copy(
                            productName = product.name,
                            productMeasurementUnit = product.measurementUnit,
                        )
                    }
                } ?: onBackPressed()
            }.onFailure {
                onBackPressed()
            }
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
                        productId = product!!.id,
                        weight = productWeight,
                        date = date,
                        mealName = mealName,
                    )
                )
            }.onSuccess {
                // TODO: Display toast on success
                onBackPressed()
            }
        }
    }
} 