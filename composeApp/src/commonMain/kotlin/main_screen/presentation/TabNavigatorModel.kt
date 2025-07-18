package main_screen.presentation

import androidx.lifecycle.viewModelScope
import domain.repository.MainRepository
import domain.use_case.InitialDiaryDataUseCases
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import presentation.base.BaseModel
import utils.handle

class TabNavigatorModel(
    private val mainRepository: MainRepository,
    private val initialDiaryDataUseCases: InitialDiaryDataUseCases,
) : BaseModel() {

    val tabNavigationEnabled = MutableStateFlow(false)

    init {
        requestInitialData()
    }

    private fun requestInitialData() {
        viewModelScope.launch {
            val availableDiaryDates = async { requestAvailableDiaryDates() }
            val diaryData = async { requestDiaryData() }
            awaitAll(availableDiaryDates, diaryData)
            tabNavigationEnabled.value = true
        }
    }

    private suspend fun requestAvailableDiaryDates() {
        mainRepository.requestAvailableDates().handle {
            settingsService.setAvailableDiaryDatesCount(it.availableDaysCount)
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