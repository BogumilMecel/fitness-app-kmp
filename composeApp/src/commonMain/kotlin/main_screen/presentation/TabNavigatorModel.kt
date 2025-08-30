package main_screen.presentation

import androidx.lifecycle.viewModelScope
import domain.use_case.InitialDiaryDataUseCases
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import presentation.base.BaseModel

class TabNavigatorModel(
    private val initialDiaryDataUseCases: InitialDiaryDataUseCases,
) : BaseModel() {

    val tabNavigationEnabled = MutableStateFlow(false)

    init {
        requestInitialData()
    }

    private fun requestInitialData() {
        viewModelScope.launch {
            async { requestDiaryData() }.await()
            tabNavigationEnabled.value = true
        }
    }

    private suspend fun requestDiaryData() {
        coroutineScope {
            with(initialDiaryDataUseCases) {
                val productDiaryJob = async { getProductDiaryAndSaveOfflineUseCase() }
                val recipeDiaryJob = async { getRecipeDiaryAndSaveOfflineUseCase() }
                val productJob = async { getProductsAndSaveOfflineUseCase() }
                val recipeJob = async { getRecipesAndSaveOfflineUseCase() }
                awaitAll(productDiaryJob, recipeDiaryJob, productJob, recipeJob)
            }
        }
    }
}