package presentation.product

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import models.MealName
import models.MeasurementUnit
import presentation.base.BaseModel
import repository.DiaryRepository

class ProductScreenModel(
    private val productId: String,
    private val date: LocalDate,
    private val mealName: MealName,
    private val diaryRepository: DiaryRepository,
) : BaseModel() {

    val state = MutableStateFlow(
        ProductState(
            productName = "",
            productMeasurementUnit = MeasurementUnit.GRAMS,
            headerPrimaryText = "",
            headerSecondaryText = "",
        )
    )

    init {
        requestProduct()
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.WeightChanged -> {
                // TODO: handle
            }

            is ProductEvent.OnSaveClicked -> {
                // TODO: handle save logic
                onBackPressed()
            }

            is ProductEvent.OnBackPressed -> {
                onBackPressed()
            }
        }
    }

    private fun requestProduct() {
        viewModelScope.launch {
            runCatching {
                diaryRepository.getProduct(productId = productId)
            }.onSuccess {
                it?.let { product ->

                } ?: onBackPressed()
            }
        }
    }
} 